package subProject;

import Employee.Employee;
import Project.Project;
import java.util.Scanner;
import java.util.Date;


public class subProject extends Project{
    private String id_subProj;
    private String nama;
    private Employee who[];
    private boolean done;
    
    public subProject (String id_company, String id_project, String nama_project, String nama, String id_subProj){
        super(id_company, id_project, nama_project);
        this.setIdSubProj(id_project);
        this.setNama(nama);
    }
     public void setIdSubProj(String id_SubProj){
        this.id_SubProj = id_SubProj;   
    }
     public void setNama(String nama){
        this.nama = nama;
    }
    public void setWho(Employee who[]){
        w
    }
    
    public void setDone(boolean done){
        this.done = done;
    }
    
    public String getNama() {
        return nama;
    }
        
    public String getId(){
        return id_subProj;
    }
    
    public boolean CheckDone(boolean done){
        boolean found = false;
        
        
        
        return found;
    }
}
