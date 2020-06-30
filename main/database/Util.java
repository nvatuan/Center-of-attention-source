package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

// This class contains static methods that help get Connection to MySQL Database
// Also contains a ResultSet to DefaultDataTable converter
public class Util {
    // This method will try to get `Connection` to MySQL DB with default args
    // database = 'javabase', username = 'root', password = 'root'
    public static Connection getConnectionDefault() throws SQLException {
        return getConnectionWithParams("javabase", "root", "root");
    }
    
    // This method accepts 3 Strings: database, user, pass
    // Will attempt to get Connection using provided credentials
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

    // This is a converter from ResultSet to DefaultTableModel
    // Helps pour data from DB to a JTable
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