package com.robot.pattern.state;

/**
 * Created by robot on 2018/4/10.
 */
public class AccountStateFactory {
	private static AccountState normalAccountState = new NormalAccountState();
	private static AccountState overdraftAccountState = new OverdraftAccountState();
	private static AccountState restrictedAccountState = new RestrictedAccountState();

	public static AccountState getNormalAccountState() {
		return normalAccountState;
	}

	public static AccountState getOverdraftAccountState() {
		return overdraftAccountState;
	}

	public static AccountState getRestrictedAccountState() {
		return restrictedAccountState;
	}
}
