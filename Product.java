import java.io.Serializable;

//this is Product class
class Product implements Serializable {
    private int productId;
    private String productName;
    private int quantity;
    private int threshold;

    public Product(int productId, String productName, int quantity, int threshold) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public int getProductId() { 
        return productId; 
    }

    public String getProductName() { 
        return productName; 
    }

    public int getQuantity() { 
        return quantity; 
    }

    public int getThreshold() { 
        return threshold; 
    }

    public synchronized void increaseQuantity(int qty) {
        quantity += qty;
    }

    public synchronized void decreaseQuantity(int qty) {
        if (qty <= quantity) {
            quantity -= qty;
        } else {
            System.out.println("Not enough stock available!");
        }
    }

    public boolean isLowStock() {
        return quantity <= threshold;
    }

    
    public String toString() {
        return productId + " - " + productName + " | Qty: " + quantity;
    }
}