package Subordinate;
import Employee.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Subordinate extends Employee implements hitungGaji{
    private String divisi;
    
    public Subordinate(String id, String nama, String jabatan, String divisi, String id_company) {
        super(id, nama, jabatan, id_company);
        this.setDivisi(divisi);
    }

    public String getDivisi()               { return divisi; }
    public void setDivisi(String divisi)    { this.divisi = divisi; }
    
    @Override
    public String hitungGaji(){
        double alpha = 0.5, base = 10000000, umr = 4000000;
        NumberFormat f = new DecimalFormat("#0.00");
        return f.format(alpha * base + umr);
    }
}
