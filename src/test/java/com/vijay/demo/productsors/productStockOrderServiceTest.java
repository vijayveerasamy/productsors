package com.vijay.demo.productsors;

import com.vijay.demo.productsors.model.Product;
import com.vijay.demo.productsors.model.ProductOrder;
import com.vijay.demo.productsors.service.ProductStockOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class productStockOrderServiceTest {

    @Autowired
    ProductStockOrderService productStockOrderService;

    @Test
    public void getProductByName_should_work() {
        Optional<Product> optionalProduct = productStockOrderService.getProductByName("a");
        optionalProduct.ifPresent(op -> assertEquals(op.getName(), "a"));
    }

    @Test
    public void saveProductOrderA_should_work() {
        Optional<Product> optionalProduct = productStockOrderService.getProductByName("a");

        optionalProduct.ifPresent(op -> assertEquals(op.getStock().longValue(), 5));

        Product product = optionalProduct.get();

        Long orderVolume = 1L;

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setVolume(orderVolume);

        Optional<ProductOrder> optionalProductOrder = productStockOrderService.orderProduct(productOrder);

        Optional<ProductOrder> productOrder2 = productStockOrderService.getOrderById(optionalProductOrder.get().getId());

        Assert.assertTrue(productOrder2.isPresent());
        productOrder2.ifPresent(o -> {
            assertEquals(o.getVolume().longValue(), 1);});

        optionalProduct = productStockOrderService.getProductByName("a");

        product = optionalProduct.get();

        assertEquals(product.getStock().longValue(), 4);
    }

    @Test
    public void saveProductOrderC_should_failC() {
        Optional<Product> optionalProduct = productStockOrderService.getProductByName("c");

        optionalProduct.ifPresent(op -> assertEquals(op.getName(), "c"));

        Product product = optionalProduct.get();

        Long orderVolume = 1L;

        ProductOrder productOrder = new ProductOrder();
        productOrder.setProduct(product);
        productOrder.setVolume(orderVolume);

        Optional<ProductOrder> optionalProductOrder = productStockOrderService.orderProduct(productOrder);

        //orderProduct() didn't succeed because product(c) is blocked for order
        Assert.assertFalse(optionalProductOrder.isPresent());

        optionalProduct = productStockOrderService.getProductByName("c");

        //Stock didn't change after ordering the product because of product (c) is blocked for ordering
        assertEquals(optionalProduct.get().getStock().longValue(), product.getStock().longValue());
    }
}
