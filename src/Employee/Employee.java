package Employee;

public class Employee {
    private String id, nama, jabatan;

    public Employee(String id, String nama, String jabatan){
        this.setID(id);
        this.setNama(nama);
        this.setJabatan(jabatan);
    }
    
    public void setID(String id)                { this.id = id; }
    public void setNama(String nama)            { this.nama = nama; }
    public void setJabatan(String jabatan)      { this.jabatan = jabatan; }
    public String getId()                       { return this.id; }
    public String getNama()                     { return this.nama; }
    public String getJabatan()                  { return this.jabatan; }

}
