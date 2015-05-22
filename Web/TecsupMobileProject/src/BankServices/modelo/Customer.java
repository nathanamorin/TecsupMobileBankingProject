package BankServices.modelo;

import java.util.ArrayList;
import java.util.Collection;

public class Customer {

	private String idUser;
	private String password;
	private String name;
	private String status;
	private String passwordChangeDate;
	private Collection<SecurityQuestion> securityQuestions = new ArrayList<SecurityQuestion>();


	public Customer() {
		super();
	}



	public Customer(String idUser, String password, String name, String status,
			String passwordChangeDate,
			Collection<SecurityQuestion> securityQuestions) {
		super();
		this.idUser = idUser;
		this.password = password;
		this.name = name;
		this.status = status;
		this.passwordChangeDate = passwordChangeDate;
		this.securityQuestions = securityQuestions;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordChangeDate() {
		return passwordChangeDate;
	}

	public void setPasswordChangeDate(String passwordChangeDate) {
		this.passwordChangeDate = passwordChangeDate;
	}

	public Collection<SecurityQuestion> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(Collection<SecurityQuestion> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}


}
