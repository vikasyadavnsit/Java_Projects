package org.builder;

public class Client {
	public static void main(String[] args) {

		BankAccount account = new BankAccount.Builder(123L).balance(243.234).build();
		System.out.println(account.toString());
		
	}
}
