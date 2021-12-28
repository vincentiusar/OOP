package Customer;

public class Customer {
    private String id;
    private String name;
    
    public Customer(String id, String name) {
        this.setID(id);
        this.setName(name);
    }
    
    private void setID(String id) { this.id = id; }
    public String getId() { return this.id; }
    private void setName(String name) { this.name = name; }
    public String getName() { return this.name; }
    
}
