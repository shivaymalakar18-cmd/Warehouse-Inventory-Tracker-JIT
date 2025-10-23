import java.io.*;
import java.util.*;

// here generate alert 
class AlertService implements StockObserver {

    public void onLowStock(Product product) {
        System.out.println("ALERT: Low stock for " + product.getProductName() +
                " — only " + product.getQuantity() + " left!");
    }
}

// hare Handles saving data
class FileHandler {
    private static final String FILE_NAME = "data.txt";

    // Save all warehouse & product data in readable format in txt file and here txt
    // file is data.txt
    public static void saveData(List<Warehouse> warehouses) {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            for (Warehouse w : warehouses) {
                writer.println("==================================");
                writer.println("Warehouse: " + w.getWarehouseName());
                writer.println("==================================");

                List<Product> products = w.getProducts();
                if (products.isEmpty()) {
                    writer.println("No products available in this warehouse.\n");
                } else {
                    for (Product p : products) {
                        writer.println("Product ID: " + p.getProductId());
                        writer.println("Product Name: " + p.getProductName());
                        writer.println("Quantity: " + p.getQuantity());
                        writer.println("Threshold: " + p.getThreshold());
                        writer.println("----------------------------------");
                    }
                }
                writer.println();
            }
            System.out.println(" Readable data saved successfully in " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving readable data: " + e.getMessage());
        }
    }

    // Load serialized data from file
    public static List<Warehouse> loadData() {
        List<Warehouse> warehouses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Warehouse currentWarehouse = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Warehouse:")) {
                    String name = line.substring(line.indexOf(":") + 1).trim();
                    currentWarehouse = new Warehouse(name);
                    warehouses.add(currentWarehouse);
                } else if (line.startsWith("Product ID:")) {
                    int id = Integer.parseInt(line.substring(line.indexOf(":") + 1).trim());
                    String name = reader.readLine().split(":")[1].trim();
                    int qty = Integer.parseInt(reader.readLine().split(":")[1].trim());
                    int th = Integer.parseInt(reader.readLine().split(":")[1].trim());
                    Product p = new Product(id, name, qty, th);
                    if (currentWarehouse != null) {
                        currentWarehouse.addProduct(p);
                    }
                    reader.readLine(); 
                }
            }
            System.out.println("Readable data loaded successfully from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error reading readable data: " + e.getMessage());
        }
        return warehouses;
    }

}

// this is main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Warehouse> warehouses = FileHandler.loadData();
        AlertService alertService = new AlertService();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("  WAREHOUSE INVENTORY TRACKER");
            System.out.println("==============================");
            System.out.println("1. Add Warehouse");
            System.out.println("2. Add Product");
            System.out.println("3. Receive Shipment (Threaded)");
            System.out.println("4. Fulfill Order (Threaded)");
            System.out.println("5. View Inventory");
            System.out.println("6. Exit");
            System.out.print(" Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter warehouse name: ");
                    String wName = sc.nextLine();
                    Warehouse w = new Warehouse(wName);
                    w.addObserver(alertService);
                    warehouses.add(w);
                    System.out.println("Warehouse added successfully!");
                }

                case 2 -> {
                    Warehouse w = selectWarehouse(warehouses, sc);
                    if (w != null) {
                        System.out.print("Enter product ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter product name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        System.out.print("Enter threshold: ");
                        int th = sc.nextInt();
                        w.addProduct(new Product(id, name, qty, th));
                    }
                }

                case 3 -> {
                    Warehouse w = selectWarehouse(warehouses, sc);
                    if (w != null) {
                        System.out.print("Enter product ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter shipment quantity: ");
                        int qty = sc.nextInt();
                        w.receiveShipment(id, qty);
                    }
                }

                case 4 -> {
                    Warehouse w = selectWarehouse(warehouses, sc);
                    if (w != null) {
                        System.out.print("Enter product ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter order quantity: ");
                        int qty = sc.nextInt();
                        w.fulfillOrder(id, qty);
                    }
                }

                case 5 -> {
                    for (Warehouse w : warehouses) {
                        w.viewProducts();
                    }
                }

                case 6 -> {
                    FileHandler.saveData(warehouses);
                    System.out.println(" Data saved to data.txt");
                    System.out.println(" Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static Warehouse selectWarehouse(List<Warehouse> warehouses, Scanner sc) {
        if (warehouses.isEmpty()) {
            System.out.println("No warehouses available!");
            return null;
        }
        System.out.println("\n Available Warehouses:");
        for (int i = 0; i < warehouses.size(); i++) {
            System.out.println((i + 1) + ". " + warehouses.get(i).getWarehouseName());
        }
        System.out.print("Select warehouse number: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < warehouses.size()) {
            return warehouses.get(index);
        } else {
            System.out.println("Invalid selection!");
            return null;
        }
    }
}
