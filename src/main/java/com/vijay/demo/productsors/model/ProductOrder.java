package com.vijay.demo.productsors.model;

import javax.persistence.*;

@Table(name="ProductOrder")
@Entity
public class ProductOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Product product;

    private Long volume;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}

