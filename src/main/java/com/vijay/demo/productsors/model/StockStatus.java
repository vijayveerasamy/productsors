package com.vijay.demo.productsors.model;

public enum StockStatus {
    Blocked(0),
    Allowed(1);

    private Integer value;

    public Integer value() { return value; }

    StockStatus(Integer value) { this.value = value; }
}
