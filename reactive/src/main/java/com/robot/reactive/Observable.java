package com.robot.reactive;

import com.robot.reactive.function.Action0;
import com.robot.reactive.function.Action1;
import com.robot.reactive.function.Fun1;
import com.robot.reactive.internal.operators.Operator;
import com.robot.reactive.internal.operators.OperatorMap;
import com.robot.reactive.internal.operators.OperatorSubscribeOn;

/**
 * 被观察对象
 * @author robot
 */
public class Observable<T> {
	private final OnSubscriber<T> onSubscriber;

	private Observable(OnSubscriber<T> onSubscriber) {
		this.onSubscriber = onSubscriber;
	}

	public static <T> Observable<T> create(OnSubscriber<T> onSubscriber) {
		return new Observable(onSubscriber);
	}

	public void subscribe(Subscriber<? super T> subscriber) {
		subscriber.onStart();
		this.onSubscriber.call(subscriber);
	}

	public void subscribe(final Action1<T> onNext) {
		Observable.this.subscribe(onNext, null, null);
	}

	public void subscribe(final Action1<T> onNext, final Action1<Throwable> onError) {
		Observable.this.subscribe(onNext, onError, null);
	}

	public void subscribe(final Action1<T> onNext, final Action1<Throwable> onError, final Action0 onComplete) {
		if (onNext == null) {
			throw new IllegalArgumentException("onNext can not be null");
		}
		Observable.this.subscribe(new Subscriber<T>() {
			@Override
			public void onNext(T val) {
				onNext.call(val);
			}

			@Override
			public void onComplete() {
				if (onComplete != null) {
					onComplete.call();
				}
			}

			@Override
			public void onError(Throwable e) {
				if (onError != null) {
					onError.call(e);
				}
			}
		});
	}

	public <R> Observable<R> map(Fun1<? super T, ? extends R> func) {
		return left(new OperatorMap<T, R>(func));
	}

	public Observable<T> subscribeOn() {
		return left(new OperatorSubscribeOn<T>());
	}

	public <R> Observable<T> test() {
		return null;
	}

	public <R> Observable<R> left(final Operator<? extends R, ? super T> operator) {
		return create(new OnSubscriber<R>() {
			@Override
			public void call(Subscriber<? super R> subscriber) {
				Subscriber<? super T> st = operator.call(subscriber);
				Observable.this.subscribe(st);
			}
		});
	}

	public interface OnSubscriber<T> {
		void call(Subscriber<? super T> subscriber);
	}
}
