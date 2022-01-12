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
public class editEmployeeLayer extends javax.swing.JFrame {
    
    // Database properties
    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    /**
     * Creates new form managerPopLayer
     */
    
    boolean isNew = false;      // penanda apakah data baru atau data diedit
    
    private class handler implements ActionListener {
        
        /*
            I.S. digunakan untuk tombol "OK" ketika ditekan, akan diterima data
                 inputan user baik edit atau new.
            F.S. akan dilakukan pengeditan data jika tombol edit pada bagian sebelumnya
                 ditekan. akan dilakukan penambahan data jika tombol new ditekan.
                 data pada DB akan diubah.
        */
        
        public void actionPerformed(ActionEvent e) {
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
                stmt = conn.createStatement();

                now_nama = namaTextField.getText().trim();
                now_jabatan = "";
                if (radioManager.isSelected()) {
                    now_jabatan = "Manager";
                } else if (radioSubor.isSelected()) {
                    now_jabatan = "subordinate";
                }
                now_divisi = divTextField.getText().trim();
                
                int id_comp = 0;
                String st;
                st = "SELECT id_company FROM company WHERE nama = '" + companyCombo.getSelectedItem() + "'";
                rs = stmt.executeQuery(st);
                while (rs.next()) {
                    id_comp = rs.getInt("id_company");
                }
                
                if ("".equals(now_nama) || (!radioManager.isSelected() && !radioSubor.isSelected()) || "".equals(now_divisi)) {
                    errorLayer mee = new errorLayer();
                    mee.setVisible(true);
                    return;
                }
                
                if (isNew) {        // jika datanya baru, lakukan insert
                    if ("Manager".equals(now_jabatan)) {
                        st = "INSERT INTO manager (nama, jabatan, headof, id_company) VALUES (?, ?, ?, ?);";
                    } else {
                        st = "INSERT INTO subordinate (nama, jabatan, divisi, id_company) VALUES (?, ?, ?, ?);";
                    }
                    PreparedStatement ps = conn.prepareStatement(st);
                    ps.setString(1, now_nama);
                    ps.setString(2, now_jabatan);
                    ps.setString(3, now_divisi);
                    ps.setInt(4, id_comp);
                    
                    ps.execute();
                } else {            // selain itu, lakukan update
                    if (prev_jabatan.equals(now_jabatan)) {
                        if ("Manager".equals(now_jabatan)) {
                            st = "UPDATE " + now_jabatan + " SET nama = ?, headof = ?, id_company = ? WHERE nama = ?";
                        } else {
                            st = "UPDATE " + now_jabatan + " SET nama = ?, divisi = ?, id_company = ? WHERE nama = ?";
                        }
                        PreparedStatement ps = conn.prepareStatement(st);
                        ps.setString(1, now_nama);
                        ps.setString(2, now_divisi);
                        ps.setInt(3, id_comp);
                        ps.setString(4, prev_nama);
                        ps.execute();
                    } else {
                        st = "DELETE FROM " + prev_jabatan + " WHERE nama = ?";
                        PreparedStatement ps = conn.prepareStatement(st);
                        ps.setString(1, prev_nama);
                        ps.execute();
                        
                        if ("Manager".equals(now_jabatan)) {
                            st = "INSERT INTO manager (nama, jabatan, headof, id_company) VALUES (?, ?, ?, ?);";
                        } else {
                            st = "INSERT INTO subordinate (nama, jabatan, divisi, id_company) VALUES (?, ?, ?, ?);";
                        }
                        ps = conn.prepareStatement(st);
                        ps.setString(1, now_nama);
                        ps.setString(2, now_jabatan);
                        ps.setString(3, now_divisi);
                        ps.setInt(4, id_comp);
                        ps.execute();
                    }
                    stmt.close();
                    conn.close();
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
        
        /*
            I.S. handler untuk tombol cancel. user menekan cancel
            F.S. kembali ke layar utama. layar employee tertutup
        */
        
        public void actionPerformed(ActionEvent e) {
            me.loadDB();
            dispose();
        }
    }
       
    private void launch(String nama, String jabatan, String div, String company) {
        
        /*
            I.S. fungsi pertama yang berjalan sebagai initiatior
            F.S. ...
        */
        
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
        String st = "SELECT * FROM company";
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                companyCombo.addItem(rs.getString("nama"));
                if (rs.getString("nama").equals(company)) {
                    id_comp = rs.getInt("id_company");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        companyCombo.setSelectedItem(company);
        
    }
    
    String now_nama, now_jabatan, now_divisi;           // menampung nama employee setelah diubah, jabatan, dan divisinya
    String prev_nama, prev_jabatan, prev_divisi;        // menampung nama employee sebelum diubah, jabatan, dan divisinya
    String company; int id_comp;
    
    public editEmployeeLayer(String nama, String jabatan, String divisi, String company) {
        
        /*
            I.S. konstrutor layer
            F.S. ...
        */
        
        initComponents();
        if ("".equals(nama)) isNew = true;
        prev_nama = nama;
        prev_jabatan = jabatan;
        prev_divisi = divisi;
        this.company = company;
        launch(nama, jabatan, divisi, company);
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
        companyOfEmployeeLabel = new javax.swing.JLabel();
        companyCombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        namaLabel.setText("Nama");

        jabatanLabel.setText("Jabatan");

        buttonGroup1.add(radioManager);
        radioManager.setText("Manager");

        buttonGroup1.add(radioSubor);
        radioSubor.setText("Subordinate");

        showTextIf.setText("Divisi");

        OKButton.setText("OK");

        cancelButton.setText("Cancel");

        companyOfEmployeeLabel.setText("Company");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addGap(69, 69, 69)
                        .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jabatanLabel)
                            .addGap(47, 47, 47)
                            .addComponent(radioManager)
                            .addGap(18, 18, 18)
                            .addComponent(radioSubor))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaLabel)
                                    .addComponent(showTextIf, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(companyOfEmployeeLabel)))
                            .addGap(38, 38, 38)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(namaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addComponent(divTextField)
                                .addComponent(companyCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaLabel)
                    .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jabatanLabel)
                    .addComponent(radioManager)
                    .addComponent(radioSubor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(divTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTextIf))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(companyOfEmployeeLabel)
                    .addComponent(companyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(OKButton))
                .addContainerGap(63, Short.MAX_VALUE))
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
    private javax.swing.JComboBox<String> companyCombo;
    private javax.swing.JLabel companyOfEmployeeLabel;
    private javax.swing.JTextField divTextField;
    private javax.swing.JLabel jabatanLabel;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JRadioButton radioManager;
    private javax.swing.JRadioButton radioSubor;
    private javax.swing.JLabel showTextIf;
    // End of variables declaration//GEN-END:variables
}
