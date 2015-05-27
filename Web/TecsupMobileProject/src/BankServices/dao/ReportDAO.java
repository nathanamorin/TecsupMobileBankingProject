package BankServices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BankServices.modelo.Account;
import BankServices.modelo.Report;
import BankServices.util.ConexionBD;

public class ReportDAO extends BaseDAO {

	public List<Report> getTransactions(String account)
	{
		String queryAccount = "(select date, description, amount, status from mydb.deposit where Account_AccountNumber= ?) "
				+ " union (select date, description, amount, status from mydb.withdraw where Account_AccountNumber= ?) "
				+ " union (select date, description, amount, status from mydb.transfer where Account_AccountNumber= ?) "
				+ " union (select date, description, amount, status from mydb.deposit where Account_AccountNumber= ? ) "
				+ " union (select date, description, amount, status from mydb.withdraw where Account_AccountNumber= ?) "
				+ " union (select date, description, amount, status from mydb.transfer where Account_AccountNumber= ?)";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Report> report = new ArrayList<Report>();
		
		try{
		con = ConexionBD.obtenerConexion();
		stmt = con.prepareStatement(queryAccount);
		stmt.setString(1, account);
		stmt.setString(2, account);
		stmt.setString(3, account);
		stmt.setString(4, account);
		stmt.setString(5, account);
		stmt.setString(6, account);
		rs = stmt.executeQuery();

		while (rs.next())
		{
			//Get Transactions
			Report t = new Report(rs.getString("date"), rs.getString("description"), rs.getString("amount"), rs.getString("status"));
			report.add(t);
			
		}
		
		
		
		}
		
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
		} 
		finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
			
		}
		return report;
		
	}
}
