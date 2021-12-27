package Project;

import Employee.Employee;
import subProject.subProject;
import java.util.Date;
import java.util.Scanner;
import Customer.Customer;
import Manager.Manager;
import Subordinate.Subordinate;
import java.util.ArrayList;

public class Project extends Customer {
    Scanner cin = new Scanner(System.in);
    
    private String id_proj, nama;
    private Date timeStart, deadLine;
    private Manager manager;
    private ArrayList<Subordinate> s;
    
    public Project(String id_cus, String nama_cus, String id_proj, String nama, Date timeStart, Date deadLine, Manager manager, ArrayList<Subordinate> s) {
        super(id_cus, nama_cus);
        this.setId(id_proj);
        this.setNama(nama);
        this.setDateStart(timeStart);
        this.setDateEnd(deadLine);
        this.manager = manager;
        this.s = s;
    }
    
    public void setId(String id_proj) {
        this.id_proj = id_proj;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setDateStart(Date d) {
        this.timeStart = d;
    }
    
    public void setDateEnd(Date d) {
        this.deadLine = d;
    }
    
    public String getId() {
        return this.id_proj;
    }

    public String getNama() {
        return this.nama;
    }

    public Date getDateStart() {
        return this.timeStart;
    }

    public Date getDateEnd() {
        return this.deadLine;
    }
}
