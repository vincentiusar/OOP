package Project;

import Orang.Orang;
import subProject.subProject;
import java.util.Date;
import java.util.Scanner;

public class project {
    Scanner cin = new Scanner(System.in);
    
    private String id_proj, nama;
    private Date timeStart, deadLine;
    private Orang worker[] = new Orang[10];
    private subProject side[] = new subProject[5];
    
    public project(Orang org[], String id_proj, String nama, Date timeStart, Date deadLine, int num_worker, int num_sub) {
        this.setId(id_proj);
        this.setNama(nama);
        this.setDateStart(timeStart);
        this.setDateEnd(deadLine);
        this.setWorker(org, num_worker);
        this.setSubProject(org, num_sub);
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
    
    private boolean checkIdOrang(Orang org[], Orang w[], String str) {
        boolean found = false;
        for (int i = 0; i < org.length; i++) {
            for (int j = 0; j < w.length; j++) {
                if (str.equals(org[i].getId()) && str.equals(this.worker[j].getId())) {
                    found = true;
                }
            }
        }
        return found;
    }
    
    private Orang findOrang(Orang org[], String str) {
        for (int i = 0; i < org.length; i++) {
            if (str.equals(org[i].getId())) {
                return org[i];
            }
        }
        return null;
    }
    
    public void setWorker(Orang org[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("ID      : ", org[i].getId());
            System.out.println("Nama    : ", org[i].getId());
        }
        for (int i = 0; i < n; i++) {
            String str;
            do {
                str = cin.nextLine();
            } while (!checkIdOrang(org, this.worker, str));     
            this.worker[i] = findOrang(org, str);
        }
    }
    
    private boolean findSub(subProject sub[], String str) {
        for (int i = 0; i < sub.length; i++) {
            if (sub[i].equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    public void setSubProject(Orang org[], int n) {
        for (int i = 0; i < n; i++) {
            String id, id_orang = "";
            System.out.println("jumlah orang yang kerja : ");
            
            int count;
            count = cin.nextInt();
                       
            Orang who[] = new Orang[count];
            
            boolean done = false;
            
            do {
                id = cin.nextLine();
            } while (findSub(this.side, id));
            this.side[i].setId(id);
           
            for (int j = 0; j < count; j++) {
                do {
                    id_orang = cin.nextLine();
                } while (this.findOrang(org, id_orang) == null);
                this.side[i].setWho(this.findOrang(org, id_orang));
            }
            this.side[i].setDone(false);
        }
    }
    
}
