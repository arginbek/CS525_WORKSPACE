package edu.mum.account.observer;

import edu.mum.account.domain.Account;

public class EmailSender implements CreationObserver {

	private static final EmailSender instance = new EmailSender();

	private EmailSender() {
	}

	@Override
	public void update(Account oldAccount, Account newAccount) {
		System.out.println("-----------------Email Sender-------------------");
		newAccount.print();
	}

	public static EmailSender getInstance() {
		return instance;
	}

}
