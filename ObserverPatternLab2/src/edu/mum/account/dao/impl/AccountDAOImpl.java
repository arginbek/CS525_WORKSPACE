package edu.mum.account.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.mum.account.dao.AccountDAO;
import edu.mum.account.domain.Account;

public class AccountDAOImpl implements AccountDAO {
	Map<String, Account> accountList = new HashMap<>();

	@Override
	public void saveAccount(Account account) {
		if (account != null)
			accountList.put(account.getAccountNumber(), account);
	}

	@Override
	public void updateAccount(Account account) {
		Account acc = loadAccount(account.getAccountNumber());
		if (acc != null) {
			accountList.put(account.getAccountNumber(), account);
		}
	}

	@Override
	public Account loadAccount(String accountNumber) {
		return accountList.get(accountNumber);
	}

	@Override
	public Collection<Account> getAccounts() {
		return accountList.values();
	}

}
