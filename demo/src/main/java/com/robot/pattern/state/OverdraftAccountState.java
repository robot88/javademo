package com.robot.pattern.state;

/**
 * Created by robot on 2018/4/10.
 */
public class OverdraftAccountState implements AccountState {
	@Override
	public void deposit(AccountContext context, double amount) {
		System.out.println(context.getName() + "存款" + amount);
		double balance = context.getBalance() + amount;
		context.setBalance(balance);

		if (balance >= 0) {
			context.setState(AccountStateFactory.getNormalAccountState());
		}
	}

	@Override
	public void withdraw(AccountContext context, double amount) {
		System.out.println(context.getName() + "取款" + amount);
		double balance = context.getBalance() - amount;
		context.setBalance(balance);

		if (balance < -2000) {
			context.setState(AccountStateFactory.getRestrictedAccountState());
		}
	}

	@Override
	public void currentState(AccountContext context) {
		System.out.println(context.getName() + "账户当前余额为：" + context.getBalance());
		System.out.println("账户状态：透支，需收取利息");
		System.out.println("------------------------------");
	}
}
