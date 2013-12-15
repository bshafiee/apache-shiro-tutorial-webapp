package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UsersModel implements DataModel{
	private static final String tableName = "users";
	//Data Fields
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private Date	created_on;
	private Date	last_login;
	
	public UsersModel() {this.reset();}
	
	public UsersModel(String username, String firstname, String lastname,
			String email) {
		super();
		this.reset();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	private void reset()
	{
		this.username = null;
		this.firstname = null;
		this.lastname = null;
		this.email = null;
		this.id = -1;
		this.created_on = null;
		this.last_login = null;
	}

	public boolean createTable() {
		String query = "CREATE TABLE IF NOT EXISTS"+ tableName +"(id serial PRIMARY KEY,"
				+ "username VARCHAR (50) UNIQUE NOT NULL,firstname VARCHAR (50),lastname VARCHAR (50),"
				+ "email VARCHAR (355) UNIQUE NOT NULL, created_on TIMESTAMP NOT NULL, "
				+ "last_login TIMESTAMP);";
		try {
			return (PostgreSQLAdaptor.getInstance().executeUpdate(query)>=0) ? true:false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean insert() {
		this.created_on = new java.util.Date();
		String query = "INSERT INTO "+ tableName +" (username, firstname,lastname,"
				+ "email, created_on)"
				+" VALUES ('"+username+"','"+firstname+"','"+lastname+"','"+email+"','"+this.created_on+"') RETURNING id;";
		try {
			ResultSet rSet = PostgreSQLAdaptor.getInstance().executeQuery(query);
			if(rSet!=null && rSet.next())
			{
				this.id = rSet.getInt(1);
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean remove() {
		String query = "DELETE FROM "+ tableName +" WHERE id = "+this.id+";";
		try {
			if (PostgreSQLAdaptor.getInstance().executeUpdate(query)>0)
			{
				this.reset();
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean fromDatabase(int id) {
		this.id = id;
		String query = "SELECT * FROM "+ tableName +" WHERE id = "+id+";";
		try {
			ResultSet rSet = PostgreSQLAdaptor.getInstance().executeQuery(query);
			if(rSet!=null && rSet.next())
			{
				this.id = rSet.getInt(1);
				this.username = rSet.getString(2);
				this.firstname = rSet.getString(3);
				this.lastname = rSet.getString(4);
				this.email = rSet.getString(5);
				this.created_on = rSet.getDate(6);
				this.last_login = rSet.getDate(7);
				
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "{id:"+this.id+", username:"+this.username+", firstname:"+this.firstname+", lastname:"+this.lastname+
				",email:"+this.email+", createdOn:"+this.created_on+", lastLogin:"+this.last_login+"}";
	}
}
