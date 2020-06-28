package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Util {
    public static Connection getConnectionDefault() throws SQLException {
        return getConnectionWithParams("javabase", "root", "root");
    }
    
    public static Connection getConnectionWithParams(String database, String user, String pass) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = user;
        String password = pass;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("@main.database.Ulti: Connect success!");
            return connection;
        }
        catch (SQLException e) {
            System.out.println("@main.database.Ulti: Connect error!");
            //e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}