package com.training.practice;

public class Application {
	
	public static void main(String[] args) throws Exception {
		
		TransactionHistory transaction1 = new TransactionHistory(100, "Naresh", "Credit", 19876);
		TransactionHistory transaction2 = new TransactionHistory(101, "Manish", "Credit", 2376);
		TransactionHistory transaction3 = new TransactionHistory(102, "Chandan", "Credit", 7675);
		TransactionHistory transaction4 = new TransactionHistory(103, "Mayur", "Credit", 7394);
		TransactionHistory transaction5 = new TransactionHistory(104, "Ashutosh", "Credit", 54236);
		
		Hibernate<TransactionHistory> conn = Hibernate.getConnection();
		
		conn.write(transaction1);
		conn.write(transaction2);
		conn.write(transaction3);
		conn.write(transaction4);
		conn.write(transaction5);
		
		TransactionHistory obj = conn.read(TransactionHistory.class, 1L);
		
		System.out.println(obj);
	}

}
