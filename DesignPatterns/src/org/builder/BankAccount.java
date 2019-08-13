package org.builder;

public class BankAccount {

	public static class Builder {

		private long accountNumber;
		private String owner;
		private String branch;
		private double balance;

		public Builder(long accountNumber) {
			this.accountNumber = accountNumber;
		}

		public Builder owner(String owner) {
			this.owner = owner;
			return this;
		}

		public Builder atBranch(String branch) {
			this.branch = branch;
			return this;
		}

		public Builder balance(Double balance) {
			this.balance = balance;
			return this;
		}

		public BankAccount build() {

			BankAccount account = new BankAccount();
			account.accountNumber = this.accountNumber;
			account.balance = this.balance;
			account.owner = this.owner;
			account.branch = this.branch;
			return account;

		}

	}

	private long accountNumber;
	private String owner;
	private String branch;
	private double balance;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	private BankAccount() {
		
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", owner=" + owner + ", branch=" + branch + ", balance="
				+ balance + "]";
	}
	
	
	
}
