package com.robot.pattern.state;

/**
 * Created by robot on 2018/4/10.
 */
public interface AccountState {

	void deposit(AccountContext context, double amount);

	void withdraw(AccountContext context, double amount);

	void currentState(AccountContext context);
}
