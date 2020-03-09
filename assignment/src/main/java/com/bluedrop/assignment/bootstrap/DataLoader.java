package com.bluedrop.assignment.bootstrap;

import com.bluedrop.assignment.entities.*;
import com.bluedrop.assignment.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Class for bootstrapping the application data. Active only for the dev application profile.
 *
 * @author Dimitar
 */
@RequiredArgsConstructor
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    private static final String PRODUCT_1_SKU = "Pro_Pr_CrM22";
    private static final String PRODUCT_2_SKU = "Pro_Pr_CrM23";
    private static final String PRODUCT_3_SKU = "Pro_Pr_CrM24";
    private static final String PRODUCT_4_SKU = "Pro_Pr_CrM25";

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            loadData();
        }
    }

    private void loadData() {
        final Product product1 = Product.builder()
                .name("Product 1")
                .sku(DataLoader.PRODUCT_1_SKU)
                .price(new BigDecimal("33.27"))
                .state(State.ACTIVE)
                .build();

        final Product product2 = Product.builder()
                .name("Product 2")
                .sku(DataLoader.PRODUCT_2_SKU)
                .price(new BigDecimal("38.33"))
                .state(State.ACTIVE)
                .build();

        final Product product3 = Product.builder()
                .name("Product 3")
                .sku(DataLoader.PRODUCT_3_SKU)
                .price(new BigDecimal("89.99"))
                .state(State.ACTIVE)
                .build();

        final Product product4 = Product.builder()
                .name("Product 4")
                .sku(DataLoader.PRODUCT_4_SKU)
                .price(new BigDecimal("170.37"))
                .state(State.ACTIVE)
                .build();

        productRepository.save(product1);
        //productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        final Order order1 = Order.builder()
                .email("john@gmail.com")
                .state(State.ACTIVE)
                .build();

        //orderRepository.save(order1);

        OrderProduct orderProduct1 = OrderProduct.builder()
                .order(order1)
                .product(product2)
                .quantity(5)
                .build();

        Set<OrderProduct> orderProductSet = new HashSet<>();
        orderProductSet.add(orderProduct1);

        order1.setOrderProducts(orderProductSet);
        product2.setOrderProducts(orderProductSet);

        orderProductRepository.saveAll(orderProductSet);
    }
}
