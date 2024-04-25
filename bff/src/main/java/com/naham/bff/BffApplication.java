package com.naham.bff;


import com.naham.bff.model.entity.Product;
import com.naham.bff.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BffApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .id(1)
                .price(BigDecimal.valueOf(30))
                .name("T-Shirt")
                .imageUrl("https://xcdn.next.co.uk/COMMON/Items/Default/Default/ItemImages/AltItemShot/315x472/235459s2.jpg")
                .build());
        products.add(Product.builder()
                .id(2)
                .price(BigDecimal.valueOf(65))
                .name("Pants")
                .imageUrl("https://www.helikon-tex.com/media/catalog/product/cache/6/image/9df78eab33525d08d6e5fb8d27136e95/s/p/sp-pgm-dc-11.jpg")
                .build());
        products.add(Product.builder()
                .id(3)
                .price(BigDecimal.valueOf(70))
                .name("Hoodie")
                .imageUrl("https://studios-tc.com/wp-content/uploads/2021/10/oversize-hoodie-black-back.jpg")
                .build());
        productRepository.saveAll(products);
    }
}
