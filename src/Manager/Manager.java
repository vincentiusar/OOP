package Manager;
import Employee.Employee;

public class Manager extends Employee{
    private String headOf;
    
    public Manager(String id, String nama, String jabatan, String headOf) {
        super(id, nama, jabatan);
        this.setHeadOf(headOf);
    }

    public String getHeadOf()               { return headOf; }
    public void setHeadOf(String headOf)    { this.headOf = headOf; }
    
}
