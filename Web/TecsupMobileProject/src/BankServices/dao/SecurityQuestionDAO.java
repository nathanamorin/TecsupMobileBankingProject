package BankServices.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import BankServices.modelo.Customer;
import BankServices.excepcion.*;
import BankServices.util.*;

public class SecurityQuestionDAO extends BaseDAO {

	public void getQuestionByCustomerId(Customer customer) throws DAOExcepcion {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String query = "select * from securityprotocol where Customer_userID = ?";
			con = ConexionBD.obtenerConexion();
			stmt = con.prepareStatement(query);
			stmt.setString(1, customer.getIdUser());
			System.out.println(stmt);
		

			rs = stmt.executeQuery();
			while (rs.next()) {
				customer.setSecurityQuestion(rs.getInt("idSecurityProtocol"), rs.getString("Question"), rs.getString("Answer"));
			}

			
			
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			throw new DAOExcepcion(e.getMessage());
		} finally {
			this.cerrarResultSet(rs);
			this.cerrarStatement(stmt);
			this.cerrarConexion(con);
		}
		return;
	}


}
