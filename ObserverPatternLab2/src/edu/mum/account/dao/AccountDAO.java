package edu.mum.account.dao;

import java.util.Collection;

import edu.mum.account.domain.Account;

public interface AccountDAO {
	public void saveAccount(Account account);

	public void updateAccount(Account account);

	public Account loadAccount(String accountNumber);

	public Collection<Account> getAccounts();
}
