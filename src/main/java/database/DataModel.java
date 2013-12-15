package database;

public interface DataModel {
	public boolean createTable();
	public boolean insert();
	public boolean remove();
	public boolean update();
	public boolean fromDatabase(int id);
	public String toString();
}
