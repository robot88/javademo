package com.robot.reactive.internal.operators;

import com.robot.reactive.Subscriber;
import com.robot.reactive.function.Fun1;

public interface Operator<R, T> extends Fun1<Subscriber<? super R>, Subscriber<? super T>> {
}
