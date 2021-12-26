package Project;

import Employee.Employee;
import subProject.subProject;
import java.util.Date;
import java.util.Scanner;
import Customer.Customer;

public class Project extends Customer {
    Scanner cin = new Scanner(System.in);
    
    private String id_proj, nama;
    private Date timeStart, deadLine;
    private Employee worker[] = new Employee[10];
    private subProject side[] = new subProject[5];
    private int lenEm, lenSu;
    
    public Project(String id_cus, String nama_cus, Employee E[], String id_proj, String nama, Date timeStart, Date deadLine, int num_worker, int num_sub, int nE, int nP) {
        super(id_cus, nama_cus);
        if (E.length != 0) {
            this.setId(id_proj);
            this.setNama(nama);
            this.setDateStart(timeStart);
            this.setDateEnd(deadLine);
            this.setWorker(E, num_worker, nE);
            this.setSubProject(id_cus, nama_cus, E, num_sub, num_worker, nE, nP);
        }
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
    
    private boolean checkIdOrang(Employee org[], Employee w[], String str, int nE) {
        // untuk mencari apakah pekerja Ai sudah terdaftar pada project
        boolean found = false;
        for (int i = 0; i < nE; i++) {
            for (int j = 0; j < lenEm; j++) {
                if (str.equals(org[i].getId()) && str.equals(this.worker[j].getId())) {
                    found = true;
                }
            }
        }
        return found;
    }
    
    private Employee findOrang(Employee org[], String str, int nE) {
        // untuk mengembalikan orang jika ketemu di array employee
        for (int i = 0; i < nE; i++) {
            if (str.equals(org[i].getId())) {
                return org[i];
            }
        }
        return null;
    }
    
    public void setWorker(Employee org[], int n, int nE) {
        while (n > 10) {
            // antisipasi ketika project dikerjakan > 10 orang
            System.out.println("Jumlah orang diminta melebihi batas (10) : ");
            n = cin.nextInt();
        }
        for (int i = 0; i < nE; i++) {
            // tampilkan semua karyawan
            System.out.print((i+1) + ". ");
            System.out.println("ID      : " + org[i].getId());
            System.out.println("Nama    : " + org[i].getNama());
        }
        for (int i = 0; i < n; i++) {
            // masukkan employee yang akan mengerjakan proyek
            String str;
            do {
                System.out.println("Masukan id pegawai :");
                str = cin.nextLine();
                // kalau belum ada di list project, masukkan employee
            } while (checkIdOrang(org, this.worker, str, nE));     
            this.worker[lenEm++] = new Employee(this.findOrang(org, str, nE).getId(), this.findOrang(org, str, nE).getNama(), this.findOrang(org, str, nE).getJabatan());
        }
    }
    
    private boolean findSub(subProject sub[], String str, int lenSu) {
        for (int i = 0; i < lenSu; i++) {
            // cek apakah subproject id terdaftar di array sub
            if (sub[i].getId().equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    public void setSubProject(String id_cus, String nama_cus, Employee E[], int n, int num_worker, int nE, int nP) {
        // cek apakah subproject > 5
        while (n > 5) {
            System.out.println("Jumlah subProject > 5 :");
            n = cin.nextInt();
        }
        // input sebanyak n sub project
        for (int i = 0; i < n; i++) {
            String id_sub;
            do {
                System.out.println("masukkan id subproject");
                id_sub = cin.nextLine();
            } while (this.findSub(side, id_sub, lenSu));
            this.side[lenSu++] = new subProject(id_cus, nama_cus, this.worker, id_sub, E, this.getId(), this.getNama(), this.getDateStart(), this.getDateEnd(), num_worker, n, nE, nP);
        }
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

    public void showInfo() {
        System.out.println("ID project : " + this.getId());
        System.out.println("Nama Project : " + this.getNama());
        System.out.println("Mulai Project : " + this.getDateStart());
        System.out.println("Deadline : " + this.getDateEnd());
        System.out.println("Pekerja : ");
        for (int i = 0; i < lenEm; i++) {
            System.out.println("Orang " + i+1 + " : " + worker[i].getId() + " " + worker[i].getNama());
        }
        System.out.println("subProject : ");
        for (int i = 0; i < lenSu; i++) {
            System.out.println("subProject " + i+1 + " : " + side[i].getId() + " " + side[i].getNama());
        }
    }
}
