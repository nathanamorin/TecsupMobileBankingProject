package BankServices.modelo;

import java.util.Collection;

public class Withdrawal {
	private String idUser;
	private String Amount;
	private int AccountNumber;




public int getAccountNumber() {
		return AccountNumber;
	}



	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}



public Withdrawal() {
	super();
}



public Withdrawal(String idUser, String Amount) {
	super();
	this.idUser = idUser;
	this.Amount = Amount;
}



public String getIdUser() {
	return idUser;
}



public void setIdUser(String idUser) {
	this.idUser = idUser;
}



public String getAmount() {
	return Amount;
}



public void setAmount(String amount) {
	Amount = amount;
}
}