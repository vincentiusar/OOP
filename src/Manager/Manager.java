package Manager;
import Employee.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Manager extends Employee implements hitungGaji{
    private String headOf;
    
    public Manager(String id, String nama, String jabatan, String headOf, String id_company) {
        super(id, nama, jabatan, id_company);
        this.setHeadOf(headOf);
    }

    public String getHeadOf()               { return headOf; }
    public void setHeadOf(String headOf)    { this.headOf = headOf; }
    
    @Override
    public String hitungGaji(){
        double alpha = 0.7, base = 10000000, umr = 4000000;
        NumberFormat f = new DecimalFormat("#0.00");
        return f.format(alpha * base + umr);
    }
}
