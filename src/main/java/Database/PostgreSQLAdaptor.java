package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;

public class PostgreSQLAdaptor {
	private static PostgreSQLAdaptor m_instance = new PostgreSQLAdaptor();
	private boolean initialized = false;
	
	//Pgresql tools
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rSet = null;
	
	private final String url = "jdbc:postgresql://localhost/testdb";
	private final String user = "behrooz";
	private final String password = "behrooz";
	
	private PostgreSQLAdaptor(){}
	
	public boolean initialize()
	{
		initialized = false;
		try {
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            rSet = st.executeQuery("SELECT VERSION()");

            if (rSet.next()) {
                System.out.println(rSet.getString(1));
            }

        } catch (SQLException ex) {
            //Logger lgr = Logger.getLogger(Version.class.getName());
            System.out.println("SQLERRO:"+ex.getMessage());

        } finally {
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

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
		initialized = true;
		return true;
	}
	
	public static PostgreSQLAdaptor getInstance()
	{
		return m_instance;
	}
	
	public boolean connect()
	{
		if(!initialized)
			return false;
		
		
		return true;
	}
	
}
