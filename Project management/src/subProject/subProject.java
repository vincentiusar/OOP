package subProject;

import Employee.Employee;
import Project.Project;
import java.util.Scanner;
import java.util.Date;


public class subProject extends Project{
    Scanner cin = new Scanner(System.in);
    
    private String id_subProj;
    private String nama;
    private Employee who[];
    private boolean done;
    
    public subProject(String id_cus, String nama_cus, Employee worker[], String id_sub, Employee E[], String id, String nama, Date start, Date end, int num_worker, int num_sub,int nE, int nP) {
        super(id_cus, nama_cus, E, id, nama, start, end, num_worker, num_sub, nE, nP);
        
        this.setIdSubProj(id_sub);
        String name = cin.nextLine();
        this.setNama(name);
        this.setWho(worker, num_sub);
        this.setDone(false);
    }
    
    public void setIdSubProj(String id_SubProj) {
        this.id_subProj = id_subProj;
    }
    
    public void setNama(String nama) {
       this.nama = nama;
    }
    
    private int findWorker(Employee worker[], String str) {
        for (int i = 0; i < worker.length; i++) {
            if (worker[i].getId().equals(str)) {
                return i;
            }
        }
        return -1;
    }
    
    public void setWho(Employee worker[], int nw) {
        for (int i = 0; i < nw; i++) {
            String str; 
            do {
                str = cin.nextLine();
            } while (this.findWorker(worker, str) == -1);
            this.who[i] = new Employee(str, worker[i].getNama(), worker[i].getJabatan());
        }
    }
    
    public void setDone(boolean done){
        this.done = done;
    }
    
    public String getNama() {
        return this.nama;
    }
        
    public String getId(){
        return this.id_subProj;
    }
    
    public boolean CheckDone(boolean done) {
        return done;
    }
}
