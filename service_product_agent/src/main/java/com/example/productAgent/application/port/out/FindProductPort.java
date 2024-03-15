package com.example.productAgent.application.port.out;

import com.example.productAgent.domain.Product;
import java.util.List;

public interface FindProductPort {

    List<Product> findAllByLowInventory();
}
