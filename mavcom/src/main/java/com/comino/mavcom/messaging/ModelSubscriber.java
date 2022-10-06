package com.comino.mavcom.messaging;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ModelSubscriber<ModelType> {

	private MSubscriber subscriber;
	private Class<ModelType> clazz;
	private ModelType data;
	private ModelListener<ModelType> listener;

	public ModelSubscriber(Class<ModelType> clazz) {
		this(clazz, null);
	}

	public ModelSubscriber(Class<ModelType> clazz, ModelListener<ModelType> listener) {
		this.clazz = clazz;
		this.listener = listener;
		this.subscriber = new MSubscriber();
	}

	public Subscriber<ModelType> get() {
		return this.subscriber;
	}

	public Class<?> getType() {
		return clazz;
	}

	public void request() {
		subscriber.request();
	}

	public boolean hasData() {
		return data != null;
	}

	public ModelType getData() {
		return data;
	}

	public void registerListener(ModelListener<ModelType> listener) {
		this.listener = listener;
	}

	private class MSubscriber implements Subscriber<ModelType> {

		private Subscription subscription;

		public void request() {
			subscription.request(1);
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			this.subscription = subscription;
			subscription.request(1);

		}

		@Override
		public void onNext(ModelType item) {
			data = item;
			if (listener != null)
				listener.listen(data);
			subscription.request(1);
		}

		@Override
		public void onError(Throwable throwable) {
			System.err.println("Flow " + throwable.getMessage());
		}

		@Override
		public void onComplete() {

		}

	}

}
