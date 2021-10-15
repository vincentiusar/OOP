package Orang;

public class Orang {
    private String id,nama,jabatan;
    
    public Orang(String id, String nama, String jabatan) {
        this.setId(id);
        this.setNama(nama);
        this.setJabatan(jabatan);
    }
    
    public void setId(String id){
      this.id = id;
    }
    
    public void setNama(String nama){
      this.nama = nama;
    }
    
    public void setJabatan(String jabatan){
      this.jabatan = jabatan;
    }

    public String getId() {
      return this.id;
    }
    
    public String getNama(){
      return this.nama;
    }
    
    public String getJabatan(){
      return this.jabatan;
    }

    public void showInfo(){

    }
}
