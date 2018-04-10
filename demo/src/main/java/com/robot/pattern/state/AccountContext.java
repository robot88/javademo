package com.robot.pattern.state;

/**
 * Created by robot on 2018/4/10.
 */
public class AccountContext {
	private AccountState state;
	private String name;
	private double balance;

	public AccountContext(String name) {
		this.name = name;
		state = AccountStateFactory.getNormalAccountState();
		state.currentState(this);
	}

	public void deposit(double amount) {
		state.deposit(this, amount);
		state.currentState(this);
	}

	public void withdraw(double amount) {
		state.withdraw(this, amount);
		state.currentState(this);
	}

	public AccountState getState() {
		return state;
	}

	public void setState(AccountState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
