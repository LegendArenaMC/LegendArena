package net.thenamedev.legendapi.sql;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/**
 * SQL base code obviously taken from Sync.
 */
public class SyncSQL extends SQLOperations {

	private String host, database, username, password;
	private SCHEMA schema;
	private Connection connection;
	private File databaseFile;

	public SyncSQL(String host, String database, String username, String password) {
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
		this.schema = SCHEMA.MySQL;
	}

	public SyncSQL(File databaseFile) {
		this.databaseFile = databaseFile;
		this.schema = SCHEMA.SQLite;
	}

	/**
	 * Reopens the SQL connection if it is closed. This is invoked upon every
	 * query.
	 */
	public void refreshConnection() {
		if (connection == null) {
			initialise();
		}
	}

	/**
	 * Manually close the connection.
	 */
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialise a new connection. This will automatically create the database
	 * file if you are using SQLite and it doesn't already exist.
	 * 
	 * @return If the connection succeeded
	 */
	public boolean initialise() {
		if (schema == SCHEMA.MySQL) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
				return true;
			} catch (ClassNotFoundException | SQLException ex) {
				ex.printStackTrace();
			}
        } else {
			if (!databaseFile.exists()) {
				try {
                    //noinspection ResultOfMethodCallIgnored
                    databaseFile.createNewFile();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Class.forName("org.sqlite.JDBC");
				connection = DriverManager.getConnection("jdbc:sqlite:" + this.databaseFile.getAbsolutePath());
				return true;
			} catch(SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
        }
		return false;
	}

	/**
	 * Any query which does not return a ResultSet object. Such as : INSERT,
	 * UPDATE, CREATE TABLE...
	 * 
	 * @param query Query to run
	 */
	public void standardQuery(String query) throws SQLException {
		this.refreshConnection();
		super.standardQuery(query, this.connection);
	}

	/**
	 * Check whether a field/entry exists in a database.
	 * @param query Query to run
	 * @return Whether or not a result has been found in the query.
	 * @throws SQLException
	 */
	public boolean existanceQuery(String query) throws SQLException {
		this.refreshConnection();
		return super.sqlQuery(query, this.connection).next();
	}

	/**
	 * Any query which returns a ResultSet object. Such as : SELECT Remember to
	 * close the ResultSet object after you are done with it to free up
	 * resources immediately. ----- ResultSet set =
	 * sqlQuery("SELECT * FROM sometable;"); set.doSomething(); set.close();
	 * -----
	 * 
	 * @param query Query to run
	 * @return ResultSet
	 */
	public ResultSet sqlQuery(String query) throws SQLException {
		this.refreshConnection();
		return super.sqlQuery(query, this.connection);
	}

	/**
	 * Check whether the table name exists.
	 * 
	 * @param table The table to check for
	 * @return If the table exists
	 */
	public boolean doesTableExist(String table) throws SQLException {
		this.refreshConnection();
		return super.checkTable(table, this.connection);
	}

}
