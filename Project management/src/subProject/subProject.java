package subProject;

import Orang.Orang;
import Project.Project;
import.java.util.Scanner;

public class subProject {
    private String id_subProj;
    private String nama;
    private String who;
    private Boolean done;
    
    public subProject (String id_subProj, String nama, String who, Boolean done){
        this.setId(id_subProj);
        this.setNama(nama);
        this.setWho(who);
        this.CheckDone(done);
    }
    public void setId(String id_subProj){
        this.id_subProj = id_subproj;   
    }
    public String getId{
        return id_subProj;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getNama{
        return nama;
    }
    public void setWho(String who){
        this.who = who;    
    }
    public boolean CheckDone( boolean done){
        boolean found = false;
        return found;
    }
}
