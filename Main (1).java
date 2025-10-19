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

        while (running) {
            printMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();
                    int threshold = getIntInput(scanner, "Enter Reorder Threshold: ");
                    warehouse.addProduct(id, name, threshold);
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    String shipId = scanner.nextLine();
                    int shipQty = getIntInput(scanner, "Enter Quantity to Add: ");
                    warehouse.receiveShipment(shipId, shipQty);
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    String orderId = scanner.nextLine();
                    int orderQty = getIntInput(scanner, "Enter Quantity to Fulfill: ");
                    warehouse.fulfillOrder(orderId, orderQty);
                    break;
                case 4:
                    System.out.print("Enter Product ID: ");
                    String statusId = scanner.nextLine();
                    warehouse.printStockStatus(statusId);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.err.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Warehouse Menu");
        System.out.println("1. Add New Product");
        System.out.println("2. Receive Shipment (Increase Stock)");
        System.out.println("3. Fulfill Order (Decrease Stock)");
        System.out.println("4. Check Product Stock");
        System.out.println("5. Exit");
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
}