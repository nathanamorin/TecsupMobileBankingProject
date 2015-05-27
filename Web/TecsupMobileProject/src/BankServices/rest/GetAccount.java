package BankServices.rest;



import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import BankServices.dao.CustomerDAO;
import BankServices.dao.SecurityQuestionDAO;
import BankServices.modelo.Account;
import BankServices.modelo.Customer;
import BankServices.dao.CheckingDAO;
import BankServices.modelo.SecurityQuestion;
import net.sf.json.JSONObject;

@Path("/GetAccount")
public class GetAccount {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON + "; charset=utf-8")
	public String Test( ) {

		
		JSONObject arrayObj = new JSONObject();
		
		try {
			arrayObj.put("response","test");
			
			
		} catch (Exception e) {	
			System.out.println(e.getMessage());
		}
		return arrayObj.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("username") String username){



		JSONObject jsonObj = new JSONObject();
		
		try {
			if (username == "" || username == null)
			{
				jsonObj.put("return", "errorUsername");
				return jsonObj.toString();
			}			
		
						
			CheckingDAO dao = new CheckingDAO();
			Account checking = dao.getCheckingById(username);
			
			jsonObj.put("Acc_Num", checking.getAccountNumber());
			jsonObj.put("bal", checking.getCurrentBal());
			return jsonObj.toString();		
			
		
						
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return jsonObj.toString();
	}
	

}
