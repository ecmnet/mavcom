package com.comino.mavcom.messaging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

import us.ihmc.log.LogTools;

public class MessageBus {

	private static MessageBus instance = null;

	private Map<Class<?>, SubmissionPublisher<?>> streams = new HashMap<Class<?>, SubmissionPublisher<?>>();

	public static MessageBus getInstance() {
		if (instance == null) {
			instance = new MessageBus();
			LogTools.info("MessageBus instance created");
		}
		return instance;
	}

	public <T> void subscribe(ModelSubscriber<T> subscriber) {
		@SuppressWarnings("unchecked")
		SubmissionPublisher<T> p = (SubmissionPublisher<T>) streams.get(subscriber.getType());
		if (p == null) {
			p = new SubmissionPublisher<T>();
			streams.put(subscriber.getType(), p);
		}
		p.subscribe(subscriber.get());
		System.out.println("Subscription: " + subscriber.getClass().getName() + " -> " + subscriber.getType());
	}

	public <T> void publish(T data) {
		@SuppressWarnings("unchecked")
		SubmissionPublisher<T> p = (SubmissionPublisher<T>) streams.get(data.getClass());
		if (p != null && p.hasSubscribers()) {
			p.submit(data);
		}
	}

	public void close() {
		streams.values().forEach((n) -> {
			n.close();
		});
		streams.clear();
	}

}
