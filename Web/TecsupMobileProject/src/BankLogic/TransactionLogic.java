package BankLogic;


import java.util.List;

import BankServices.modelo.*;
import BankServices.dao.*;
import BankServices.excepcion.DAOExcepcion;

public class TransactionLogic {

	public void addTransaction(Account account, Transaction transaction)
	{
		TransactionDAO dao = new TransactionDAO();
		dao.addTransaction(account, transaction);
		
	}

	public List<Transaction> getTransactions(Account account)
	{
		TransactionDAO dao = new TransactionDAO();
		List<Transaction> transactions = dao.getTransactions(account);
		return transactions;
		
	}
	
	public List<Transaction> getTransactions(Customer customer, String AccountType)
	{
		TransactionDAO dao = new TransactionDAO();
		List<Transaction> transactions = null;
		AccountDAO Adao = new AccountDAO();
		Account account;
		try {
			account = Adao.getAccountByCustomer(customer, AccountType);
			transactions = dao.getTransactions(account);
		} 
		catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return transactions;
		
	}
	

}