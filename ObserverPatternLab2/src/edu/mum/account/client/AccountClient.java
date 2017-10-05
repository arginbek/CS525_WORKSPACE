package edu.mum.account.client;

import edu.mum.account.domain.Account;
import edu.mum.account.domain.AccountEntry;
import edu.mum.account.domain.Customer;
import edu.mum.account.domain.SavingType;
import edu.mum.account.observer.EmailSender;
import edu.mum.account.observer.Logger;
import edu.mum.account.observer.SMSSender;
import edu.mum.account.service.AccountService;
import edu.mum.account.service.impl.AccountServiceImpl;

public class AccountClient {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		AccountService accountService = AccountServiceImpl.getInstance();

		accountService.register(Logger.getInstance());
		accountService.register(SMSSender.getInstance());
		accountService.register(EmailSender.getInstance());

		// create 2 accounts;
		accountService.createAccount("1263862", "Frank Brown");
		accountService.remove(EmailSender.getInstance());
		System.out.println("---------------------------------------------");
		System.out.println("---------------Observer Removed--------------");
		System.out.println("---------------------------------------------");
		accountService.createAccount("4253892", "John Doe");
		// use account 1;
		accountService.deposit("1263862", 240);
		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 230);
		// use account 2;
		accountService.deposit("4253892", 12450);
		accountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");
		accountService.changeInterestType("4253892", new SavingType());
		accountService.addInterest();
		// show balances

		for (Account account : accountService.getAllAccounts()) {
			Customer customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + customer.getName());

			System.out.println(
					"-Date-------------------------" + "-Description------------------" + "-Amount-------------");

			for (AccountEntry entry : account.getEntries()) {
				System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(),
						entry.getAmount());
			}

			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
		}
	}

}
