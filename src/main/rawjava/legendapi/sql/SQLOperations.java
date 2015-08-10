/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.sql;

import java.sql.*;

public class SQLOperations {

	protected synchronized int standardQuery(String query, Connection connection) throws SQLException {
		Statement statement = null;
		int rowsUpdated = 0;
		try {
			statement = connection.createStatement();
			rowsUpdated = statement.executeUpdate(query);
		} catch(Exception ex) {

		} finally {
			if(statement != null)
				statement.close();
		}
		return rowsUpdated;
	}

	protected synchronized ResultSet sqlQuery(String query, Connection connection) throws SQLException {
		return connection.createStatement().executeQuery(query);
	}

	protected synchronized boolean checkTable(String table, Connection connection) throws SQLException {
		DatabaseMetaData dbm;
		dbm = connection.getMetaData();
		ResultSet tables = dbm.getTables(null, null, table, null);
		return tables.next();
	}

}
