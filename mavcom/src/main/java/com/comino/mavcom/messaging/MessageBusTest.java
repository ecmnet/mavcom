package com.comino.mavcom.messaging;

import com.comino.mavcom.model.segment.Battery;
import com.comino.mavcom.model.segment.Vision;
import com.comino.mavutils.workqueue.WorkQueue;

public class MessageBusTest {

	private final WorkQueue wq = WorkQueue.getInstance();

	public MessageBusTest() {

		wq.start();
		
		// SubmissionPublisher<Vision> publisher = new SubmissionPublisher<>();

		MessageBus bus = MessageBus.getInstance();

		Vision v2 = new Vision();
		v2.x = 0.183f;
		v2.flags = 1;

		ModelSubscriber<Vision> subscriber = new ModelSubscriber<Vision>(Vision.class, (n) -> {
			System.out.println("received V: " + n.flags);
		});

		bus.subscribe(subscriber);

		ModelSubscriber<Battery> subscribe2 = new ModelSubscriber<Battery>(Battery.class, (n) -> {
			System.out.println("received B1: " + n.a0);
		});

		bus.subscribe(subscribe2);

		ModelSubscriber<Battery> subscribe3 = new ModelSubscriber<Battery>(Battery.class, (n) -> {
			System.out.println("received B2: " + n.a0);
		});

		bus.subscribe(subscribe3);

		bus.publish(v2);
		
		new Thread(() -> {
		   bus.publish(new Battery());
		}).start();

		wq.addSingleTask("LP", 500, () -> {
			
			System.out.println("Publishing 1");

			try {
			   MessageBus bus2 = MessageBus.getInstance();

				Vision v = new Vision();
				v.x = 2.345f;
				v.flags = 3;

				Battery b = new Battery();
				b.a0 = 8.5f;

				bus2.publish(v);

				bus2.publish(b);

			} catch (Exception e) {
				e.printStackTrace();
			}

		});
		
		wq.addSingleTask("LP", 800, () -> {
			
			System.out.println("Publishing 2");

			try {
				MessageBus bus2 = MessageBus.getInstance();
				
				subscribe3.cancel();

				Battery b = new Battery();
				b.a0 = 8.4f;

				bus2.publish(b);

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		try {
			Thread.sleep(1000000);
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
