package com.robot.reactive;

/**
 * 观察者
 * @author robot
 */
public interface Observer<T> {
	/**
	 * 事件回调方法
	 * @param val 参数
	 */
	void onNext(T val);

	/**
	 * 事件执行完成回调方法
	 */
	void onComplete();

	/**
	 * 事件执行失败回调方法
	 * @param e 异常
	 */
	void onError(Throwable e);
}
