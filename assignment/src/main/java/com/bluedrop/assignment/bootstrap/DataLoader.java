package com.bluedrop.assignment.bootstrap;

import com.bluedrop.assignment.entities.Product;
import com.bluedrop.assignment.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Class for bootstrapping the application data. Active only for the dev application profile.
 *
 * @author Dimitar
 */
@RequiredArgsConstructor
@Component
@Profile("dev")
public class DataLoader implements CommandLineRunner {
    public static final String PRODUCT_1_SKU = "Pro/Pr-CrM22";
    public static final String PRODUCT_2_SKU = "Pro/Pr-CrM23";
    public static final String PRODUCT_3_SKU = "Pro/Pr-CrM24";
    public static final String PRODUCT_4_SKU = "Pro/Pr-CrM25";


    private final ProductRepository productRepository;


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
                .build();

        final Product product2 = Product.builder()
                .name("Product 2")
                .sku(DataLoader.PRODUCT_2_SKU)
                .price(new BigDecimal("38.33"))
                .build();

        final Product product3 = Product.builder()
                .name("Product 3")
                .sku(DataLoader.PRODUCT_3_SKU)
                .price(new BigDecimal("89.99"))
                .build();

        final Product product4 = Product.builder()
                .name("Product 4")
                .sku(DataLoader.PRODUCT_4_SKU)
                .price(new BigDecimal("170.37"))
                .build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }
}
