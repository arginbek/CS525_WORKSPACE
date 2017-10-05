package edu.mum.account.service;

import java.util.Collection;

import edu.mum.account.domain.Account;
import edu.mum.account.domain.InterestType;
import edu.mum.account.observer.Observable;

public interface AccountService extends Observable {
	Account createAccount(String accountNumber, String customerName);

	Account getAccount(String accountNumber);

	Collection<Account> getAllAccounts();

	void deposit(String accountNumber, double amount) throws CloneNotSupportedException;

	void withdraw(String accountNumber, double amount) throws CloneNotSupportedException;

	void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description)
			throws CloneNotSupportedException;

	void addInterest() throws CloneNotSupportedException;

	void changeInterestType(String accountNumber, InterestType type) throws CloneNotSupportedException;
}
