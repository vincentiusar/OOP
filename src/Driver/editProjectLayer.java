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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vincentius
 */
public class editProjectLayer extends javax.swing.JFrame {

    boolean isNew = false;
    
    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    /**
     * Creates new form newProjectLayer
     */
    
    String prev_nama = "";
    String nama_cus = "";
    
    private class handler implements ActionListener {
        
        
        
        public void actionPerformed(ActionEvent e) {
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
                stmt = conn.createStatement();
                String st;
                if (isNew) {
                    st = "INSERT INTO project (nama, timeStart, timeEnd, manager, worker, subProject) VALUES (?, ?, ?, ?, ?, '[]')";
                } else {
                    st = "UPDATE project SET nama = ?, timeStart = ?, timeEnd = ?, manager = ?, worker = ? WHERE nama = ?";
                }
                
                if ("".equals(namaProjectField.getText().trim()) || "".equals(managerCombo.getSelectedItem().toString()) || dateStartSpinner.getValue() == null || dateEndSpinner.getValue() == null) {
                    errorLayer mee = new errorLayer();
                    mee.setVisible(true);
                    return;
                }
                
                String worker = "[";
                ArrayList<String> tmp = new ArrayList<>();
                if (!"-------".equals(subordinateCombo1.getSelectedItem().toString())) {
                    worker += "\"" + subordinateCombo1.getSelectedItem().toString() + "\"";
                    tmp.add(subordinateCombo1.getSelectedItem().toString());
                }

                if (!"-------".equals(subordinateCombo2.getSelectedItem().toString()) && !tmp.contains(subordinateCombo2.getSelectedItem().toString())) {
                    worker += ", \"" + subordinateCombo2.getSelectedItem().toString() + "\"";
                    tmp.add(subordinateCombo2.getSelectedItem().toString());
                }

                if (!"-------".equals(subordinateCombo3.getSelectedItem().toString()) && !tmp.contains(subordinateCombo3.getSelectedItem().toString())) {
                    worker += ", \"" + subordinateCombo3.getSelectedItem().toString() + "\"";
                    tmp.add(subordinateCombo2.getSelectedItem().toString());
                }

                if (!"-------".equals(subordinateCombo4.getSelectedItem().toString()) && !tmp.contains(subordinateCombo4.getSelectedItem().toString())) {
                    worker += ", \"" + subordinateCombo4.getSelectedItem().toString() + "\"";
                    tmp.add(subordinateCombo2.getSelectedItem().toString());
                }

                if (!"-------".equals(subordinateCombo5.getSelectedItem().toString()) && !tmp.contains(subordinateCombo5.getSelectedItem().toString())) {
                    worker += ", \"" + subordinateCombo5.getSelectedItem().toString() + "\"";
                }

                worker += "]";

                java.util.Date res = (java.util.Date) dateStartSpinner.getValue();
                int day = res.getDate(), month = res.getMonth()+1, year = res.getYear()+1900;
                String appended_date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

                PreparedStatement ps = conn.prepareStatement(st);
                ps.setString(1, namaProjectField.getText().trim());
                ps.setString(2, appended_date);

                res = (java.util.Date) dateEndSpinner.getValue();
                day = res.getDate(); month = res.getMonth()+1; year = res.getYear()+1900;
                appended_date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

                ps.setString(3, appended_date);
                ps.setString(4, managerCombo.getSelectedItem().toString());
                ps.setString(5, worker);
                
                if (!isNew) ps.setString(6, prev_nama);

                ps.execute();
                
                if (isNew) {
                    String nama = namaProjectField.getText().trim();
                    st = "SELECT id_project FROM project WHERE nama = '" + nama + "'";
                    rs = stmt.executeQuery(st);
                    int id = 0;
                    while (rs.next()) {
                        id = rs.getInt("id_project");
                    }
                    st = "INSERT INTO customer (nama, id_project) VALUES (?, ?)";
                    ps = conn.prepareStatement(st);
                    ps.setString(1, namaCustomerField.getText().trim());
                    ps.setInt(2, id);
                    ps.execute();
                } else {
                    String nama = namaProjectField.getText().trim();
                    st = "SELECT id_project FROM project WHERE nama = '" + nama + "'";
                    rs = stmt.executeQuery(st);
                    int id = 0;
                    while (rs.next()) {
                        id = rs.getInt("id_project");
                    }
                    st = "UPDATE customer SET nama = ? WHERE id_project = ?";
                    ps = conn.prepareStatement(st);
                    ps.setString(1, namaCustomerField.getText().trim());
                    ps.setInt(2, id);
                    ps.execute();
                }
                
                stmt.close();
                conn.close();
            } catch (Exception en) {
                en.printStackTrace();
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
    
    private void launch(String nama_cus, String nama, LocalDate start, LocalDate end, String manager, ArrayList<String> worker) {
        try {
            OKButton.addActionListener(new handler());
            cancelButton.addActionListener(new handler2());
            
            namaCustomerField.setText(nama_cus);
            namaProjectField.setText(nama);
            String st;
            
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            stmt = conn.createStatement();
            st = "SELECT nama FROM manager";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                String name = rs.getString("nama");
                managerCombo.addItem(name);
            }
            st = "SELECT nama FROM subordinate";
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                String name = rs.getString("nama");
                subordinateCombo1.addItem(name);
                subordinateCombo2.addItem(name);
                subordinateCombo3.addItem(name);
                subordinateCombo4.addItem(name);
                subordinateCombo5.addItem(name);
            }
            
            if (!isNew) {
                managerCombo.setSelectedItem(manager);
                
                try {
                    subordinateCombo1.setSelectedItem(worker.get(0));
                } catch (Exception en) {
                    System.out.print("");
                }
                
                try {
                    subordinateCombo2.setSelectedItem(worker.get(1));
                } catch (Exception en) {
                    System.out.print("");
                }

                try {
                    subordinateCombo3.setSelectedItem(worker.get(2));
                } catch (Exception en) {
                    System.out.print("");
                } 
                try {
                    subordinateCombo3.setSelectedItem(worker.get(3));
                } catch (Exception en) {
                    System.out.print("");
                }
                try {
                    subordinateCombo3.setSelectedItem(worker.get(4));
                } catch (Exception en) {
                    System.out.print("");
                }
                
                Date tmp = Date.valueOf(start);
                dateStartSpinner.setModel(new javax.swing.SpinnerDateModel(tmp, null, null, java.util.Calendar.DAY_OF_MONTH));
                tmp = Date.valueOf(end);
                dateEndSpinner.setModel(new javax.swing.SpinnerDateModel(tmp, null, null, java.util.Calendar.DAY_OF_MONTH));
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public editProjectLayer(String nama_cus, String nama, LocalDate start, LocalDate end, String manager, ArrayList<String> worker) {
        initComponents();
        if (nama.equals("")) isNew = true;
        prev_nama = nama;
        this.nama_cus = nama_cus;
        launch(nama_cus, nama, start, end, manager, worker);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectNameLabel = new javax.swing.JLabel();
        tglMulaiLabel = new javax.swing.JLabel();
        deadlineLabel = new javax.swing.JLabel();
        managerProjectLabel = new javax.swing.JLabel();
        subordinate1ProjectLabel = new javax.swing.JLabel();
        managerCombo = new javax.swing.JComboBox<>();
        subordinate2ProjectLabel = new javax.swing.JLabel();
        subordinate3ProjectLabel = new javax.swing.JLabel();
        subordinate4ProjectLabel = new javax.swing.JLabel();
        subordinate5ProjectLabel = new javax.swing.JLabel();
        subordinateCombo1 = new javax.swing.JComboBox<>();
        subordinateCombo2 = new javax.swing.JComboBox<>();
        subordinateCombo3 = new javax.swing.JComboBox<>();
        subordinateCombo4 = new javax.swing.JComboBox<>();
        subordinateCombo5 = new javax.swing.JComboBox<>();
        namaProjectField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        OKButton = new javax.swing.JButton();
        dateStartSpinner = new javax.swing.JSpinner();
        dateEndSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        namaCustomerField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        projectNameLabel.setText("Nama Projek");

        tglMulaiLabel.setText("Tanggal Projek Mulai");

        deadlineLabel.setText("Deadline");

        managerProjectLabel.setText("Manager");

        subordinate1ProjectLabel.setText("Pekerja 1");

        managerCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        subordinate2ProjectLabel.setText("Pekerja 2");

        subordinate3ProjectLabel.setText("Pekerja 3");

        subordinate4ProjectLabel.setText("Pekerja 4");

        subordinate5ProjectLabel.setText("Pekerja 5");

        subordinateCombo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        subordinateCombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        subordinateCombo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        subordinateCombo4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        subordinateCombo5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------" }));

        cancelButton.setText("Cancel");

        OKButton.setText("OK");

        dateStartSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(1262278800000L), null, java.util.Calendar.DAY_OF_MONTH));

        dateEndSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(1262278800000L), null, java.util.Calendar.DAY_OF_MONTH));

