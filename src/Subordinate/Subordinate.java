package Subordinate;
import Employee.Employee;

public class Subordinate extends Employee{
    private String divisi;
    
    public Subordinate(String id, String nama, String jabatan, String divisi) {
        super(id, nama, jabatan);
        this.setDivisi(divisi);
    }

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }
    
}
