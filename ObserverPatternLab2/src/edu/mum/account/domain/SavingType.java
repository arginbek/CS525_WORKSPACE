package edu.mum.account.domain;

public class SavingType implements InterestType {
	@Override
	public double addInterest(double balance) {
		return balance < 1000 ? balance * 0.01 : (balance > 5000 ? balance * 0.02 : balance * 0.04);
	}
}
