package BankServices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BankServices.excepcion.DAOExcepcion;
import BankServices.modelo.Customer;

import BankServices.util.*;

public class CustomerDAO extends BaseDAO {

	public Customer getCustomerById(String idCustomer) throws DAOExcepcion {
		Customer customer = new Customer();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String query = "select * from Customer where userID = ?";
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, idCustomer);
			System.out.println(stmt);
		

			rs = stmt.executeQuery();
			if (rs.next()) {
				customer.setIdUser(rs.getString("userID"));
				customer.setPassword(rs.getString("password"));
				customer.setName(rs.getString("firstName") + " " + rs.getString("LastName"));	
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
		return customer;
	}

	
	public void changeAttr(Customer customer)
	{
		String query = "insert into Customer (Status) VALUES (" + customer.getStatus() + ") where userID=?";
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
		con = ConexionBD.obtenerConexion();
		stmt = con.prepareStatement(query);
		stmt.setString(1,customer.getIdUser());
		stmt.executeUpdate();
		}
		
		
		catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		
	}
	

	
	


}
	
	
	
