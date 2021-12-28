/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Driver;

import Customer.Customer;
import Manager.Manager;
import Project.Project;
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
    static Statement stmt, stmt2;
    static ResultSet rs, rs2;
    
    DefaultListModel<String> listModel_1;
    DefaultListModel<String> listModel_2;
    DefaultListModel<String> listModel_3;
    DefaultListModel<String> listModel_4;
    ArrayList<Customer> ArrayListCustomer;
    ArrayList<Manager> ArrayListManager;
    ArrayList<Subordinate> ArrayListSubordinate;
    ArrayList<Project> ArrayListProject;
    ArrayList<subProject> ArrayListSubProject;
    
    /**
     * Creates new form java
     */
    public driver() {
        initComponents();
    }

    // null pointer terjadi ketika data didelete sehingga pointer yang menunjuk
    // data yang didelete akan null. Tidak ada kesalahan pada program
    
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
    
    // null pointer terjadi ketika data didelete sehingga pointer yang menunjuk
    // data yang didelete akan null. Tidak ada kesalahan pada program
    
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
    
    private class selectHandlerProject implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                String namaSelected = ListProject.getSelectedValue().toString();
                for (Project P : ArrayListProject) {
                    if (namaSelected.equals(P.getNama())) {
                        showNamaProject.setText(P.getNama());
                        showTanggalMulai.setText(P.getDateStart().toString());
                        showTanggalAkhir.setText(P.getDateEnd().toString());
                        showManager.setText(P.getManager().getNama());
                    }
                }
            }
        }
    }
    
    static String nama, jabatan, div, nama_cus;
    static LocalDate timeStart, timeEnd;
    static String worker, subProject;
    static Manager manager;
    
    private class handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            editEmployeeLayer mee;
            if (e.getSource() == editManagerButton || e.getSource() == editPekerjaButton) {
                mee = new editEmployeeLayer(nama, jabatan, div);
            } else {
                nama = ""; div = ""; jabatan = "";
                mee = new editEmployeeLayer(nama, jabatan, div);
            }
            mee.setVisible(true);
            listModel_1.clear();
            listModel_2.clear();
            
            ArrayListManager.clear();
            ArrayListSubordinate.clear();
            ArrayListProject.clear();
        }
    }
    
    private class deleteHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deleteEmployee mee;
            mee = new deleteEmployee(nama, jabatan, div);
            mee.setVisible(true);
            listModel_1.clear();
            listModel_2.clear();
            
            ArrayListManager.clear();
            ArrayListSubordinate.clear();
            ArrayListProject.clear();
        }
    }
    
    private ArrayList<String> toArrayString(String target) {
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            String result = "";
            if (target.charAt(i) == '\"' && "".equals(result)) {
                i++;
                result += target.charAt(i);
            } else if (target.charAt(i) == '\"' && !"".equals(result)) {
                res.add(result);
                result = "";
            }
        }
        return res;
    }
    
    public void loadDB() {
        listModel_1 = new DefaultListModel<>();
        listModel_2 = new DefaultListModel<>();
        listModel_3 = new DefaultListModel<>();
        ArrayListManager = new ArrayList<>();
        ArrayListSubordinate = new ArrayList<>();
        ArrayListProject = new ArrayList<>();
        ArrayListSubProject = new ArrayList<>();
        ArrayListCustomer = new ArrayList<>();
        
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            stmt = conn.createStatement();
            
            // load manager
            String st;
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
            
            // load subordinate
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
            
            int id = 0;
            
            st = "SELECT * FROM customer";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                int id_cus = rs.getInt("id_cus");
                nama_cus = rs.getString("nama");
                int id_proj = rs.getInt("id_project");
                ArrayListCustomer.add(new Customer(Integer.toString(id_cus), nama_cus, Integer.toString(id_proj)));
            }
            
            st = "SELECT * FROM subproject";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                id = rs.getInt("id");
                nama = rs.getString("nama");
                boolean isDone = rs.getBoolean("isDone");
                int id_proj = rs.getInt("id_project");
                ArrayListSubProject.add(new subProject(Integer.toString(id), nama, isDone, Integer.toString(id_proj)));
            }
            
            ArrayList<Subordinate> subor = new ArrayList<>();
            ArrayList<subProject> sub = new ArrayList<>();
            
            st = "SELECT * FROM project";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                id = rs.getInt("id_project");

                nama = rs.getString("nama");
                timeStart = rs.getDate("timeStart").toLocalDate();
                timeEnd = rs.getDate("timeEnd").toLocalDate();
                System.out.println(nama);
                String tmp = rs.getString("manager");
                
                for (Manager m : ArrayListManager) {
                    if (tmp.equals(m.getNama())) {
                        manager = m;
                        break;
                    }
                }
                
                worker = rs.getString("worker");
                ArrayList<String> w = toArrayString(worker);
                
                for (String str : w) {
                    for (Subordinate s : ArrayListSubordinate) {
                        if (str.equals(s.getNama())) {
                            subor.add(s);
                        }
                    }
                }

                String subproject = rs.getString("subproject");
                ArrayList<String> s = toArrayString(subproject);
                for (String str : s) {
                    for (subProject S : ArrayListSubProject) {
                        if (str.equals(S.getNamaSub())) {
                            sub.add(S);
                        }
                    }
                }
                
                String id_cus = "", nama_cus = "";
                
                for (Customer c : ArrayListCustomer) {
                    if (Integer.toString(id).equals(c.getId_proj())) {
                        id_cus = c.getId();
                        nama_cus = c.getName();
                    }
                }
                ArrayListProject.add(new Project(id_cus, nama_cus, Integer.toString(id), nama, timeStart, timeEnd, manager, subor, sub));
                listModel_3.addElement(nama);
            }
            
            stmt.close();
            conn.close();
            
            ListManager.setModel(listModel_1);
            ListPekerja.setModel(listModel_2);
            ListProject.setModel(listModel_3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void launch() {
        ListManager.addListSelectionListener(new selectHandler1());
        ListPekerja.addListSelectionListener(new selectHandler2());
        ListProject.addListSelectionListener(new selectHandlerProject());
        newManagerButton.addActionListener(new handler());
        newPekerjaButton.addActionListener(new handler());
        editManagerButton.addActionListener(new handler());
        editPekerjaButton.addActionListener(new handler());
        hapusDataButton.addActionListener(new deleteHandler());
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
        hapusDataButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        ListProjectLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ListProject = new javax.swing.JList<>();
        newProjectButton = new javax.swing.JButton();
        editProjectButton = new javax.swing.JButton();
        hapusProjectButton = new javax.swing.JButton();
        namaProjectLabel = new javax.swing.JLabel();
        tanggalMulaiLabel = new javax.swing.JLabel();
        deadlineLabel = new javax.swing.JLabel();
        pekerjaLabel = new javax.swing.JLabel();
        managerLabel = new javax.swing.JLabel();
        subProjectLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        showNamaProject = new javax.swing.JLabel();
        showTanggalMulai = new javax.swing.JLabel();
        showTanggalAkhir = new javax.swing.JLabel();
        showManager = new javax.swing.JLabel();
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

        hapusDataButton.setText("Hapus Data");

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
                    .addComponent(newPekerjaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ListPekerjaLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editPekerjaButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hapusDataButton)
                            .addComponent(LabelNama)
                            .addComponent(LabelJabatan)
                            .addComponent(LabelDivisi)
                            .addComponent(showNamaLabel)
                            .addComponent(showJabatanLabel)
                            .addComponent(showDivisiLabel))))
                .addContainerGap(86, Short.MAX_VALUE))
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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editPekerjaButton)
                        .addComponent(hapusDataButton)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        employeeTabs.addTab("Employee", jPanel1);

        ListProjectLabel.setText("List Project");

        ListProject.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(ListProject);

        newProjectButton.setText("New Project");

        editProjectButton.setText("Edit Project");

        hapusProjectButton.setText("Hapus Project");

        namaProjectLabel.setText("Nama Project");

        tanggalMulaiLabel.setText("Tanggal Mulai");

        deadlineLabel.setText("Deadline");

        pekerjaLabel.setText("Pekerja");

        managerLabel.setText("Manager");

        subProjectLabel.setText("SubProject");

        statusLabel.setText("Status");

        showNamaProject.setText("null");

        showTanggalMulai.setText("null");

        showTanggalAkhir.setText("null");

        showManager.setText("null");

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
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pekerjaLabel)
                    .addComponent(managerLabel)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namaProjectLabel)
                            .addComponent(showTanggalAkhir))
                        .addGap(100, 100, 100)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusLabel)
                            .addComponent(subProjectLabel)))
                    .addComponent(showNamaProject)
                    .addComponent(showTanggalMulai)
                    .addComponent(showManager)
                    .addComponent(tanggalMulaiLabel)
                    .addComponent(deadlineLabel))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListProjectLabel)
                    .addComponent(namaProjectLabel)
                    .addComponent(subProjectLabel))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(newProjectButton)
                            .addComponent(editProjectButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapusProjectButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(showNamaProject)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tanggalMulaiLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showTanggalMulai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deadlineLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusLabel)
                            .addComponent(showTanggalAkhir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(managerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showManager)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pekerjaLabel)))
                .addContainerGap(21, Short.MAX_VALUE))
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
            .addGap(0, 347, Short.MAX_VALUE)
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
    private javax.swing.JList<String> ListProject;
    private javax.swing.JLabel ListProjectLabel;
    private javax.swing.JLabel deadlineLabel;
    private javax.swing.JButton editManagerButton;
    private javax.swing.JButton editPekerjaButton;
    private javax.swing.JButton editProjectButton;
    private javax.swing.JTabbedPane employeeTabs;
    private javax.swing.JButton hapusDataButton;
    private javax.swing.JButton hapusProjectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel managerLabel;
    private javax.swing.JLabel namaProjectLabel;
    private javax.swing.JButton newManagerButton;
    private javax.swing.JButton newPekerjaButton;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JLabel pekerjaLabel;
    private javax.swing.JLabel showDivisiLabel;
    private javax.swing.JLabel showJabatanLabel;
    private javax.swing.JLabel showManager;
    private javax.swing.JLabel showNamaLabel;
    private javax.swing.JLabel showNamaProject;
    private javax.swing.JLabel showTanggalAkhir;
    private javax.swing.JLabel showTanggalMulai;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel subProjectLabel;
    private javax.swing.JLabel tanggalMulaiLabel;
    // End of variables declaration//GEN-END:variables
}
