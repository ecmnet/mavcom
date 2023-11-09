package com.comino.mavcom.mavlink;

import java.time.Instant;

import org.mavlink.messages.lquac.msg_log_data;
import org.mavlink.messages.lquac.msg_logging_data;
import org.mavlink.messages.lquac.msg_timesync;

import com.comino.mavcom.comm.IMAVComm;
import com.comino.mavcom.model.DataModel;
import com.comino.mavutils.workqueue.WorkQueue;

// TODO: Better implementation
// http://docs.ros.org/en/lunar/api/mavros/html/sys__time_8cpp_source.html

public class MAVTimeSync implements Runnable {

	private static double OFFSET_AVG_ALPHA = 0.1d;

	private final IMAVComm comm;
	private final WorkQueue wq = WorkQueue.getInstance();

	private final msg_timesync sync_s = new msg_timesync(2, 1);

	private long time_offset_ns = 0;

	private long    tms_logging = 0;

	public MAVTimeSync(IMAVComm comm) {
		this.comm = comm;

		if (comm.isConnected() && comm.isSerial()) {
			comm.getReader().getParser().registerListener(msg_timesync.class,
					(o) -> handle_time_sync((msg_timesync) o));
			
			comm.getReader().getParser().registerListener(msg_log_data.class,
					(o) -> check_logging((msg_log_data) o));
			
			System.out.println("Time synchronization started..."+comm.isConnected());

			wq.addCyclicTask("HP", 100, this);

		}
		
		sync_s.target_system    = 1;
		sync_s.target_component = 1;
		
	}

	
	private void check_logging(msg_log_data log) {
		tms_logging = System.currentTimeMillis();
	}

	private void handle_time_sync(msg_timesync sync) {
		
//		if(!comm.isSerial())
//			return;

		// Do not timesync during ULOG transfer
		if(System.currentTimeMillis() - tms_logging < 500)
			return;

		try {

			Instant ins = Instant.now();
			long now_ns = ins.getEpochSecond() * 1000000000L + ins.getNano();

//			 System.out.println(sync.sysId+"/ "+
//			       sync.ts1+"/ "+sync.tc1+"/ "+now_ns+"/ "+DataModel.t_offset_ns);

			if (sync.tc1 == 0) {
				synchronized (this) {
					sync_s.tc1 = now_ns;
					sync_s.ts1 = sync.ts1;
					comm.write(sync_s);
				}
				return;

			} else if (sync.tc1 > 0) {

				// check RTT
				long rtt_ms = (now_ns - sync.ts1) / 1000000L;
				if (rtt_ms > 10) {
					return;
				}

				long offset_ns = (sync.ts1 + now_ns - sync.tc1 * 2L) / 2L;
				long dt = time_offset_ns - offset_ns;
				if (dt > 100000000L || dt < -100000000L) {
					time_offset_ns = offset_ns;
					System.out.println("[sys]  Clock skew detected: " + (dt / 1000) + "us");
				} else {
					time_offset_ns = (long) (OFFSET_AVG_ALPHA * (double) offset_ns
							+ (1.0d - OFFSET_AVG_ALPHA) * (double) time_offset_ns);
				}
				DataModel.t_offset_ns = time_offset_ns;
			//	System.out.println("PX4 "+DataModel.getSynchronizedPX4Time_us());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// Publish timesync to Vehicle

		// Do not timesync during ULOG transfer
		if(System.currentTimeMillis() - tms_logging < 1000)
			return;

		try {
			Instant ins = Instant.now();
			long now_ns = ins.getEpochSecond() * 1000000000L + ins.getNano();
			synchronized (this) {
				sync_s.tc1 = 0;
				sync_s.ts1 = now_ns;
				comm.write(sync_s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "Current offset: " + (time_offset_ns / 1000L) + "us";
	}

}
