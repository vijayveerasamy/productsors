package com.vijay.demo.productsors.service;

import com.vijay.demo.productsors.model.StockCheck;
import com.vijay.demo.productsors.model.StockStatus;
import com.vijay.demo.productsors.model.ProductOrder;
import com.vijay.demo.productsors.model.Product;
import com.vijay.demo.productsors.repository.ProductOrderRepository;
import com.vijay.demo.productsors.repository.ProductRepository;
import com.vijay.demo.productsors.rules.DroolsBeanFactory;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProductStockOrderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductOrderRepository productOrderRepository;

    @Autowired
    DroolsBeanFactory droolsBeanFactory;

    public Optional<Product> getProductByName(String name) {
        return Optional.of(productRepository.findByName(name));
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<ProductOrder> saveOrder(ProductOrder order) {
        return Optional.of(productOrderRepository.save(order));
    }

    public Optional<ProductOrder> getOrderById(Long id) {
        return productOrderRepository.findById(id);
    }

    public Iterable<ProductOrder> getOrders() {
        return productOrderRepository.findAll();
    }


    public StockCheck checkProductStock(ProductOrder productOrder) {
        StockCheck stockCheck = new StockCheck();
        stockCheck.setStatus(StockStatus.Blocked);

        KieSession kieSession=droolsBeanFactory.getKieSession();

        kieSession.insert(productOrder);

        kieSession.setGlobal("stockCheck", stockCheck);
        kieSession.fireAllRules();

        kieSession.dispose();

        return stockCheck;
    }

    public Optional<ProductOrder> orderProduct(ProductOrder productOrder) {
        if(checkProductStock(productOrder).getStatus()== StockStatus.Allowed) {
            Optional<ProductOrder> optionalProductOrder = saveOrder(productOrder);
            optionalProductOrder.ifPresent(po -> {
                po.getProduct().setStock(po.getProduct().getStock()-po.getVolume());
                saveProduct(po.getProduct());
            });
            return optionalProductOrder;
        }
        return Optional.empty();
    }
}
