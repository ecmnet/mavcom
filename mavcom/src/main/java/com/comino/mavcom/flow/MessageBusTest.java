package com.comino.mavcom.flow;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.comino.mavcom.model.segment.Battery;
import com.comino.mavcom.model.segment.Vision;
import com.comino.mavcom.model.segment.generic.Segment;
import com.comino.mavutils.legacy.ExecutorService;

public class MessageBusTest {

	public MessageBusTest() {

	//	SubmissionPublisher<Vision> publisher = new SubmissionPublisher<>();

		MessageBus bus = MessageBus.getInstance();

		Vision v2 = new Vision();
		v2.x = 0.183f;
		v2.flags = 1;


		ModelSubscriber<Vision> subscriber = new ModelSubscriber<Vision>(Vision.class,(n) -> {
			System.out.println("received V: "+n.flags);
		});

		bus.subscribe(subscriber);

		ModelSubscriber<Battery> subscribe2 = new ModelSubscriber<Battery>(Battery.class,(n) -> {
			System.out.println("received B: "+n.a0);
		});

		bus.subscribe(subscribe2);

		ModelSubscriber<Battery> subscribe3 = new ModelSubscriber<Battery>(Battery.class,(n) -> {
			System.out.println("received B2: "+n.a0);
		});

		bus.subscribe(subscribe3);

		bus.publish(v2);


        ExecutorService.create();


		ExecutorService.get().schedule(() -> {

			try {
			MessageBus bus2 = MessageBus.getInstance();

			Vision v = new Vision();
			v.x = 2.345f;
			v.flags = 3;

			Battery  b = new Battery();
			b.a0 = 8.5f;

			bus2.publish(v);

			bus2.publish(b);

			} catch(Exception e) { e.printStackTrace(); }


		}, 3, TimeUnit.SECONDS);



		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(subscriber.getData().x);

		bus.close();

	}

	public static void main(String[] args) {
		new MessageBusTest();

	}



}
