package Manager;
import Employee.Employee;

public class Manager extends Employee{
    private String divisi;
    
    public Manager(String id, String nama, String jabatan, String divisi) {
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
