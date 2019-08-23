package com.vijay.demo.productsors.model;

public class StockCheck {
    private StockStatus status;
    private String message;

    public StockStatus getStatus() {
        return status;
    }

    public void setStatus(StockStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
