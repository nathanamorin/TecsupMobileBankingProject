package BankServices.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import BankServices.dao.WithdrawalDAO;
import BankServices.modelo.SecurityQuestion;
import BankServices.modelo.Withdrawal;
import net.sf.json.JSONObject;

@Path("/withdrawal")
public class WithdrawalRest {
	
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
	public String withdrawal(@FormParam("AccountNumber") String AccountNumber, 
							@FormParam("Amount") float Amount, 
							@FormParam("txtAccountNum") String AccountNum, float acc_balance){


		JSONObject jsonObj = new JSONObject();
		
	
		
		try {
			
			WithdrawalDAO wdao = new WithdrawalDAO();
			Withdrawal withdrawal = wdao.getAccBalance(acc_balance);
			
			if (acc_balance < Amount)
			{
				jsonObj.put("return", "errorAmountInvalid");
				return jsonObj.toString();
			}
			
			float actualBal = acc_balance - Amount;
			wdao.getWithdrawal(actualBal);
			
			
			
			
			
			
					
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		jsonObj.put("return", "success");
		return jsonObj.toString();
	}
	

}
