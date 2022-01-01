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
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vincentius
 */
public class editSubProjectLayer extends javax.swing.JFrame {

    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    /**
     * Creates new form editSubProject
     */
    
    boolean isNew = false;
    String prev_nama;
    
    private class handler implements ActionListener {
        
        private ArrayList<String> toArrayString(String target) {
            ArrayList<String> res = new ArrayList<>();
            for (int i = 0; i < target.length(); i++) {
                String result = "";
                if (target.charAt(i) == '"') {
                    i++;
                    while (target.charAt(i) != '"') {
                        result += target.charAt(i);
                        i++;
                    }
                    res.add(result);
                    result = "";
                    i++;
                }
            }
            return res;
        }
        
        public void actionPerformed(ActionEvent e) {
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
                stmt = conn.createStatement();
                
                String st;
                if (isNew) {
                    st = "INSERT INTO subproject (nama, isDone, id_project) VALUES (?, ?, ?)";
                } else {
                    st = "UPDATE subproject SET nama = ?, isDone = ?, id_project = ? WHERE nama = ?";
                }
                PreparedStatement ps = conn.prepareStatement(st);     
                ps.setString(1, namaTextField.getText().trim());
                ps.setBoolean(2, selesaiRadio.isSelected());
                
                st = "SELECT id_project FROM project WHERE nama = '" + projectCombo.getSelectedItem().toString() + "'";
                rs = stmt.executeQuery(st);
                
                int id = 0;
                while (rs.next()) {
                    id = rs.getInt("id_project");
                }
                
                ps.setInt(3, id);
                
                if (!isNew) ps.setString(4, prev_nama);
                ps.execute();
                
                String sub = "";
                st = "SELECT subproject FROM project WHERE id_project = " + id;
                rs = stmt.executeQuery(st);
                while (rs.next()) {
                    sub += rs.getString("subproject");
                }

                st = "UPDATE project SET subProject = ? WHERE id_project = ?";
                ps = conn.prepareStatement(st);
                
                if (isNew) {
                    sub = sub.substring(0, sub.length() - 1);
                    if (sub.charAt(sub.length()-1) == '[')
                        sub += "\"" + namaTextField.getText().trim() + "\"]";
                    else sub += ", \"" + namaTextField.getText().trim() + "\"]";
                } else {
                    ArrayList<String> tmp = toArrayString(sub);
                    int pos = tmp.indexOf(prev_nama);
                    tmp.set(pos, namaTextField.getText().trim());
                    String sp = "[";
                    for (String s : tmp) {
                        sp += "\"" + s + "\",";
                    }
                    sp += "]";
                    sub = sp;
                }
                ps.setString(1, sub);
                ps.setInt(2, id);
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
    
    public void launch(String nama, String induk, boolean status) {
        OKButton.addActionListener(new handler());
        cancelButton.addActionListener(new handler2());
        namaTextField.setText(nama);
        
        String st = "SELECT nama FROM project";
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                projectCombo.addItem(rs.getString("nama"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        projectCombo.setSelectedItem(induk);
        if (status == true) {
            selesaiRadio.setSelected(true);
        } else {
            belumSelesaiRadio.setSelected(true);
        }
    }
    
    public editSubProjectLayer(String nama, String induk, boolean status) {
        initComponents();
        if ("".equals(nama)) isNew = true;
        prev_nama = nama;
        launch(nama, induk, status);
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
        indukProjectLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        namaTextField = new javax.swing.JTextField();
        selesaiRadio = new javax.swing.JRadioButton();
        belumSelesaiRadio = new javax.swing.JRadioButton();
        projectCombo = new javax.swing.JComboBox<>();
        cancelButton = new javax.swing.JButton();
        OKButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        namaLabel.setText("Nama");

        indukProjectLabel.setText("Induk Project");

        statusLabel.setText("Status");

        buttonGroup1.add(selesaiRadio);
        selesaiRadio.setText("Selesai");

        buttonGroup1.add(belumSelesaiRadio);
        belumSelesaiRadio.setText("Belum Selesai");

        cancelButton.setText("Cancel");

        OKButton.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(indukProjectLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(projectCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaLabel)
                                    .addComponent(statusLabel))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(namaTextField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(selesaiRadio)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(belumSelesaiRadio)
                                            .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(cancelButton)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaLabel)
                    .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indukProjectLabel)
                    .addComponent(projectCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(selesaiRadio)
                    .addComponent(belumSelesaiRadio))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(OKButton))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButton;
    private javax.swing.JRadioButton belumSelesaiRadio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel indukProjectLabel;
    private javax.swing.JLabel namaLabel;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JComboBox<String> projectCombo;
    private javax.swing.JRadioButton selesaiRadio;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
