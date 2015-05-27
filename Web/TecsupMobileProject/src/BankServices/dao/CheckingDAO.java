package BankServices.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BankServices.modelo.*;
import BankServices.excepcion.*;
import BankServices.dao.*;
import BankServices.util.*;

public class CheckingDAO extends BaseDAO {

	public Account getCheckingById(String uID) throws DAOExcepcion {
		Account account = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ConexionBD.obtenerConexion();
			String query = "select AccountNumber,CurrentBal,DateOpened,Status,InterestRate,AvailableBal,MinBal,Customer_userID from account where Customer_userID = ? and AccountType = Checking";
			stmt = con.prepareStatement(query);
			stmt.setString(1, uID);
			rs = stmt.executeQuery();
			if (rs.next()) {

				
				
				
				account.setAccountNumber(rs.getString("AccountNumber"));
				account.setCurrentBal(rs.getInt("CurrentBal"));
				
				account.setStatus(rs.getString("Status"));
				account.setInterestRate(rs.getInt("InterestRate"));
				account.setAvailableBal(rs.getInt("AvailableBal"));
				account.setMinBal(rs.getInt("MinBal"));
				
				
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return account;
	}

}