/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.api.sql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Code mostly stolen (*ahem* sorry, utilized) from Sync.
 */
public class LegendSQL extends SQLOperations {

    private enum Type {

        SQLITE,
        MYSQL

    }

    private String host, database, username, password;
    private Type schema;
    private Connection connection;
    private File databaseFile;

    /**
     * Create a new MySQL database connection
     * @param host The host to connect to (commonly 127.0.0.1 or localhost)
     * @param database The database to connect to
     * @param username The username to utilise
     * @param password The password to connect with
     */
    public LegendSQL(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.schema = Type.MYSQL;
    }

    /**
     * Create a new SQLite database connection
     * @param databaseFile The database file to use
     */
    public LegendSQL(File databaseFile) {
        this.databaseFile = databaseFile;
        this.schema = Type.SQLITE;
    }

    /**
     * Reopens the SQL connection if it is closed. This is invoked upon every query.
     */
    public void refreshConnection() {
        if(connection == null)
            initialise();
    }

    /**
     * Manually close the SQL connection.
     */
    public void closeConnection() {
        try {
            this.connection.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initialise a new connection. This will automatically create the database
     * file if you are using SQLite and it doesn't already exist.
     *
     * @return If the connection succeeded
     */
    public boolean initialise() {
        if(schema == Type.MYSQL)
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, username, password);
                return true;
            } catch(ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        else {
            if(!databaseFile.exists()) {
                try {
                    databaseFile.createNewFile();
                } catch(IOException ex) {
                    ex.printStackTrace();
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
     * Any query which does not return a ResultSet object. Such as <code>INSERT</code>,
     * <code>UPDATE</code>, <code>CREATE TABLE</code>, etc...
     *
     * @param query SQL query to run
     */
    public void standardQuery(String query) throws SQLException {
        this.refreshConnection();
        super.standardQuery(query, this.connection);
    }

    /**
     * Check whether a field/entry exists in a database.
     * @param query SQL query to run
     * @return Whether or not a result has been found in the query.
     */
    public boolean existanceQuery(String query) throws SQLException {
        this.refreshConnection();
        return super.sqlQuery(query, this.connection).next();
    }

    /**
     * Any query which returns a ResultSet object, such as <code>SELECT</code>. Remember to
     * close the ResultSet object after you are done with it to free up
     * resources immediately.<br><br>
     *
     * Example usage:<br><br>
     * <code>ResultSet set = sqlQuery("SELECT * FROM sometable;");<br>
     * //do stuff<br>
     * set.close();</code>
     *
     * @param query SQL query to run
     * @return ResultSet returned by the SQL query
     */
    public ResultSet sqlQuery(String query) throws SQLException {
        this.refreshConnection();
        return super.sqlQuery(query, this.connection);
    }

    /**
     * Check whether the table name exists.
     * @param table The table to check for
     * @return If the table exists
     */
    public boolean doesTableExist(String table) throws SQLException {
        this.refreshConnection();
        return super.checkTable(table, this.connection);
    }

}
