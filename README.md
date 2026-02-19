# Inventory Management System

A lightweight command-line inventory management application built in Java for tracking products, quantities, and stock values.

## Features

- Add new products with ID, name, quantity, and price
- View all products in inventory
- Search products by ID
- Calculate total stock value
- Persistent storage using file-based data
- Duplicate ID validation
- Auto-save on exit

## Requirements

See [REQUIREMENTS.txt](REQUIREMENTS.txt) for detailed system requirements.

## Installation

1. Ensure Java Development Kit (JDK) 14 or higher is installed
2. Clone or download this repository
3. Navigate to the project directory

## Usage

### Compile the program

```bash
javac InventoryManagementSystem.java
```

### Run the application

```bash
java InventoryManagementSystem
```

### Menu Options

1. **Add product** - Register a new product with ID, name, quantity, and unit price
2. **View all products** - Display complete inventory list
3. **Find product by ID** - Search for a specific product
4. **Show total stock value** - Calculate the total worth of all inventory
5. **Save to file** - Manually save inventory data
6. **Exit** - Save and close the application

## Data Storage

Product data is automatically saved to `products.txt` in CSV format. The file is loaded on startup and saved on exit.

## Example

```
--- Inventory Menu ---
1. Add product
2. View all products
3. Find product by ID
4. Show total stock value
5. Save to file
6. Exit
Choose: 1

ID: 101
Name: Laptop
Quantity: 15
Unit price: 899.99
Item added to inventory.
```

## License

This project is open source and available for educational purposes.
