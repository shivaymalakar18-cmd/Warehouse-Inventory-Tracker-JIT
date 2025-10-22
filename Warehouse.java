import java.io.Serializable;
import java.util.*;

public class Warehouse implements Serializable {
    private String warehouseName;
    private List<Product> productList = new ArrayList<>();
    private List<StockObserver> observers = new ArrayList<>();

    public Warehouse(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    // Register observer
    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    // Notify observers when low stock
    private void notifyObservers(Product p) {
        for (StockObserver obs : observers) {
            obs.onLowStock(p);
        }
    }

    public void addProduct(Product p) {
        productList.add(p);
        System.out.println("Product added successfully!");
    }

    // Shipment handling (Threaded)
    public void receiveShipment(int productId, int qty) {
        Thread shipmentThread = new Thread(() -> {
            try {
                System.out.println("Processing shipment in " + warehouseName + "...");
                Thread.sleep(1000);
                for (Product p : productList) {
                    if (p.getProductId() == productId) {
                        p.increaseQuantity(qty);
                        System.out.println("Shipment received for " + p.getProductName());
                        checkLowStock(p);
                        return;
                    }
                }
                System.out.println("Product not found!");
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
        shipmentThread.start();
    }

    // Order handling (Threaded)
    public void fulfillOrder(int productId, int qty) {
        Thread orderThread = new Thread(() -> {
            try {
                System.out.println("Processing order in " + warehouseName + "...");
                Thread.sleep(1000);
                for (Product p : productList) {
                    if (p.getProductId() == productId) {
                        p.decreaseQuantity(qty);
                        System.out.println("Order fulfilled for " + p.getProductName());
                        checkLowStock(p);
                        return;
                    }
                }
                System.out.println("Product not found!");
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        });
        orderThread.start();
    }

    public void viewProducts() {
        System.out.println("\n " + warehouseName + " Inventory:");
        if (productList.isEmpty()) {
            System.out.println("No products available!");
        } else {
            for (Product p : productList) {
                System.out.println("→ " + p);
            }
        }
    }

    private void checkLowStock(Product p) {
        if (p.isLowStock()) {
            notifyObservers(p);
        }
    }

    public List<Product> getProducts() {
        return productList;
    }

}
