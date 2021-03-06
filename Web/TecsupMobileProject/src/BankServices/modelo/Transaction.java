package BankServices.modelo;

import java.sql.Timestamp;

public class Transaction {

	private int ID;
	private Timestamp TransactionDateTime;
	private double Amount;
	private String TransactionType;
	private String Description;
	
	public Transaction() {
		super();
	}

	public Transaction(int iD, Timestamp transactionDateTime, double amount,
			String transactionType, String description) {
		super();
		ID = iD;
		TransactionDateTime = transactionDateTime;
		Amount = amount;
		TransactionType = transactionType;
		Description = description;
	}


	public void setID(int iD) {
		ID = iD;
	}

	public String getTransactionDateTime() {
		return TransactionDateTime.toString();
	}

	public void setTransactionDateTime(Timestamp transactionDateTime) {
		TransactionDateTime = transactionDateTime;
	}


	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
	
	public String getTransactionType() {
		return TransactionType;
	}

	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}


}
