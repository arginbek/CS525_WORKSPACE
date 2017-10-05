package edu.mum.account.observer;

import edu.mum.account.domain.Account;

public class SMSSender implements ChangeObserver {

	private static final SMSSender instance = new SMSSender();

	private SMSSender() {
	}

	@Override
	public void update(Account oldAccount, Account newAccount) {
		System.out.println("--------------SMSSender----------------------");
		ChangeObserver.super.update(oldAccount, newAccount);
	}

	public static SMSSender getInstance() {
		return instance;
	}
}
