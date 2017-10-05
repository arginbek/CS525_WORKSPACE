package edu.mum.account.domain;

import java.util.ArrayList;
import java.util.List;

public class Account implements Cloneable {
	private String accountNumber;
	private Customer customer;
	private List<AccountEntry> entries = new ArrayList<>();
	private InterestType interestType;

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
		this.interestType = new CheckingType();
	}

	public Account(String accountNumber, InterestType interestType) {
		this.accountNumber = accountNumber;
		this.interestType = interestType;
	}

	public Account(String accountNumber, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.customer = customer;
		this.entries = new ArrayList<>();
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
		entries.add(entry);
	}

	public void withraw(double amount) {
		AccountEntry entry = new AccountEntry(amount, "withraw", "", "");
		entries.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(amount, description, accountNumber, customer.getName());

		entries.add(fromEntry);

		toAccount.addEntry(toEntry);
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entries) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public double addInterest() {
		return interestType.addInterest(getBalance());
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
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

	public void addEntry(AccountEntry entry) {
		entries.add(entry);
	}

	public InterestType getInterestType() {
		return interestType;
	}

	public void setInterestType(InterestType interestType) {
		this.interestType = interestType;
	}

	public void print() {
		System.out.println("--------------Account Info Start-----------------------");
		System.out.println("Owner:" + customer.getName());
		System.out.println("AccountType: " + interestType.getClass().getName());
		System.out.println("Balance: " + getBalance());
		System.out.println("--------------Account Info End-----------------------");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
