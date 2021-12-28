package subProject;

public class subProject {    
    private String id_subProj, id_proj;

    private String nama;
    private boolean done;
    
    public subProject(String id_sub, String nama_sub, boolean isDone, String id_proj) {
        this.setIdSubProj(id_sub);
        this.setIdProj(id_proj);
        this.setNamaSub(nama_sub);
        this.setDone(isDone);
        
    }
    
    public String getIdProj() { return id_proj; }
    public void setIdProj(String id_proj) { this.id_proj = id_proj; }
    public void setIdSubProj(String id_SubProj) { this.id_subProj = id_SubProj; }
    public void setNamaSub(String nama) { this.nama = nama; }
    public void setDone(boolean done) { this.done = done; }
    public String getNamaSub() { return this.nama; }
    public String getIdSub() { return this.id_subProj; }
    public boolean CheckDone() { return this.done; }
}
