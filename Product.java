public class Product {
    private String id;
    private String name;
    private int quantity;
    private int reorderThreshold;

    public Product(String id, String name, int reorderThreshold) {
        this.id = id;
        this.name = name;
        this.reorderThreshold = reorderThreshold;
        this.quantity = 0; 
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    void increaseQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
        }
    }

    boolean decreaseQuantity(int amount) {
        if (amount <= 0) {
            return false; 
        }
        if (this.quantity >= amount) {
            this.quantity -= amount;
            return true; 
        }
        return false;
    }
}