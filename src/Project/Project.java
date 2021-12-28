package Project;

import Employee.Employee;
import subProject.subProject;
import java.util.Date;
import Customer.Customer;
import Manager.Manager;
import Subordinate.Subordinate;
import java.time.LocalDate;
import java.util.ArrayList;

public class Project {
    private String id_cus, nama_cus;
    private String id_proj, nama;
    private LocalDate timeStart, deadLine;
    private Manager manager;
    private ArrayList<Subordinate> s;
    private ArrayList<subProject> sub;
    
    public Project(String id_cus, String nama_cus, String id_proj, String nama, LocalDate timeStart, LocalDate deadLine, Manager manager, ArrayList<Subordinate> s, ArrayList<subProject> sub) {
        this.setNamaCus(nama_cus);
        this.setIdCus(id_cus);
        this.setId(id_proj);
        this.setNama(nama);
        this.setDateStart(timeStart);
        this.setDateEnd(deadLine);
        this.setManager(manager);
        this.setSubordinate(s);
        this.setSubproject(sub);
    }
    
    public void setIdCus(String id) { this.id_cus = id; }
    public void setNamaCus(String nama) { this.nama_cus = nama; }
    public void setManager(Manager m) { this.manager = m; }
    public void setSubordinate(ArrayList<Subordinate> s) { this.s = s; }
    public void setSubproject(ArrayList<subProject> sub) { this.sub = sub; }
    public void setId(String id_proj) { this.id_proj = id_proj; }
    public void setNama(String nama) { this.nama = nama; }
    public void setDateStart(LocalDate d) { this.timeStart = d; }
    public void setDateEnd(LocalDate d) { this.deadLine = d; }
    public String getIdCus() { return this.id_cus; }
    public String getNamaCus() { return this.nama_cus; }
    public Manager getManager() { return this.manager; }
    public ArrayList<Subordinate> getSubordinate() { return this.s; }
    public ArrayList<subProject> getSubproject() { return this.sub; }
    public String getId() { return this.id_proj; }
    public String getNama() { return this.nama; } 
    public LocalDate getDateStart() { return this.timeStart; }
    public LocalDate getDateEnd() { return this.deadLine; }
}
