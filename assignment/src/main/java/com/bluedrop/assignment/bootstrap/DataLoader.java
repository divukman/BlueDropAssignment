package com.bluedrop.assignment.bootstrap;

import com.bluedrop.assignment.entities.*;
import com.bluedrop.assignment.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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

    @Transactional
    protected void loadData() {
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

        final Order order1 = Order.builder()
                .email("dimitar@gmail.com")
                .state(State.ACTIVE)
                .build();

        final Order order2 = Order.builder()
                .email("ante@gmail.com")
                .state(State.ACTIVE)
                .build();

        OrderProduct orderProduct1 = OrderProduct.builder()
                .order(order1)
                .product(product2)
                .quantity(5)
                .build();

        OrderProduct orderProduct2 = OrderProduct.builder()
                .order(order1)
                .product(product3)
                .quantity(2)
                .build();

        OrderProduct orderProduct3 = OrderProduct.builder()
                .order(order2)
                .product(product1)
                .quantity(10)
                .build();

        OrderProduct orderProduct4 = OrderProduct.builder()
                .order(order2)
                .product(product4)
                .quantity(15)
                .build();

        Set<OrderProduct> orderProductSet = new HashSet<>();
        orderProductSet.add(orderProduct1);
        orderProductSet.add(orderProduct2);
        orderProductSet.add(orderProduct3);
        orderProductSet.add(orderProduct4);

        orderProductRepository.saveAll(orderProductSet);

    }
}
