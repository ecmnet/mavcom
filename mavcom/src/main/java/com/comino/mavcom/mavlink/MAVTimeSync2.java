package com.comino.mavcom.mavlink;

import java.io.IOException;
import java.time.Instant;

import org.mavlink.messages.lquac.msg_timesync;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.model.DataModel;
import com.comino.mavutils.workqueue.WorkQueue;

import us.ihmc.log.LogTools;

public class MAVTimeSync2 implements Runnable {

	private final int CONVERGENCE_WINDOW     = 500;
	private final int MAX_CONS_HIGH_DEVIATION = 5;

	private final float FILTER_ALPHA_INITAL = 0.05f;
	private final float FILTER_BETA_INITAL  = 0.05f;
	private final float FILTER_ALPHA_FINAL  = 0.003f;
	private final float FILTER_BETA_FINAL   = 0.003f;
	private final int   MAX_CONS_HIGH_RTT = 5;

	private final long MAX_RTT_SAMPLE  = 10;
	private final long MAX_DEVIATION_SAMPLE = 100;


	private final IMAVComm comm;
	private final WorkQueue    wq = WorkQueue.getInstance();
	private final msg_timesync sync_s = new msg_timesync(2, 1);

	// Filter parameters
	int            sequence     = 0;
	private double filter_alpha = FILTER_ALPHA_INITAL;
	private double filter_beta  = FILTER_BETA_INITAL;

	// Estimated statistics
	private double time_offset;
	private double time_skew;

	// Outlier rejection
	int high_rtt_count;
	int high_deviation_count;

	public MAVTimeSync2(IMAVComm comm) {
		this.comm = comm;

		if (comm.isConnected()) {
			comm.getReader().getParser().registerListener(msg_timesync.class,
					(o) -> {
						try {
							handle_time_sync((msg_timesync) o);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});

			wq.addCyclicTask("HP", 100, this);
			LogTools.info("Time synchronization started");
		}

		sync_s.target_system    = 1;
		sync_s.target_component = 1;

	}

	@Override
	public void run() {
		sync_s.tc1 = 0;
		sync_s.ts1 = this.getNowNS();
		try {
			comm.write(sync_s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handle_time_sync(msg_timesync tsync) throws IOException {

		long now_ns = this.getNowNS();

		if (tsync.tc1 == 0) {
			sync_s.tc1 = now_ns;
			sync_s.ts1 = tsync.ts1;
			comm.write(sync_s);
			return;
		} else if (tsync.tc1 > 0) {
			// Time offset between this system and the remote system is calculated assuming RTT for
			// the timesync packet is roughly equal both ways.
			add_timesync_observation((tsync.ts1 + now_ns - tsync.tc1 * 2) / 2, tsync.ts1, tsync.tc1);
		}
	}

	private void add_timesync_observation(long offset_ns, long local_time_ns, long remote_time_ns) {

		long now_ns = this.getNowNS();
		long rtt_ns = now_ns - local_time_ns;
		long deviation = Math.abs((long)(time_offset) - offset_ns);

		if (rtt_ns < MAX_RTT_SAMPLE * 1000000L) {  
			if((sequence >= CONVERGENCE_WINDOW) && (deviation > MAX_DEVIATION_SAMPLE * 1000000L)) {
				high_deviation_count++;
				if (high_deviation_count > MAX_CONS_HIGH_DEVIATION) {
					LogTools.error("Time jump detected. Resetting the time synchronizer");
					reset_filter();
				}
			} else {
				if(sequence < CONVERGENCE_WINDOW) {
					float progress = (float)sequence / CONVERGENCE_WINDOW;
					float p = 1.0f - (float)Math.exp(0.5f * (1.0f - 1.0f / (1.0f - progress)));
					filter_alpha = p * FILTER_ALPHA_FINAL + (1.0f - p) * FILTER_ALPHA_INITAL;
					filter_beta = p * FILTER_BETA_FINAL + (1.0f - p) * FILTER_BETA_INITAL;
				} else {
					filter_alpha = FILTER_ALPHA_FINAL;
					filter_beta = FILTER_BETA_FINAL;
				}

				add_sample(offset_ns);
				sequence++;
				high_deviation_count = 0;
				high_rtt_count = 0;
			}
		} else {
			high_rtt_count++;
			if (high_rtt_count > MAX_CONS_HIGH_RTT) {
				LogTools.warn("RTT too high for timesync: "+(rtt_ns / 1000000.0f)+"ms");
				high_rtt_count = 0;
			}
		}

		DataModel.t_offset_us = (long)time_offset / 1000L;
		
//		LogTools.info("PX4 "+DataModel.getSynchronizedPX4Time_us());
//		LogTools.info("Est "+DataModel.t_offset_us);
//		LogTools.info("RTTP "+(rtt_ns / 1000000.0f));
	}

	void add_sample(long offset_ns) {
		// Online exponential smoothing filter. The derivative of the estimate is also
		// estimated in order to produce an estimate without steady state lag:
		// https://en.wikipedia.org/wiki/Exponential_smoothing#Double_exponential_smoothing

		double time_offset_prev = time_offset;
		if (sequence == 0) {                    // First offset sample
			time_offset = offset_ns;
		} else {
			// Update the clock offset estimate
			time_offset = filter_alpha * offset_ns + (1.0 - filter_alpha) * (time_offset + time_skew);
			// Update the clock skew estimate
			time_skew = filter_beta * (time_offset - time_offset_prev) + (1.0 - filter_beta) * time_skew;
		}
	}

	private void reset_filter() {
		sequence = 0;
		time_offset = 0.0;
		time_skew = 0.0;
		filter_alpha = FILTER_ALPHA_INITAL;
		filter_beta = FILTER_BETA_INITAL;
		high_deviation_count = 0;
		high_rtt_count = 0;
	}

	private long getNowNS() {
		Instant ins = Instant.now();
		return ins.getEpochSecond() * 1000000000L + ins.getNano();
	}

}
