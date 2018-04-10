package com.robot.pattern.state;

/**
 * Created by robot on 2018/4/10.
 */
public class StatePatternDemo {
	public static void main(String[] args) {
		AccountContext account = new AccountContext("robot");
		account.deposit(2000);
		account.withdraw(3000);
		account.deposit(2000);
		account.withdraw(2000);
		account.withdraw(2000);
		account.withdraw(2000);
	}
}
