package edu.mum.account.domain;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private long accountNumber;
	private Customer customer;
	private List<AccountEntry> entries;
	private double balance;

	public Account(long accountNumber, Customer customer, List<AccountEntry> entries, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.entries = entries;
		this.balance = balance;
	}

	public Account(long accountNumber, Customer customer, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.entries = new ArrayList<>();
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<AccountEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<AccountEntry> entries) {
		this.entries = entries;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
