import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();
        StockObserver alertService = new AlertService();
        warehouse.addObserver(alertService);
        System.out.println("Welcome to the Event-Based Warehouse Tracker");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printMenu();

        while (running) {
            int choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    // 1. Add Product
                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    int threshold = getIntInput(scanner, "Enter Reorder Threshold: ");
                    warehouse.addProduct(id.strip().replaceAll("\\s+", " "), name.strip().replaceAll("\\s+", " "),
                            threshold);
                    break;
                case 2:
                    // 2. Receive Shipment
                    if (warehouse.getCountItem() <= 0) {
                        System.out.print("Warehouse is empty");
                        break;
                    }
                    System.out.print("Enter Product ID: ");
                    String shipId = scanner.nextLine();
                    int shipQty = getIntInput(scanner, "Enter Quantity to Add: ");
                    warehouse.receiveShipment(shipId, shipQty);
                    break;
                case 3:
                    // 3. Fulfill Order
                    if (warehouse.getCountItem() <= 0) {
                        System.out.print("Warehouse is empty");
                        break;
                    }
                    System.out.print("Enter Product ID: ");
                    String orderId = scanner.nextLine();
                    int orderQty = getIntInput(scanner, "Enter Quantity to Fulfill: ");
                    warehouse.fulfillOrder(orderId, orderQty);
                    break;
                case 4:
                    // 4. Check Stock
                    if (warehouse.getCountItem() <= 0) {
                        System.out.print("Warehouse is empty");
                        break;
                    }
                    System.out.print("Enter Product ID: ");
                    String statusId = scanner.nextLine();
                    warehouse.printStockStatus(statusId);
                    break;
                case 5:
                    warehouse.getAllItemStock();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("Warehouse Menu");
        System.out.println("1. Add New Product");
        System.out.println("2. Receive Shipment (Increase Stock)");
        System.out.println("3. Fulfill Order (Decrease Stock)");
        System.out.println("4. Check Product Stock");
        System.out.println("5. View All Item Stock");
        System.out.println("6. Exit");
        System.out.println("");
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
