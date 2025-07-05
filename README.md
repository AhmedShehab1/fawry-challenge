# Fawry Quantum E-Commerce System

![Java Version](https://img.shields.io/badge/Java-17%2B-blue)
![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)
![SOLID Principles](https://img.shields.io/badge/SOLID-compliant-success)
![Design Patterns](https://img.shields.io/badge/Decorator%20Pattern-implemented-blueviolet)


A modern Java implementation of an e-commerce system featuring product inventory management, shopping cart functionality, and checkout processing with shipping integration. Designed for Fawry Quantum Internship Challenge using SOLID principles and OOP best practices.

## Features

- **Product Management**
  - Create products with name, price, and quantity
  - Support for perishable (expiring) products
  - Support for shippable products with weight
  - Digital products (non-shippable, non-perishable)
  
- **Shopping Experience**
  - Add products to cart with quantity validation
  - Checkout process with receipt generation
  - Real-time inventory updates
  - Customer balance management

- **Shipping Integration**
  - Automatic shipment grouping for shippable items
  - Weight-based shipment notices
  - Extensible shipping service interface

- **Error Handling**
  - Empty cart validation
  - Insufficient balance checks
  - Stock availability verification
  - Expired product detection

## Getting Started

### Prerequisites

- Java 17 or higher

### Installation

```bash
git clone https://github.com/AhmedShehab1/fawry-challenge.git
cd fawry-challenge
```

### Running the Project

```java
// Sample Main.java
import java.time.LocalDate;
import com.ecommerce.model.product.*;
import com.ecommerce.cart.ShoppingCart;
import com.ecommerce.model.Customer;
import com.ecommerce.shipping.*;


public class Main {
    public static void main(String[] args) {

        // Create products
        Product cheese = new PerishableProductDecorator(
            new PhysicalProductDecorator(
                new BasicProduct("Cheese", 100, 10),
                0.4
            ),
            LocalDate.now().plusDays(10)
        );

        Product biscuits = new PerishableProductDecorator(
            new PhysicalProductDecorator(
                new BasicProduct("biscuits", 150, 5),
                0.7
            ),
            LocalDate.now().plusDays(20)
        );

        Product scratchCard = new BasicProduct("Scratch Card", 50, 100);
        
        // Create services
        ShippingService shippingService = new ConsoleShippingService();
        ShoppingCart cart = new ShoppingCart(shippingService);
        
        // Create customer
        Customer customer = new Customer("John", 500, cart);
        
        // Add items
        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);
        cart.addItem(scratchCard, 1);
        
        // Checkout
        customer.checkout();
    }
}
```
```bash
# Compile the project
javac -cp src src/com/ecommerce/MainApp.java

# Run the application
java -cp src com.ecommerce.Main


```
### Sample Output

```
** Shipment notice **
2x Cheese    400g
1x Biscuits    700g
Total package weight 1.5kg

** Checkout receipt **
2x Cheese    200
1x Biscuits    150
1x Scratch Card    50
---
Subtotal    400
Shipping    30
Amount    430
New balance: 70
```

### Design Decisions

1. **Decorator Pattern for Product Composition**
   - Core `Product` interface with basic properties
   - Capability interfaces: `Shippable` and `Expirable`
   - Decorator classes to add capabilities dynamically
   - `BasicProduct` as the foundation for all products
   - `PhysicalProductDecorator` adds shipping capabilities
   - `PerishableProductDecorator` adds expiration capabilities

2. **SOLID Principles**
   - **Single Responsibility**: Each class has one primary function
   - **Open/Closed**: New capabilities can be added without modifying existing code
   - **Liskov Substitution**: Decorators can be used interchangeably with base products
   - **Interface Segregation**: Shippable/Expirable as separate interfaces
   - **Dependency Inversion**: High-level modules depend on abstractions

3. **Error Handling**
   - Custom exceptions for domain-specific errors
   - Pre-check validation before state changes
   - Informative error messages with context
   - Fail-fast approach to prevent inconsistent state

4. **Shipping Service**
   - Interface-driven design for easy implementation swapping
   - Grouping logic for identical products in shipments
   - Unit conversion for consistent output

## Project Structure

```
src
├── main
│     └── com
│          └── ecommerce
│                   ├── model
│                   │   │   └── Customer.java
│                   │   └── product
│                   │       ├── Product.java
│                   │       ├── Shippable.java
│                   │       ├── Expirable.java
│                   │       ├── BasicProduct.java
│                   │       │── PhysicalProductDecorator.java
│                   │       │── PerishableProductDecorator.java
│                   ├── cart
│                   │   ├── ShoppingCart.java
│                   │   └── exceptions
│                   │       ├── CartException.java
│                   │       ├── EmptyCartException.java
│                   │       ├── InsufficientStockException.java
│                   │       ├── ExpiredProductException.java
│                   │       └── InsufficientBalanceException.java
│                   ├── shipping
│                   │   ├── ShippingService.java
│                   │   └── ConsoleShippingService.java
│                   └── MainApp.java
└── test
    └── java
        └── com
            └── fawry
                └── ecommerce
                    ├── model
                    │   └── product
                    │       ├── decorator
                    │       │   ├── PhysicalProductDecoratorTest.java
                    │       │   └── PerishableProductDecoratorTest.java
                    ├── cart
                    │   └── ShoppingCartTest.java
                    └── shipping
                        └── ConsoleShippingServiceTest.java
```

## Testing

The project includes comprehensive unit tests covering all major functionality:

### Key Test Cases

1. **Product Tests**
   - Expiration date validation
   - Stock reduction logic
   - Price calculation

2. **Shopping Cart Tests**
   - Add item with valid quantity
   - Prevent adding out-of-stock items
   - Block expired products at checkout
   - Reject checkout with insufficient balance

3. **Shipping Service Tests**
   - Correct item grouping
   - Accurate weight calculation
   - Proper output formatting

## Contribution Guidelines

1. **Fork** the repository and create your feature branch
2. **Write tests** for new functionality
3. **Follow Java coding conventions** with proper Javadoc
4. **Submit a pull request** with detailed description

### Best Practices
- Keep classes small and focused
- Prefer composition over inheritance
- Use dependency injection for services
- Write self-documenting code
- Maintain 80%+ test coverage

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