        jLabel1.setText("Nama Customer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(cancelButton)
                        .addGap(79, 79, 79)
                        .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subordinate4ProjectLabel)
                            .addComponent(subordinate3ProjectLabel)
                            .addComponent(subordinate2ProjectLabel)
                            .addComponent(subordinate5ProjectLabel)
                            .addComponent(managerProjectLabel)
                            .addComponent(subordinate1ProjectLabel)
                            .addComponent(projectNameLabel)
                            .addComponent(tglMulaiLabel)
                            .addComponent(deadlineLabel)
                            .addComponent(jLabel1))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subordinateCombo5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subordinateCombo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subordinateCombo3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subordinateCombo4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateEndSpinner)
                            .addComponent(namaProjectField)
                            .addComponent(dateStartSpinner)
                            .addComponent(subordinateCombo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(managerCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(namaCustomerField))
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(namaCustomerField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameLabel)
                    .addComponent(namaProjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tglMulaiLabel)
                    .addComponent(dateStartSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deadlineLabel)
                    .addComponent(dateEndSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managerProjectLabel)
                    .addComponent(managerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subordinate1ProjectLabel)
                    .addComponent(subordinateCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subordinate2ProjectLabel)
                    .addComponent(subordinateCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subordinateCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subordinate3ProjectLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subordinate4ProjectLabel)
                    .addComponent(subordinateCombo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subordinate5ProjectLabel)
                    .addComponent(subordinateCombo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(OKButton))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JSpinner dateEndSpinner;
    private javax.swing.JSpinner dateStartSpinner;
    private javax.swing.JLabel deadlineLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> managerCombo;
    private javax.swing.JLabel managerProjectLabel;
    private javax.swing.JTextField namaCustomerField;
    private javax.swing.JTextField namaProjectField;
    private javax.swing.JLabel projectNameLabel;
    private javax.swing.JLabel subordinate1ProjectLabel;
    private javax.swing.JLabel subordinate2ProjectLabel;
    private javax.swing.JLabel subordinate3ProjectLabel;
    private javax.swing.JLabel subordinate4ProjectLabel;
    private javax.swing.JLabel subordinate5ProjectLabel;
    private javax.swing.JComboBox<String> subordinateCombo1;
    private javax.swing.JComboBox<String> subordinateCombo2;
    private javax.swing.JComboBox<String> subordinateCombo3;
    private javax.swing.JComboBox<String> subordinateCombo4;
    private javax.swing.JComboBox<String> subordinateCombo5;
    private javax.swing.JLabel tglMulaiLabel;
    // End of variables declaration//GEN-END:variables
}
