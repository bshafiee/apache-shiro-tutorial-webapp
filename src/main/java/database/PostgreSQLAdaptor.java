package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import org.slf4j.Logger;

public class PostgreSQLAdaptor {
	private static PostgreSQLAdaptor m_instance = new PostgreSQLAdaptor();
	private boolean initialized = false;
	
	//Pgresql tools
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rSet = null;
	
	private final String url = "jdbc:postgresql://localhost/";
	private final String user = "postgres";
	private final String password = "behrooz";
	
	private PostgreSQLAdaptor(){}
	
	public static PostgreSQLAdaptor getInstance()
	{
		return m_instance;
	}
	
	public boolean initialize()
	{
		initialized = false;
		try {
            conn = DriverManager.getConnection(url, user, password);
            initialized = true;
            return true;
        } catch (SQLException ex) {
        	ex.printStackTrace();
        	return false;
        }
	}
	
	public boolean shutdown()
	{
		try {
		 if (rSet != null) {
             rSet.close();
         }
         if (st != null) {
             st.close();
         }
         if (conn != null) {
             conn.close();
         }
         return true;
		} catch (SQLException ex) {
            ex.printStackTrace();
            return false;
		}
	}
	
	public int executeUpdate(String query) throws SQLException
	{
		st = conn.createStatement();
		return st.executeUpdate(query);
	}
	
	public ResultSet executeQuery(String query) throws SQLException
	{
		st = conn.createStatement();
		return st.executeQuery(query);
	}

	@Override
	protected void finalize() throws Throwable {
		shutdown();
		super.finalize();
	}
	
	
}
