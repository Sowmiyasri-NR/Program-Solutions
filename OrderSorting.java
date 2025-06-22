public class OrderSorting {

    static class Order {
        String orderId;
        String customerName;
        double totalPrice;

        Order(String orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        public String toString() {
            return orderId + " - " + customerName + " - ₹" + totalPrice;
        }
    }

    static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    static void displayOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Order[] orders1 = {
            new Order("O101", "Alice", 5600.0),
            new Order("O102", "Bob", 2500.5),
            new Order("O103", "Charlie", 9800.0),
            new Order("O104", "Diana", 3100.75),
            new Order("O105", "Ethan", 6200.0)
        };

        System.out.println("Bubble Sort:");
        bubbleSort(orders1);
        displayOrders(orders1);

        Order[] orders2 = {
            new Order("O101", "Alice", 5600.0),
            new Order("O102", "Bob", 2500.5),
            new Order("O103", "Charlie", 9800.0),
            new Order("O104", "Diana", 3100.75),
            new Order("O105", "Ethan", 6200.0)
        };

        System.out.println("\nQuick Sort:");
        quickSort(orders2, 0, orders2.length - 1);
        displayOrders(orders2);
    }
}
