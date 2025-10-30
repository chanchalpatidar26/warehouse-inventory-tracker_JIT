import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {

    private List<StockObserver> observers;
    private Map<String, Product> inventory;

    public Warehouse() {
        this.observers = new ArrayList<>();
        this.inventory = new HashMap<>();
    }

    public int getCountItem() {
        return inventory.size();

    }

    public void addObserver(StockObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(StockObserver observer) {
        this.observers.remove(observer);
    }

    private void notifyLowStock(Product product) {
        for (StockObserver observer : this.observers) {
            observer.onLowStock(product);
        }
    }

    public void addProduct(String id, String name, int reorderThreshold) {
        if (inventory.containsKey(id)) {
            System.out.println("ERROR: Product ID '" + id + "' already exists.");
            return;
        }
        Product product = new Product(id, name, reorderThreshold);
        this.inventory.put(id, product);
        System.out.println("INFO: Product '" + name + "' (ID: " + id + ") added with 0 stock.");
    }

    public void receiveShipment(String productId, int amount) {
        Product product = getProductById(productId);
        if (product == null) {
            return;
        }
        if (amount <= 0) {
            System.out.println("ERROR: Shipment amount must be greater than 0.");
            return;
        }

        product.increaseQuantity(amount);
        System.out.println("INFO: Received " + amount + " units for '" + product.getName() + "'. New stock: "
                + product.getQuantity());
    }

    public void fulfillOrder(String productId, int amount) {
        Product product = getProductById(productId);
        if (product == null) {
            return;
        }
        if (amount <= 0) {
            System.out.println("ERROR: Order amount must be greater than 0.");
            return;
        }

        boolean success = product.decreaseQuantity(amount);

        if (success) {
            System.out.println("SUCCESS: Fulfilled order for " + amount + " units of '" + product.getName()
                    + "'. New stock: " + product.getQuantity());

            checkStockThreshold(product);
        } else {
            System.out.println("FAILED: Cannot fulfill order. Insufficient stock for '" + product.getName() + "'.");
            System.out.println(" (Ordered: " + amount + ", Available: " + product.getQuantity() + ")");
        }
    }

    private void checkStockThreshold(Product product) {
        if (product.getQuantity() < product.getReorderThreshold()) {
            notifyLowStock(product);
        }
    }

    private Product getProductById(String productId) {
        Product product = this.inventory.get(productId);
        if (product == null) {
            System.out.println("ERROR: Product ID '" + productId + "' not found.");
        }
        return product;
    }

    public void printStockStatus(String productId) {
        Product product = getProductById(productId);
        if (product != null) {
            System.out.println("STATUS: '" + product.getName() + "' (ID: " + product.getId() + ") has " + product.getQuantity() + " units.");
        }
    }

    void getAllItemStock() {
        for (Product product : inventory.values()) {
            System.out.println("Product ID: " + product.getId() + ", Name: " + product.getName() + ", Quantity: " + product.getQuantity());
        }
    }
}
