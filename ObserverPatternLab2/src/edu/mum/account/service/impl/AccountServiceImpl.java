package edu.mum.account.service.impl;

import java.util.Collection;
import java.util.HashSet;

import edu.mum.account.dao.AccountDAO;
import edu.mum.account.dao.impl.AccountDAOImpl;
import edu.mum.account.domain.Account;
import edu.mum.account.domain.AccountEntry;
import edu.mum.account.domain.Customer;
import edu.mum.account.domain.InterestType;
import edu.mum.account.domain.NotifyType;
import edu.mum.account.observer.ChangeObserver;
import edu.mum.account.observer.CreationObserver;
import edu.mum.account.observer.Observer;
import edu.mum.account.service.AccountService;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	private Collection<CreationObserver> creationObservers;
	private Collection<ChangeObserver> changeObservers;

	private static final AccountService instance = new AccountServiceImpl();

	private AccountServiceImpl() {
		accountDAO = new AccountDAOImpl();
		creationObservers = new HashSet<>();
		changeObservers = new HashSet<>();
	}

	public static AccountService getInstance() {
		return instance;
	}

	@Override
	public Account createAccount(String accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);

		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		notifyObservers(NotifyType.CREATE, null, account);
		return account;
	}

	@Override
	public Account getAccount(String accountNumber) {
		return accountDAO.loadAccount(accountNumber);
	}

	@Override
	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	@Override
	public void deposit(String accountNumber, double amount) throws CloneNotSupportedException {
		Account account = accountDAO.loadAccount(accountNumber);
		Account oldAccount = (Account) account.clone();
		account.deposit(amount);
		notifyObservers(NotifyType.CHANGE, oldAccount, account);
		accountDAO.updateAccount(account);
	}

	@Override
	public void withdraw(String accountNumber, double amount) throws CloneNotSupportedException {
		Account account = accountDAO.loadAccount(accountNumber);
		Account oldAccount = (Account) account.clone();
		account.withraw(amount);
		notifyObservers(NotifyType.CHANGE, oldAccount, account);
		accountDAO.updateAccount(account);
	}

	@Override
	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description)
			throws CloneNotSupportedException {
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		Account oldToAccount = (Account) toAccount.clone();
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account oldFromAccount = (Account) fromAccount.clone();
		fromAccount.transferFunds(toAccount, amount, description);

		notifyObservers(NotifyType.CHANGE, oldToAccount, toAccount);
		notifyObservers(NotifyType.CHANGE, oldFromAccount, fromAccount);

		accountDAO.updateAccount(toAccount);
		accountDAO.updateAccount(fromAccount);
	}

	@Override
	public void addInterest() throws CloneNotSupportedException {
		for (Account account : accountDAO.getAccounts()) {
			double balance = account.addInterest();
			Account oldAccount = (Account) account.clone();
			account.addEntry(
					new AccountEntry(balance, "interest", account.getAccountNumber(), account.getCustomer().getName()));
			notifyObservers(NotifyType.CHANGE, oldAccount, account);
			accountDAO.updateAccount(account);
		}
	}

	@Override
	public void changeInterestType(String accountNumber, InterestType type) throws CloneNotSupportedException {
		Account account = accountDAO.loadAccount(accountNumber);
		Account oldAccount = (Account) account.clone();
		account.setInterestType(type);
		notifyObservers(NotifyType.CHANGE, oldAccount, account);
		accountDAO.updateAccount(account);
	}

	@Override
	public void register(Observer o) {
		if (o instanceof ChangeObserver)
			changeObservers.add((ChangeObserver) o);
		else if (o instanceof CreationObserver)
			creationObservers.add((CreationObserver) o);
	}

	@Override
	public void remove(Observer o) {
		if (o instanceof ChangeObserver)
			changeObservers.remove(o);
		else if (o instanceof CreationObserver)
			creationObservers.remove(o);
	}

	@Override
	public void notifyObservers(NotifyType type, Account oldAccount, Account newAccount) {
		if (type.equals(NotifyType.CHANGE)) {
			changeObservers.forEach(x -> x.update(oldAccount, newAccount));
		} else if (type.equals(NotifyType.CREATE)) {
			creationObservers.forEach(x -> x.update(oldAccount, newAccount));
		}
	}

}
