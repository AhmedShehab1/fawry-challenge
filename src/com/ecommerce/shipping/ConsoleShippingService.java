package com.ecommerce.shipping;

import java.util.Map;

import com.ecommerce.model.product.Shippable;

public class ConsoleShippingService implements ShippingService {

    @Override
    public void shipItems(Map<Shippable, Integer> items) {

        System.out.println("** Shipment notice **");

        double totalWeight = 0;
            for (Map.Entry<Shippable, Integer> entry : items.entrySet()) {
                String name = entry.getKey().getName();
                int quantity = entry.getValue();
                double weight = entry.getKey().getWeight();
                totalWeight += weight * quantity;
                System.out.printf("%dx %-20s %6.0fg%n", 
                quantity, name, weight * 1000);
            }
            System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }
   
}
