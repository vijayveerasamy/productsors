package com.vijay.demo.productsors;

import com.vijay.demo.productsors.model.ProductOrder;
import com.vijay.demo.productsors.model.Product;
import com.vijay.demo.productsors.service.ProductStockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class ProductStockOrderApplication implements CommandLineRunner{

    ProductStockOrderService productStockOrderService;

    public static void main(String... args) {
        SpringApplication app = new SpringApplication(ProductStockOrderApplication.class);
        app.run(args);
    }

    public void run(String... args) {
        Optional<Product> optionalProduct = productStockOrderService.getProductByName("d");
        Product productA = optionalProduct.get();
        System.out.println("Product stock: "+ productA.getStock());

        productA.setStock(9L);
        //productStockOrderService.saveProduct(productA);

        ProductOrder productOrderA = new ProductOrder();

        Long orderVolume = 1000000L;
        productOrderA.setProduct(productA);
        productOrderA.setVolume(orderVolume);

        Optional<ProductOrder> optionalProductOrder = productStockOrderService.orderProduct(productOrderA);

        orderVolume = 1L;
        productOrderA.setVolume(orderVolume);
        optionalProductOrder = productStockOrderService.orderProduct(productOrderA);

        optionalProductOrder.ifPresent(po -> {System.out.println(po.getProduct().getName()+" ordered successfully.");});
    }

    public ProductStockOrderService getProductStockOrderService() {
        return productStockOrderService;
    }

    @Autowired
    public void setProductStockOrderService(ProductStockOrderService productStockOrderService) {
        this.productStockOrderService = productStockOrderService;
    }
}
