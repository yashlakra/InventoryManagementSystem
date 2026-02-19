import java.io.*;
import java.util.*;

// Represents one item stored in the inventory
class ProductItem {
    private int productId;
    private String productName;
    private int stockCount;
    private double unitCost;

    // Assigns values when a product is created
    public ProductItem(int productId, String productName, int stockCount, double unitCost) {
        this.productId = productId;
        this.productName = productName;
        this.stockCount = stockCount;
        this.unitCost = unitCost;
    }

    // Used to compare products while searching
    public int id() {
        return productId;
    }

    // Returns total value of this item
    public double totalValue() {
        return stockCount * unitCost;
    }

    // Prepares product data to be written into file
    public String pack() {
        return productId + "," + productName + "," + stockCount + "," + unitCost;
    }

    // Shows details of the product on screen
    public void show() {
        System.out.println("ID       : " + productId);
        System.out.println("Name     : " + productName);
        System.out.println("Quantity : " + stockCount);
        System.out.println("Price    : " + unitCost);
        System.out.println("Value    : " + totalValue());
        System.out.println("-----------------------------");
    }
}

public class InventoryManager {

    private static final String DATA_FILE = "inventory.txt";
    private static List<ProductItem> items = new ArrayList<>();

    // Reads input from user and stores a new product
    static void insertItem(Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            System.out.print("Enter Price: ");
            double price = sc.nextDouble();

            items.add(new ProductItem(id, name, qty, price));
            System.out.println("Product stored.");

        } catch (Exception e) {
            System.out.println("Wrong input format. Please retry.");
            sc.nextLine();
        }
    }

    // Prints all products
    static void listItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (ProductItem p : items) {
            p.show();
        }
    }

    // Finds a product by its ID
    static void findItem(Scanner sc) {
        System.out.print("Search ID: ");
        int id = sc.nextInt();

        for (ProductItem p : items) {
            if (p.id() == id) {
                System.out.println("Item found:");
                p.show();
                return;
            }
        }
        System.out.println("No item found with given ID.");
    }

    // Calculates combined value of all products
    static void computeInventoryValue() {
        double sum = 0;
        for (ProductItem p : items) {
            sum += p.totalValue();
        }
        System.out.println("Total Inventory Value: " + sum);
    }

    // Writes product list to file
    static void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (ProductItem p : items) {
                bw.write(p.pack());
                bw.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save data.");
        }
    }

    // Loads products from file
    static void readFromFile() {
        File f = new File(DATA_FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                items.add(new ProductItem(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3])
                ));
            }
            System.out.println("Data loaded from file.");
        } catch (Exception e) {
            System.out.println("Unable to read data.");
        }
    }

    // Program starts here
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        readFromFile();

        while (true) {
            System.out.println("\n--- Inventory Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Show Products");
            System.out.println("3. Search Product");
            System.out.println("4. Total Inventory Value");
            System.out.println("5. Save to File");
            System.out.println("6. Exit");

            System.out.print("Choose option: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> insertItem(sc);
                case 2 -> listItems();
                case 3 -> findItem(sc);
                case 4 -> computeInventoryValue();
                case 5 -> writeToFile();
                case 6 -> {
                    writeToFile();
                    sc.close();
                    System.out.println("Program terminated.");
                    return;
                }
                default -> System.out.println("Invalid option selected.");
            }
        }
    }
}
