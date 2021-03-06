package BankLogic;

import BankServices.modelo.*;
import BankServices.excepcion.*;
import BankServices.dao.*;

public class LoginLogic {

	
	public SecurityQuestion doLogin(String username, String password) throws DAOExcepcion
	{//OUT OF DATE DO NOT USE
		
		if (username == "" || username == null)
		{
			return null;
		}
		
		else if (password == "" || password == null)
		{
			return null;
		}
		
		
		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.getCustomerById(username);
		SecurityQuestionDAO SQdao = new SecurityQuestionDAO();
		
		SQdao.getQuestionByCustomerId(customer);
		
		
		if (!password.equals(customer.getPassword()))
		{
			return null;
		}
		
		return customer.getRandomSecurityQuestion();
		
		
	}
	
	public Customer doLogin(String username, String password, Integer securityQuestionID, String securityAnswer) throws DAOExcepcion
	{
		
		if (username == "" || username == null)
		{
			System.out.println("Do Login Error Username");
			return null;
		}
		
		else if (password == "" || password == null)
		{
			System.out.println("Do Login Error Password");
			return null;
		}
		
		
		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.getCustomerById(username);
		SecurityQuestionDAO SQdao = new SecurityQuestionDAO();
		
		SQdao.getQuestionByCustomerId(customer);
		
		
		if (!password.equals(customer.getPassword()))
		{
			System.out.println("Do Login Error Password Not Match");
			return null;
		}



		if (securityAnswer == null || securityAnswer == "" || securityQuestionID == null)
		{
			System.out.println("Customer do login " + customer.getName());
			return customer;
		}
		
		SecurityQuestion ResponseSQ = customer.getSecurityQuestion(securityQuestionID);
		if (ResponseSQ == null)
		{
			System.out.println("Customer id of secure " + securityQuestionID.toString());
			return customer;
		}
		
		if (!securityAnswer.equals(ResponseSQ.getAnswer()) )
		{
			System.out.println("Customer answer " + securityAnswer);
			System.out.println("Correct answer" + ResponseSQ.getAnswer());
			return customer;
		}
		customer.loggedIn = true;
		return customer;
		
		
	}
	

}