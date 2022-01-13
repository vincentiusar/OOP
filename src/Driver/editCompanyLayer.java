/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import static Driver.driver.me;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vincentius
 */
public class editCompanyLayer extends javax.swing.JFrame {

    /**
     * Creates new form editCompanyLayer
     */
    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    String nama;
    boolean isNew = false;
    
    private class handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                stmt = conn.createStatement();
                
                String st;
                if (isNew) {
                    st = "INSERT INTO company (nama) VALUE (?)";
                } else {
                    st = "UPDATE company SET nama = ? WHERE nama = ?";
                }
                PreparedStatement ps = conn.prepareStatement(st);
                if (isNew) ps.setString(1, namaCompanyField.getText().trim());
                else {
                    ps.setString(1, namaCompanyField.getText().trim());
                    ps.setString(2, nama);
                }
                ps.execute();
                                
                stmt.close();
                conn.close();
            } catch (Exception a) {
                a.printStackTrace();
            } finally {
                me.loadDB();
                SwingUtilities.updateComponentTreeUI(me);
                dispose();
            }
        }
    }
    
    private class handler2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            me.loadDB();
            dispose();
        }
    }
    
    void launch(String nama_comp) {
        if ("".equals(nama_comp)) isNew = true;
        this.nama = nama_comp;
        namaCompanyField.setText(nama);
        OKButton.addActionListener(new handler());
        CancelButton.addActionListener(new handler2());
    }
    
    public editCompanyLayer(String nama_comp) {
        initComponents();
        launch(nama_comp);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        namaCompanyLabel = new javax.swing.JLabel();
        namaCompanyField = new javax.swing.JTextField();
        OKButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        namaCompanyLabel.setText("Nama Company");

        OKButton.setText("OK");

        CancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(namaCompanyLabel)
                .addGap(26, 26, 26)
                .addComponent(namaCompanyField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaCompanyLabel)
                    .addComponent(namaCompanyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKButton)
                    .addComponent(CancelButton))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton OKButton;
    private javax.swing.JTextField namaCompanyField;
    private javax.swing.JLabel namaCompanyLabel;
    // End of variables declaration//GEN-END:variables
}