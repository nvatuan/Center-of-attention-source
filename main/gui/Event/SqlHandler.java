package main.gui.Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Blob;
import java.sql.PreparedStatement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import main.database.Util;
import main.gui.Controller.Frame;
import main.algo.ImageCentralPixels;

// This class handles event when a button in the History Dialog is pressed
public class SqlHandler implements ActionListener {
    // ==== Fields
    private Frame src;
    public JTable srcTable;
    // ==== Constructor
    public SqlHandler(Frame obj, JTable objTable) { this.src = obj; this.srcTable = objTable; }
    // ==== Overriden method
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                // Button is "Clone Input"
                case "Clone":
                    Clone();
                    break;
                // Button is "Save to Database"
                case "Save":
                    Save();
                    Show();
                    break;
                // Button is "Delete"
                case "Delete":
                    Delete();
                    Show();
                    break;
                // Default behaviour
                default:
                    Show();
                    break;
            }
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi đang thao tác với MySQL DB. Thông tin thêm:\n[" + sqlex.getMessage() + "]", "ERROR: Kết nối với MySQL DB bị ngắt", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
        }
    }

    // ==== methods
    // DELETE statement
    public void Delete() throws SQLException {
        int[] selection = srcTable.getSelectedRows();
        if (selection.length == 0) {
        } else {
            int sel = ((int) srcTable.getValueAt(selection[0], 0));
            String query = "DELETE FROM tb\n" + "WHERE id = " + sel + ";";
            try {
                Statement sm = Util.getConnectionDefault().createStatement();
                sm.execute(query);
                System.out.println("DELETE succeeded.");
            } catch (SQLException sqlex) {
                System.out.println("DELETE failed.");
                throw new SQLException(sqlex);
            } catch (Exception e) {
                System.out.println("DELETE failed.");
                e.printStackTrace();
            }
        }
    }

    // SELECT statement and parse the Blob object
    public void Clone() throws SQLException {
        int[] selection = srcTable.getSelectedRows();
        if (selection.length == 0) {
        } else {
            int sel = ((int) srcTable.getValueAt(selection[0], 0));
            String query = "SELECT serial FROM tb\n" + "WHERE id = " + sel + ";";
            
            try {
                Statement sm = Util.getConnectionDefault().createStatement();
                ResultSet rs = sm.executeQuery(query);

                if (rs.first()) {
                    byte[] bytes = (byte[]) rs.getBytes(1);
                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                    ObjectInputStream ois = new ObjectInputStream(bais);

                    ImageCentralPixels img = (ImageCentralPixels) ois.readObject();

                    src.taInput.setText(img.toString());
                }
            } catch (SQLException sqlex) {
                throw new SQLException(sqlex);
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    // Write object to a Blob object and INSERT statement
    public void Save() throws SQLException {
        try {
            Connection conn = Util.getConnectionDefault();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            byte[] bytes = null;
                
            ImageCentralPixels img = ImageCentralPixels.parseImageCP(src.taInput.getText());

            out = new ObjectOutputStream(bos);
            out.writeObject(img);
            out.flush();
            bytes = bos.toByteArray();

            Blob bl = conn.createBlob();
            bl.setBytes(1, bytes);

            PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO javabase.tb (serial, created) VALUES (?, NOW());"
            );

            pst.setBlob(1, bl);
            pst.executeUpdate();
            pst.close();
        } catch (Exception ex) {
            System.out.println("SAVE CURRENT failed.");
            ex.printStackTrace();
        }
    }

    // a SELECT statement and a table model update
    public void Show() throws SQLException {
        try {
            Statement st = Util.getConnectionDefault().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tb;");
            srcTable.setModel(Util.buildTableModel(rs));
            srcTable.revalidate();
        } catch (SQLException sqlex) {
            throw new SQLException(sqlex);
        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }
}