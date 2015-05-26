package BankServices.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BankServices.modelo.*;
import BankServices.excepcion.*;
import BankServices.util.*;

public class AccountDAO extends BaseDAO {

	public Account getAccountByCustomer(Customer customer, String AccountType) throws DAOExcepcion {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Account ch = null;
		try {
			con = ConexionBD.obtenerConexion();
			String query = "select AccountNumber,CurrentBal,DateOpened,Status,InterestRate,AvailableBal,MinBal,Customer_userID from account where Customer_userID=? AND AccountType=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, customer.getIdUser());
			stmt.setString(2, AccountType);
			rs = stmt.executeQuery();
			
			if (rs.next()) {

				ch = new Account();
				
				
				ch.setAccountNumber(rs.getString("AccountNumber"));
				ch.setCurrentBal(rs.getInt("CurrentBal"));
				ch.setStatus(rs.getString("Status"));
				ch.setInterestRate(rs.getInt("InterestRate"));
				ch.setAvailableBal(rs.getInt("AvailableBal"));
				ch.setMinBal(rs.getInt("MinBal"));
				//ch.setCustomer_idUser(customer.getIdUser());
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return ch;
	}

}