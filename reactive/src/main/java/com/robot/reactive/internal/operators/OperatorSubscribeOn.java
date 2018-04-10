package com.robot.reactive.internal.operators;

import com.robot.reactive.Subscriber;

/**
 *
 * @author robot
 * @date 2018/2/11
 */
public class OperatorSubscribeOn<T> implements Operator<T, T> {
	@Override
	public Subscriber<? super T> call(final Subscriber<? super T> o) {
		return new Subscriber<T>() {
			@Override
			public void onNext(final T val) {
				new Thread() {
					@Override
					public void run() {
						o.onNext(val);
					}
				}.start();

			}

			@Override
			public void onComplete() {
				new Thread() {
					@Override
					public void run() {
						o.onComplete();
					}
				}.start();
			}

			@Override
			public void onError(final Throwable e) {
				new Thread() {
					@Override
					public void run() {
						o.onError(e);
					}
				}.start();
			}
		};
	}
}
