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

	public Collection<CheckingAccount> listar() throws DAOExcepcion {
		Collection<CheckingAccount> c = new ArrayList<CheckingAccount>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = ConexionBD.obtenerConexion();
			String query = "select AccountNumber,CurrentBal,DateOpened,Status,InterestRate,AvailableBal,MinBal,Customer_userID from CheckingAccount";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {

				CheckingAccount ch = new CheckingAccount();
				
				
				ch.setAccountNumber(rs.getString("AccountNumber"));
				ch.setCurrenBal(rs.getInt("CurrentBal"));
				ch.setDateOpened(rs.getString("DateOpened"));
				ch.setStatus(rs.getString("Status"));
				ch.setInteresRate(rs.getInt("InterestRate"));
				ch.setAvailableBal(rs.getInt("AvailableBal"));
				ch.setMinBal(rs.getInt("MinBal"));
				ch.setCustomer_idUser(rs.getString("Customer_userId"));
				
				c.add(ch);
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return c;
	}

}