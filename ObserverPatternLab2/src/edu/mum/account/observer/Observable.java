package edu.mum.account.observer;

import edu.mum.account.domain.Account;
import edu.mum.account.domain.NotifyType;

public interface Observable {
	public void register(Observer o);

	public void remove(Observer o);

	public void notifyObservers(NotifyType type, Account oldAccount, Account newAccount);
}
