package com.robot.reactive;

import static javafx.scene.input.KeyCode.O;

/**
 * 具体订阅者
 * @author robot
 */
public abstract class Subscriber<T> implements Observer<T> {
	public void onStart() {
	}
}
