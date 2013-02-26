/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * Executes SQL queries with pluggable strategies for handling
 * <code>ResultSet</code>s.  This class is thread safe.
 *
 * @see ResultSetHandler
 */
public class QueryRunner extends AbstractQueryRunner {

    /**
     * Constructor for QueryRunner.
     */
    public QueryRunner() {
        super();
    }

    /**
     * Constructor for QueryRunner that takes a <code>DataSource</code> to use.
     *
     * Methods that do not take a <code>Connection</code> parameter will retrieve connections from this
     * <code>DataSource</code>.
     *
     * @param ds The <code>DataSource</code> to retrieve connections from.
     */
    public QueryRunner(DataSource ds) {
        super(ds);
    }

    /**
     * Execute a batch of SQL INSERT, UPDATE, or DELETE queries.
     *
     * @param conn The Connection to use to run the query.  The caller is
     * responsible for closing this Connection.
     * @param sql The SQL to execute.
     * @param params An array of query replacement parameters.  Each row in
     * this array is one set of batch replacement values.
     * @return The number of rows updated per statement.
     * @throws SQLException if a database access error occurs
     * @since DbUtils 1.1
     */
    public int[] batch(Connection conn, String sql, Object[][] params) throws SQLException {
        return this.batch(conn, false, sql, params);
    }

    /**
     * Execute a batch of SQL INSERT, UPDATE, or DELETE queries.  The
     * <code>Connection</code> is retrieved from the <code>DataSource</code>
     * set in the constructor.  This <code>Connection</code> must be in
     * auto-commit mode or the update will not be saved.
     *
     * @param sql The SQL to execute.
     * @param params An array of query replacement parameters.  Each row in
     * this array is one set of batch replacement values.
     * @return The number of rows updated per statement.
     * @throws SQLException if a database access error occurs
     * @since DbUtils 1.1
     */
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection conn = this.prepareConnection();

