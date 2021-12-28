package Customer;

public class Customer {
    private String id, id_proj;
    private String name;
    
    public Customer(String id, String name, String id_proj) {
        this.setID(id);
        this.setName(name);
        this.setId_proj(id_proj);
    }
    
    public String getId_proj() { return id_proj; }
    public void setId_proj(String id_proj) { this.id_proj = id_proj; }
    private void setID(String id) { this.id = id; }
    public String getId() { return this.id; }
    private void setName(String name) { this.name = name; }
    public String getName() { return this.name; }
    
}
