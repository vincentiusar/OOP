package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Employee {
    
    static final String DB_URL = "jdbc:mysql://localhost/projectmanagementtubes";
    static final String DB_USER = "root";
    static final String DB_PASS = "";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    private String id, nama, jabatan, id_company;

    public Employee(String id, String nama, String jabatan, String id_company){
        this.setID(id);
        this.setNama(nama);
        this.setJabatan(jabatan);
        this.setId_company(id_company);
    }

    public String getId_company()               { return id_company; }
    public void setId_company(String id_company){ this.id_company = id_company; }
    public void setID(String id)                { this.id = id; }
    public void setNama(String nama)            { this.nama = nama; }
    public void setJabatan(String jabatan)      { this.jabatan = jabatan; }
    public String getId()                       { return this.id; }
    public String getNama()                     { return this.nama; }
    public String getJabatan()                  { return this.jabatan; }
    public String getNamaCompany() { 
        String st;
        st = "SELECT nama FROM company WHERE id_company = '" + id_company + "'";
        
        String res = "";
        
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(st);
            while (rs.next()) {
                res = rs.getString("nama");
            }
            
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