        return this.batch(conn, true, sql, params);
    }

    /**
     * Calls update after checking the parameters to ensure nothing is null.
     * @param conn The connection to use for the batch call.
     * @param closeConn True if the connection should be closed, false otherwise.
     * @param sql The SQL statement to execute.
     * @param params An array of query replacement parameters.  Each row in
     * this array is one set of batch replacement values.
     * @return The number of rows updated in the batch.
     * @throws SQLException If there are database or parameter errors.
     */
    private int[] batch(Connection conn, boolean closeConn, String sql, Object[][] params) throws SQLException {
        throw new SQLException("Not yet implemented");
/*        
        if (conn == null) {
            throw new SQLException("Null connection");
        }

        if (sql == null) {
            if (closeConn) {
                close(conn);
            }
            throw new SQLException("Null SQL statement");
        }

        if (params == null) {
            if (closeConn) {
                close(conn);
            }
            throw new SQLException("Null parameters. If parameters aren't need, pass an empty array.");
        }

        PreparedStatement stmt = null;
        int[] rows = null;
        try {
            stmt = this.prepareStatement(conn, sql);

            for (int i = 0; i < params.length; i++) {
                this.fillStatement(stmt, params[i]);
                stmt.addBatch();
            }
            rows = stmt.executeBatch();

        } catch (SQLException e) {
            this.rethrow(e, sql, (Object[])params);
        } finally {
            close(stmt);
            if (closeConn) {
                close(conn);
            }
        }

        return rows;
*/
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.QueryExecutor} for the given SQL statement.
     * The <code>Connection</code> is retrieved from the
     * <code>DataSource</code> set in the constructor.
     * @param sql The SQL statement to execute.
     * @return An {@link org.apache.commons.dbutils.QueryExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public QueryExecutor query(String sql) throws SQLException {
        Connection conn = this.prepareConnection();

        return this.query(conn, true, sql);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.QueryExecutor} for the given SQL statement and connection.
     * The connection is <b>NOT</b> closed after execution.
     * @param conn The connection to use for the update call.
     * @param sql The SQL statement to execute.
     * @return An {@link org.apache.commons.dbutils.QueryExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public QueryExecutor query(Connection con, String sql) throws SQLException {
        final Connection conn = this.prepareConnection();

        return this.query(conn, true, sql);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.QueryExecutor} for the given SQL statement and connection.
     * @param conn The connection to use for the update call.
     * @param closeConn True if the connection should be closed, false otherwise.
     * @param sql The SQL statement to execute.
     * @return An {@link org.apache.commons.dbutils.QueryExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public QueryExecutor query(Connection conn, boolean closeConn, String sql) throws SQLException {
        if (conn == null) {
            throw new SQLException("Null connection");
        }

        if (sql == null) {
            if (closeConn) {
                close(conn);
            }
            throw new SQLException("Null SQL statement");
        }
        
        return new QueryExecutor(conn, sql, closeConn);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.UpdateExecutor} for the given SQL statement.
     * <code>Connection</code> is retrieved from the <code>DataSource</code>
     * set in the constructor.  This <code>Connection</code> must be in
     * auto-commit mode or the update will not be saved.
     *
     * @param sql The SQL statement to execute.
     * @return An {@link org.apache.commons.dbutils.UpdateExecutor} for this SQL statement.
     * @throws SQLException if a database access error occurs
     */
    public UpdateExecutor update(String sql) throws SQLException {
        Connection conn = this.prepareConnection();

        return this.update(conn, true, sql);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.UpdateExecutor} for the given SQL statement and connection.
     * The connection is <b>NOT</b> closed after execution.
     * @param conn The connection to use for the update call.
     * @param sql The SQL statement to execute.
     * @return An {@link org.apache.commons.dbutils.UpdateExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public UpdateExecutor update(Connection conn, String sql) throws SQLException {
        return this.update(conn, false, sql);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.UpdateExecutor} for the given SQL statement and connection.
     * @param conn The connection to use for the update call.
     * @param closeConn True if the connection should be closed, false otherwise.
     * @param sql The SQL statement to execute.
     * @return An {@link org.apache.commons.dbutils.UpdateExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public UpdateExecutor update(Connection conn, boolean closeConn, String sql) throws SQLException {
        if (conn == null) {
            throw new SQLException("Null connection");
        }

        if (sql == null) {
            if (closeConn) {
                close(conn);
            }
            throw new SQLException("Null SQL statement");
        }

        return new UpdateExecutor(conn, sql, closeConn);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.InsertExecutor} for the given SQL.
     * <code>Connection</code> is retrieved from the <code>DataSource</code>
     * set in the constructor.  This <code>Connection</code> must be in
     * auto-commit mode or the insert will not be saved.
     * @param conn The connection to use for the query call.
     * @param sql The SQL statement to execute.
     *
     * @return An {@link org.apache.commons.dbutils.InsertExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public InsertExecutor insert(String sql) throws SQLException {
        return insert(this.prepareConnection(), true, sql);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.InsertExecutor} for the given SQL and connection
     * The connection is <b>NOT</b> closed after execution.
     * @param conn The connection to use for the query call.
     * @param sql The SQL statement to execute.
     *
     * @return An {@link org.apache.commons.dbutils.InsertExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public InsertExecutor insert(Connection conn, String sql) throws SQLException {
        return insert(conn, false, sql);
    }

    /**
     * Creates an {@link org.apache.commons.dbutils.InsertExecutor} for the given SQL and connection.
     * @param conn The connection to use for the query call.
     * @param closeConn True if the connection should be closed, false otherwise.
     * @param sql The SQL statement to execute.
     *
     * @return An {@link org.apache.commons.dbutils.InsertExecutor} for this SQL statement.
     * @throws SQLException If there are database or parameter errors.
     */
    public InsertExecutor insert(Connection conn, boolean closeConn, String sql) throws SQLException {
        if (conn == null) {
            throw new SQLException("Null connection");
        }

        if (sql == null) {
            if (closeConn) {
                close(conn);
            }
            throw new SQLException("Null SQL statement");
        }
        
        return new InsertExecutor(conn, sql, closeConn);
    }
}
