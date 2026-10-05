# Development Environments Project: Order Management
# 1. Project Overview
This project is a Java application for managing customers, orders, and products.
The system supports both physical and digital products. It allows customers to be associated with orders, products to be added or removed from orders, and the final price of an order to be calculated.
The project also includes functionality for calculating taxes, shipping costs, discounts, and final prices depending on the type of product.

### Main Features
- Customer management.
- Order management.
- Physical product management.
- Digital product management.
- Assignment of customers to orders.
- Adding and removing products from orders.
- Calculation of product final prices.
- Calculation of order totals.
- Calculation of VAT.
- Calculation of shipping costs for physical products.
- Customer loyalty discounts.
- Order summary generation.

# Key features: #
- Creation of models for customers, products, and orders.
- Supports digital products (with download size and license details) and physical products (with shipping costs).
- Automatically calculates the final price for each product and the entire order.
- Displays a comprehensive order summary.

# 2. Execution instructions
To view the project:
Open the project in Visual Studio or another editor.
Run the `Main.java` class using the "Run Java" command or the run icon.

To view the initial UML diagram:
Open `salida\proyecto_entornos.puml`.
Then, click on the search bar and enter `>PlantUML: Preview Current Diagram`.

To view the automatically generated diagram, follow the same steps but use the file `output\proyecto_entornos_AUTOMATICO.puml`.

# 3. Difference between the initial and automatically generated diagrams
# Initial diagram (proyecto_entornos.puml) #
This diagram shows class relationships, such as inheritance and aggregation.
It is simpler and easier to understand, but it does not list every method or include the `Main` class.

# Automatically generated diagram (proyecto_entornos_AUTOMATICO.puml) #
This version includes all methods, getters, setters, and constructors.
It also shows the `Main` class and illustrates the code structure.
It is more comprehensive but slightly harder to read due to the large amount of information.

# 4. Project Structure
The main source code is located in:

```text
src/main/java/proyecto_entornos/