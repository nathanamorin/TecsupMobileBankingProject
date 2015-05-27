package BankServices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankServices.excepcion.DAOExcepcion;
import BankServices.modelo.Withdrawal;
import BankServices.util.ConexionBD;

public class WithdrawalDAO extends BaseDAO{
	
	
	public Withdrawal getAccBalance(float balance) throws DAOExcepcion {
		
		Withdrawal withdrawal = new Withdrawal();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String query = "select CurrentBal from account where AccountNumber = ?";
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setFloat(1, balance);
			System.out.println(stmt);
		

			rs = stmt.executeQuery();
			if (rs.next()) {
				
				withdrawal.setAccountNumber(rs.getInt("AccountNumber"));
					
			}
			else
			{
				System.out.println("No Data");
			}
			
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return withdrawal;
		
	}
	
	
	
	public Withdrawal getWithdrawal(float actualBal) throws DAOExcepcion {
		
		Withdrawal balance = new Withdrawal();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
try {
			
	
			String query = "update account"
					+ " SET CurrentBal = " + actualBal + " WHERE AccountNumber = ? ";
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setFloat(1, actualBal);
			System.out.println(stmt);
		

			rs = stmt.executeQuery();
			if (rs.next()) {
				
				balance.setAccountNumber(rs.getInt("AccountNumber"));
					
			}
			else
			{
				System.out.println("No Data");
			}
			
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return balance;
	}
	
}
