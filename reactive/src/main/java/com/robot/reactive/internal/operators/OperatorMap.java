package com.robot.reactive.internal.operators;

import com.robot.reactive.Subscriber;
import com.robot.reactive.function.Fun1;

public class OperatorMap<T, R> implements Operator<R, T> {
	private final Fun1<? super T, ? extends R> transformer;

	public OperatorMap(Fun1<? super T, ? extends R> transformer) {
		this.transformer = transformer;
	}

	@Override
	public Subscriber<? super T> call(final Subscriber<? super R> o) {
		return new Subscriber<T>() {
			@Override
			public void onNext(T val) {
				o.onNext(transformer.call(val));
			}

			@Override
			public void onComplete() {
				o.onComplete();
			}

			@Override
			public void onError(Throwable e) {
				o.onError(e);
			}
		};
	}

}
