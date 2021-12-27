package Driver;

import static Driver.driver.me;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vincentius
 */
public class popupLayer extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    /**
     * Creates new form managerPopLayer
     */
    
    boolean isNew = false;
    
    private class handler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            try {
                stmt = conn.createStatement();
                now_nama = namaTextField.getText().trim();
                now_jabatan = "";
                if (radioManager.isSelected()) {
                    now_jabatan = "Manager";
                } else if (radioSubor.isSelected()) {
                    now_jabatan = "subordinate";
                }
                now_divisi = divTextField.getText().trim();
                
                if (isNew) {
                    String st = "";
                    if ("Manager".equals(now_jabatan)) {
                        st = "INSERT INTO manager (nama, jabatan, headof) VALUES (?, ?, ?);";
                    } else {
                        st = "INSERT INTO subordinate (nama, jabatan, divisi) VALUES (?, ?, ?);";
                    }
                    PreparedStatement ps = conn.prepareStatement(st);
                    ps.setString(1, now_nama);
                    ps.setString(2, now_jabatan);
                    ps.setString(3, now_divisi);
                    ps.execute();
                } else {
                    if (prev_jabatan.equals(now_jabatan)) {
                        String st = "UPDATE ? SET nama = ?, divisi = ? WHERE nama = ?";
                        PreparedStatement ps = conn.prepareStatement(st);
                        ps.setString(1, now_jabatan);
                        ps.setString(2, now_nama);
                        ps.setString(3, now_divisi);
                        ps.setString(4, prev_nama); 
                        ps.execute();
                    } else {
                        String st = "DELETE FROM ? WHERE nama = '?'";
                        PreparedStatement ps = conn.prepareStatement(st);
                        ps.setString(1, prev_jabatan);
                        ps.setString(2, prev_nama);
                        ps.execute();
                        
                        if ("manager".equals(now_jabatan)) {
                            st = "INSERT INTO manager (nama, jabatan, headof) VALUES (?, ?, ?);";
                        } else {
                            st = "INSERT INTO subordinate (nama, jabatan, divisi) VALUES (?, ?, ?);";
                        }
                        ps = conn.prepareStatement(st);
                        ps.setString(1, now_nama);
                        ps.setString(2, now_jabatan);
                        ps.setString(3, now_divisi);
                        ps.execute();
                    }
                }
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
            dispose();
        }
    }
    
    private void launch(String nama, String jabatan, String div) {
        try {
            OKButton.addActionListener(new handler());
            cancelButton.addActionListener(new handler2());
            namaTextField.setText(nama);
            if (jabatan.equals("Manager")) {
                radioManager.setSelected(true);
                showTextIf.setText("Head Of");
            } else if (jabatan.equals("subordinate")) {
                radioSubor.setSelected(true);
                showTextIf.setText("Divisi");
            }
            divTextField.setText(div);
            
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    String now_nama, now_jabatan, now_divisi;
    String prev_nama, prev_jabatan, prev_divisi;
    
    public popupLayer(String nama, String jabatan, String divisi) {
        initComponents();
        if ("".equals(nama)) isNew = true;
        prev_nama = nama;
        prev_jabatan = jabatan;
        prev_divisi = divisi;
        launch(nama, jabatan, divisi);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        namaLabel = new javax.swing.JLabel();
        jabatanLabel = new javax.swing.JLabel();
        radioManager = new javax.swing.JRadioButton();
        radioSubor = new javax.swing.JRadioButton();
        namaTextField = new javax.swing.JTextField();
        showTextIf = new javax.swing.JLabel();
        divTextField = new javax.swing.JTextField();
        OKButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        namaLabel.setText("Nama");

        jabatanLabel.setText("Jabatan");

        buttonGroup1.add(radioManager);
        radioManager.setText("Manager");

        buttonGroup1.add(radioSubor);
        radioSubor.setText("Subordinate");

        OKButton.setText("OK");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jabatanLabel)
                            .addComponent(namaLabel)
                            .addComponent(showTextIf))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(radioManager)
                                .addGap(18, 18, 18)
                                .addComponent(radioSubor))
                            .addComponent(namaTextField)
                            .addComponent(divTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addGap(69, 69, 69)
                        .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaLabel)
                    .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jabatanLabel)
                    .addComponent(radioManager)
                    .addComponent(radioSubor))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(showTextIf))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(divTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(OKButton))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField divTextField;
    private javax.swing.JLabel jabatanLabel;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JRadioButton radioManager;
    private javax.swing.JRadioButton radioSubor;
    private javax.swing.JLabel showTextIf;
    // End of variables declaration//GEN-END:variables
}
