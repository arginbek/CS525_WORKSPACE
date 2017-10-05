package edu.mum.account.observer;

import edu.mum.account.domain.Account;

public interface Observer {
	default void update(Account oldAccount, Account newAccount) {
		System.out.println("----------------Before Change-----------------");
		oldAccount.print();
		System.out.println("----------------After  Change-----------------");
		newAccount.print();
	}
}
