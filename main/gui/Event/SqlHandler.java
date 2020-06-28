package main.gui.Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.PreparedStatement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import main.database.Ulti;
import main.gui.Controller.Frame;
import main.algo.ImageCentralPixels;

public class SqlHandler implements ActionListener {
    private Frame src;
    public JTable srcTable;
    public SqlHandler(Frame obj, JTable objTable) { this.src = obj; this.srcTable = objTable; }
    //
    @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Clone") {
            Clone();
            //dispose();
            //Show();
			return;
		}
		if (e.getActionCommand() == "Save") {
			Save();
			Show();
			return;
		}
		if (e.getActionCommand() == "Delete") {
			Delete();
			Show();
			return;
		}
        Show();
    }

    // --
    public void Delete() {
        int[] selection = srcTable.getSelectedRows();
        if (selection.length == 0) {
        } else {
            int sel = ((int) srcTable.getValueAt(selection[0], 0));
            String query = "DELETE FROM tb\n" + "WHERE id = " + sel + ";";
            try {
                Statement sm = Ulti.getConnectionDefault().createStatement();
                sm.execute(query);
                System.out.println("DELETE succeeded.");
            } catch (Exception e) {
                System.out.println("DELETE failed.");
                e.printStackTrace();
            }
        }
    }

    public void Clone() {
        int[] selection = srcTable.getSelectedRows();
        if (selection.length == 0) {
        } else {
            int sel = ((int) srcTable.getValueAt(selection[0], 0));
            String query = "SELECT serial FROM tb\n" + "WHERE id = " + sel + ";";
            
            try {
                Statement sm = Ulti.getConnectionDefault().createStatement();
                ResultSet rs = sm.executeQuery(query);

                if (rs.first()) {
                    byte[] bytes = (byte[]) rs.getBytes(1);
                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                    ObjectInputStream ois = new ObjectInputStream(bais);

                    //OutputStream os = new FileOutputStream("TestClone.txt");
                    //os.write(bytes);

                    ImageCentralPixels img = (ImageCentralPixels) ois.readObject();
                    System.out.println(img);

                    src.taInput.setText(img.toString());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    public void Save() {
        try {
            Connection conn = Ulti.getConnectionDefault();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            byte[] bytes = null;
                
            ImageCentralPixels img = ImageCentralPixels.parseImageCP(src.taInput.getText());

            out = new ObjectOutputStream(bos);
            out.writeObject(img);
            out.flush();
            bytes = bos.toByteArray();
                    
            //OutputStream os = new FileOutputStream("TestSave.txt");
            //os.write(bytes);

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

    public void Show() {
        try {
            Statement st = Ulti.getConnectionDefault().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tb;");
            srcTable.setModel(Ulti.buildTableModel(rs));
        } catch (Exception exx) {
            exx.printStackTrace();
        }
    }
}