package edu.mum.account.domain;

public class CheckingType implements InterestType {
	@Override
	public double addInterest(double balance) {
		return balance <= 1000 ? balance * 0.015 : balance * 0.025;
	}
}
