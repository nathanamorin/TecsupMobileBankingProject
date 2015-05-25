package BankServices.modelo;



public class Account {

	private String AccountNumber;
	private double CurrentBal;
	private String Status;
	private double InterestRate;
	private double AvailableBal;
	private double MinBal;
	private String AccountName;


	public Account() {
		super();
	}


	public Account(String accountNumber, double currentBal, String status,
			double interestRate, double availableBal, double minBal, String AccountName) {
		super();
		AccountNumber = accountNumber;
		CurrentBal = currentBal;
		Status = status;
		InterestRate = interestRate;
		AvailableBal = availableBal;
		MinBal = minBal;
	}
	
	
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public double getCurrentBal() {
		return CurrentBal;
	}
	public void setCurrentBal(double currentBal) {
		CurrentBal = currentBal;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public double getInterestRate() {
		return InterestRate;
	}
	public void setInterestRate(double interestRate) {
		InterestRate = interestRate;
	}
	public double getAvailableBal() {
		return AvailableBal;
	}
	public void setAvailableBal(double availableBal) {
		AvailableBal = availableBal;
	}
	public double getMinBal() {
		return MinBal;
	}
	public void setMinBal(double minBal) {
		MinBal = minBal;
	}
	public String getAccountName() {
		return AccountName;
	}


	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	
	




}
