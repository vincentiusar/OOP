/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Company;

/**
 *
 * @author Vincentius
 */
public class Company {
    private String id_company, nama;
    
    public Company(String id, String nama) {
        this.setId_company(id_company);
        this.setNama(nama);
    }

    public String getId_company()                   { return id_company; }

    public void setId_company(String id_company)    { this.id_company = id_company; }

    public String getNama()                         { return nama; }

    public void setNama(String nama)                { this.nama = nama; }
}
