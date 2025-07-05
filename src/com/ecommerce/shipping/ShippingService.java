package com.ecommerce.shipping;
import java.util.Map;

import com.ecommerce.model.product.Shippable;


public interface ShippingService {
    void shipItems(Map<Shippable, Integer> items);
}
