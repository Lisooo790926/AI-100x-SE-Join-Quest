package com.order;

import java.util.HashMap;
import java.util.Map;

public class OrderSummary {
    private int originalAmount;
    private int discount;
    private int bulkDiscount;
    private int totalAmount;
    private Map<String, Integer> productQuantities;
    
    public OrderSummary() {
        this.productQuantities = new HashMap<>();
        this.originalAmount = 0;
        this.discount = 0;
        this.bulkDiscount = 0;
        this.totalAmount = 0;
    }
    
    public int getOriginalAmount() {
        return originalAmount;
    }
    
    public void setOriginalAmount(int originalAmount) {
        this.originalAmount = originalAmount;
    }
    
    public int getDiscount() {
        return discount;
    }
    
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    public int getBulkDiscount() {
        return bulkDiscount;
    }
    
    public void setBulkDiscount(int bulkDiscount) {
        this.bulkDiscount = bulkDiscount;
    }
    
    public int getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public int getProductQuantity(String productName) {
        return productQuantities.getOrDefault(productName, 0);
    }
    
    public void setProductQuantity(String productName, int quantity) {
        productQuantities.put(productName, quantity);
    }
    
    public Map<String, Integer> getProductQuantities() {
        return new HashMap<>(productQuantities);
    }
} 