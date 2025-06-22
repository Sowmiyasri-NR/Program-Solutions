import java.util.Arrays;
import java.util.Comparator;

public class EcommerceSearch {

    static class Product {
        String productId;
        String productName;
        String category;

        Product(String productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        public String toString() {
            return productId + " - " + productName + " (" + category + ")";
        }
    }

    static Product[] products = {
        new Product("P101", "Laptop", "Electronics"),
        new Product("P102", "T-Shirt", "Clothing"),
        new Product("P103", "Mobile", "Electronics"),
        new Product("P104", "Shoes", "Footwear"),
        new Product("P105", "Headphones", "Electronics")
    };

    static Product linearSearch(String name) {
        for (Product p : products) {
            if (p.productName.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    static Product binarySearch(String name) {
        Arrays.sort(products, Comparator.comparing(p -> p.productName.toLowerCase()));
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = name.compareToIgnoreCase(products[mid].productName);
            if (cmp == 0) {
                return products[mid];
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String searchName = "Shoes";

        System.out.println("Linear Search Result:");
        Product result1 = linearSearch(searchName);
        System.out.println(result1 != null ? result1 : "Product not found");

        System.out.println("Binary Search Result:");
        Product result2 = binarySearch(searchName);
        System.out.println(result2 != null ? result2 : "Product not found");
    }
}
