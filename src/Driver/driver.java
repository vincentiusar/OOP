/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import Manager.Manager;
import Subordinate.Subordinate;
import subProject.subProject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.time.LocalDate;
import javax.swing.event.*;


/**
 *
 * @author Vincentius
 */
public class driver extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    DefaultListModel<String> listModel_1;
    DefaultListModel<String> listModel_2;
    ArrayList<Manager> ArrayListManager;
    ArrayList<Subordinate> ArrayListSubordinate;
    ArrayList<subProject> ArrayListsubProject;
    
    /**
     * Creates new form java
     */
    public driver() {
        initComponents();
    }

    private class selectHandler1 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String namaSelected = ListManager.getSelectedValue().toString();
                for (Manager M : ArrayListManager) {
                    if (namaSelected.equals(M.getNama())) {
                        showNamaLabel.setText(M.getNama());
                        showJabatanLabel.setText(M.getJabatan());
                        showDivisiLabel.setText(M.getHeadOf());
                        nama = M.getNama();
                        jabatan = M.getJabatan();
                        div = M.getHeadOf();
                    }
                }
            }
        }
    }
    
    private class selectHandler2 implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String namaSelected = ListPekerja.getSelectedValue().toString();
                for (Subordinate M : ArrayListSubordinate) {
                    if (namaSelected.equals(M.getNama())) {
                        showNamaLabel.setText(M.getNama());
                        showJabatanLabel.setText(M.getJabatan());
                        showDivisiLabel.setText(M.getDivisi());
                        nama = M.getNama();
                        jabatan = M.getJabatan();
                        div = M.getDivisi();
                    }
                }
            }
        }
    }
    
    static String nama, jabatan, div;
    
    private class handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            popupLayer mee;
            if (e.getSource() == editManagerButton || e.getSource() == editPekerjaButton) {
                mee = new popupLayer(nama, jabatan, div);
            } else {
                nama = ""; div = ""; jabatan = "";
                mee = new popupLayer(nama, jabatan, div);
            }
            mee.setVisible(true);
        }
    }
    
    public void loadDB() {
        listModel_1 = new DefaultListModel<>();
        listModel_2 = new DefaultListModel<>();
        ArrayListManager = new ArrayList<>();
        ArrayListSubordinate = new ArrayList<>();
        ArrayListsubProject = new ArrayList<>();
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            stmt = conn.createStatement();
  
            String st;
//            String st = "INSERT INTO manager (nama, jabatan, headof) VALUES (?, ?, ?);";
//            PreparedStatement ps = conn.prepareStatement(st);
//            ps.setString(1, "kamu");
//            ps.setString(2, "Manager");
//            ps.setString(3, "makan");
//            ps.execute();
//            st = "UPDATE manager SET nama = ? WHERE nama = ?";
//            PreparedStatement ps = conn.prepareStatement(st);
//            ps.setString(1, "aduh");
//            ps.setString(2, "aku adalah lelaki");
//            ps.execute();
            st = "SELECT * FROM manager";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                nama = rs.getString("nama");
                int id = rs.getInt("id");
                jabatan = rs.getString("jabatan");
                div = rs.getString("headof");
                ArrayListManager.add(new Manager(Integer.toString(id), nama, jabatan, div));
                listModel_1.addElement(nama);
            }
            st = "SELECT * FROM subordinate";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                nama = rs.getString("nama");
                int id = rs.getInt("id");
                jabatan = rs.getString("jabatan");
                div = rs.getString("divisi");
                ArrayListSubordinate.add(new Subordinate(Integer.toString(id), nama, jabatan, div));
                listModel_2.addElement(nama);
            }
            stmt.close();
            conn.close();
            ListManager.setModel(listModel_1);
            ListManager.addListSelectionListener(new selectHandler1());
            ListPekerja.setModel(listModel_2);
            ListPekerja.addListSelectionListener(new selectHandler2());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void launch() {
        newManagerButton.addActionListener(new handler());
        newPekerjaButton.addActionListener(new handler());
        editManagerButton.addActionListener(new handler());
        editPekerjaButton.addActionListener(new handler());
        loadDB();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        employeeTabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        ListManagerLabel = new javax.swing.JLabel();
        ListPekerjaLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListManager = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListPekerja = new javax.swing.JList<>();
        newPekerjaButton = new javax.swing.JButton();
        newManagerButton = new javax.swing.JButton();
        LabelNama = new javax.swing.JLabel();
        LabelJabatan = new javax.swing.JLabel();
        LabelDivisi = new javax.swing.JLabel();
        showNamaLabel = new javax.swing.JLabel();
        showJabatanLabel = new javax.swing.JLabel();
        showDivisiLabel = new javax.swing.JLabel();
        editManagerButton = new javax.swing.JButton();
        editPekerjaButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ListProjectLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        newProjectButton = new javax.swing.JButton();
        editProjectButton = new javax.swing.JButton();
        hapusProjectButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ListManagerLabel.setText("List Manager");

        ListPekerjaLabel.setText("List Pekerja");

        ListManager.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ListManager);

        ListPekerja.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(ListPekerja);

        newPekerjaButton.setText("New Pekerja");

        newManagerButton.setText("New Manager");

        LabelNama.setText("Nama");

        LabelJabatan.setText("Jabatan");

        LabelDivisi.setText("Divisi");

        editManagerButton.setText("Edit Manger");

        editPekerjaButton.setText("Edit Pekerja");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ListManagerLabel)
                    .addComponent(newManagerButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editManagerButton))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editPekerjaButton)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ListPekerjaLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNama)
                            .addComponent(LabelJabatan)
                            .addComponent(LabelDivisi)
                            .addComponent(showNamaLabel)
                            .addComponent(showJabatanLabel)
                            .addComponent(showDivisiLabel)))
                    .addComponent(newPekerjaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListManagerLabel)
                    .addComponent(ListPekerjaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelNama)
                        .addGap(11, 11, 11)
                        .addComponent(showNamaLabel)
                        .addGap(26, 26, 26)
                        .addComponent(LabelJabatan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(showJabatanLabel)
                        .addGap(32, 32, 32)
                        .addComponent(LabelDivisi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showDivisiLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPekerjaButton)
                    .addComponent(newManagerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editManagerButton)
                    .addComponent(editPekerjaButton))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        employeeTabs.addTab("Employee", jPanel1);

        ListProjectLabel.setText("List Project");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        newProjectButton.setText("New Project");

        editProjectButton.setText("EditProject");

        hapusProjectButton.setText("Hapus Project");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ListProjectLabel)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(newProjectButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editProjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(hapusProjectButton)))
                .addContainerGap(374, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ListProjectLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newProjectButton)
                    .addComponent(editProjectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hapusProjectButton)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        employeeTabs.addTab("Project", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        employeeTabs.addTab("tab3", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeeTabs)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeeTabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public static driver me;
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(driver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(driver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(driver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(driver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        me = new driver();
        me.setVisible(true);
        me.launch();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelDivisi;
    private javax.swing.JLabel LabelJabatan;
    private javax.swing.JLabel LabelNama;
    private javax.swing.JList<String> ListManager;
    private javax.swing.JLabel ListManagerLabel;
    private javax.swing.JList<String> ListPekerja;
    private javax.swing.JLabel ListPekerjaLabel;
    private javax.swing.JLabel ListProjectLabel;
    private javax.swing.JButton editManagerButton;
    private javax.swing.JButton editPekerjaButton;
    private javax.swing.JButton editProjectButton;
    private javax.swing.JTabbedPane employeeTabs;
    private javax.swing.JButton hapusProjectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton newManagerButton;
    private javax.swing.JButton newPekerjaButton;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JLabel showDivisiLabel;
    private javax.swing.JLabel showJabatanLabel;
    private javax.swing.JLabel showNamaLabel;
    // End of variables declaration//GEN-END:variables
}
