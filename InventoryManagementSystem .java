import java.util.HashMap;
import java.util.Scanner;

public class InventoryManagementSystem {
    
    static class Product {
        String productId;
        String productName;
        int quantity;
        double price;

        Product(String productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public String toString() {
            return productId + " - " + productName + " | Qty: " + quantity + " | Price: ₹" + price;
        }
    }

    static class Inventory {
        HashMap<String, Product> products = new HashMap<>();

        void addProduct(Product product) {
            products.put(product.productId, product);
            System.out.println("Product added: " + product);
        }

        void updateProduct(String productId, int newQty, double newPrice) {
            if (products.containsKey(productId)) {
                Product p = products.get(productId);
                p.quantity = newQty;
                p.price = newPrice;
                System.out.println("Product updated: " + p);
            } else {
                System.out.println("Product ID not found.");
            }
        }

        void deleteProduct(String productId) {
            if (products.remove(productId) != null) {
                System.out.println("Product with ID " + productId + " removed.");
            } else {
                System.out.println("Product ID not found.");
            }
        }

        void displayAll() {
            if (products.isEmpty()) {
                System.out.println("Inventory is empty.");
            } else {
                System.out.println("Current Inventory:");
                for (Product p : products.values()) {
                    System.out.println(p);
                }
            }
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Product\n2. Update Product\n3. Delete Product\n4. View Inventory\n5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    inventory.addProduct(new Product(id, name, qty, price));
                    break;

                case 2:
                    System.out.print("Enter Product ID to update: ");
                    String uid = sc.nextLine();
                    System.out.print("New Quantity: ");
                    int nQty = sc.nextInt();
                    System.out.print("New Price: ");
                    double nPrice = sc.nextDouble();
                    inventory.updateProduct(uid, nQty, nPrice);
                    break;

                case 3:
                    System.out.print("Enter Product ID to delete: ");
                    String did = sc.nextLine();
                    inventory.deleteProduct(did);
                    break;

                case 4:
                    inventory.displayAll();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}

