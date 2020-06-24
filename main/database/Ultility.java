package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Ultility {
    public static Connection getDefaultConnection() {
        String url = "jdbc:mysql://localhost:3306/javabase";
        String username = "nvat";
        String password = "nvat";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("@main.database.Conn: Success!");
            return connection;
        }
        catch (SQLException e) {
            System.out.println("@main.database.Conn: Error!");
            e.printStackTrace();
            return null;
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

    //public static void main(String args[]) {
    //    Ultility.getDefaultConnection();
    //}
}