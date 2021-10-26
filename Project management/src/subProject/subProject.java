package subProject;

import Orang.Orang;
import Project.Project;
import java.util.Scanner;

public class subProject {
    private String id_subProj;
    private String nama;
    private Orang who;
    private Boolean done;
    
    public subProject (String id_subProj, String nama, Orang who, Boolean done){
        this.setId(id_subProj);
        this.setNama(nama);
        this.setWho(who);
        this.CheckDone(done);
    }
    
    public void setId(String id_subProj){
        this.id_subProj = id_subProj;   
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
        
    public void setWho(Orang who) {
        this.who = who;
    }
    
    public void setDone(boolean done) {
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
