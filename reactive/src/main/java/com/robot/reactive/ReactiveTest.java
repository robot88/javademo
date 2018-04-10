package com.robot.reactive;

import com.robot.reactive.function.Action1;
import com.robot.reactive.function.Fun1;

/**
 *
 * @author robot
 * @date 2018/2/1
 */
public class ReactiveTest {
	public static void main(String[] args) {
		Observable.create(new Observable.OnSubscriber<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> subscriber) {
				System.out.println(Thread.currentThread().getName());
				subscriber.onNext(1);
			}
		}).map(new Fun1<Integer, String>() {
			@Override
			public String call(Integer o) {
				return String.valueOf(o);
			}
		}).subscribeOn().subscribe(new Action1<String>() {
			@Override
			public void call(String o) {
				System.out.println(Thread.currentThread().getName());
				System.out.println(o);
			}
		});
	}
}
