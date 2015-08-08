/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.sql;

import java.sql.*;

public class SQLOperations {

	protected synchronized int standardQuery(String query, Connection connection) throws SQLException{
		Statement statement = connection.createStatement();
		int rowsUpdated = statement.executeUpdate(query);
		statement.close();
		return rowsUpdated;
	}

	protected synchronized ResultSet sqlQuery(String query, Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
	}

	protected synchronized boolean checkTable(String table, Connection connection) throws SQLException {
		DatabaseMetaData dbm;
		dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, table, null);
		return tables.next();
	}

}
