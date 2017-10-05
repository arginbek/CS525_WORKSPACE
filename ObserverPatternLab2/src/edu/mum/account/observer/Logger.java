package edu.mum.account.observer;

import edu.mum.account.domain.Account;

public class Logger implements ChangeObserver {

	private static final Logger instance = new Logger();

	private Logger() {
	}

	@Override
	public void update(Account oldAccount, Account newAccount) {
		System.out.println("--------------Logger------------------");
		ChangeObserver.super.update(oldAccount, newAccount);
	}

	public static Logger getInstance() {
		return instance;
	}
}
