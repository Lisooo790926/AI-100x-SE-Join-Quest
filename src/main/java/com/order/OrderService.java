package com.order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<OrderItem> orderItems;
    private int thresholdAmount;
    private int discountAmount;
    private String buyOneGetOneCategory;
    private int doubleElevenMinQuantity;
    private int doubleElevenDiscountRate;
    
    public OrderService() {
        this.orderItems = new ArrayList<>();
        this.thresholdAmount = 0;
        this.discountAmount = 0;
        this.buyOneGetOneCategory = null;
        this.doubleElevenMinQuantity = 0;
        this.doubleElevenDiscountRate = 0;
    }
    
    public void configureThresholdDiscount(int threshold, int discount) {
        this.thresholdAmount = threshold;
        this.discountAmount = discount;
    }
    
    public void configureBuyOneGetOnePromotion(String category) {
        this.buyOneGetOneCategory = category;
    }
    
    public void configureDoubleElevenBulkDiscount(int minQuantity, int discountRate) {
        this.doubleElevenMinQuantity = minQuantity;
        this.doubleElevenDiscountRate = discountRate;
    }
    
    public void addProduct(Product product, int quantity) {
        orderItems.add(new OrderItem(product, quantity));
    }
    
    public OrderSummary calculateOrder() {
        OrderSummary summary = new OrderSummary();
        
        int originalAmount = 0;
        
        // Calculate original amount and apply buy one get one promotion
        for (OrderItem item : orderItems) {
            Product product = item.getProduct();
            int purchasedQuantity = item.getQuantity();
            int itemTotal = product.getUnitPrice() * purchasedQuantity;
            
            originalAmount += itemTotal;
            
            // Apply buy one get one promotion if applicable
            int finalQuantity = purchasedQuantity;
            if (buyOneGetOneCategory != null && product.getCategory().equals(buyOneGetOneCategory)) {
                // For buy one get one: each cosmetics product gets 1 free item
                // Regardless of how many purchased, only 1 bonus item per product type
                int bonusQuantity = 1;
                finalQuantity = purchasedQuantity + bonusQuantity;
            }
            
            summary.setProductQuantity(product.getName(), finalQuantity);
        }
        
        // Apply Double Eleven bulk discount if applicable
        int bulkDiscount = 0;
        if (doubleElevenMinQuantity > 0 && doubleElevenDiscountRate > 0) {
            for (OrderItem item : orderItems) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                
                if (quantity >= doubleElevenMinQuantity) {
                    // Calculate how many groups of 10 can get discount
                    int discountGroups = quantity / doubleElevenMinQuantity;
                    int discountedQuantity = discountGroups * doubleElevenMinQuantity;
                    
                    // Calculate discount amount for each group
                    int groupOriginalAmount = doubleElevenMinQuantity * product.getUnitPrice();
                    int discountPerGroup = groupOriginalAmount * doubleElevenDiscountRate / 100;
                    
                    bulkDiscount += discountPerGroup * discountGroups;
                }
            }
        }
        
        // Apply threshold discount if applicable
        int discount = 0;
        if (thresholdAmount > 0 && originalAmount >= thresholdAmount) {
            discount = discountAmount;
        }
        
        int totalAmount = originalAmount - discount - bulkDiscount;
        
        summary.setOriginalAmount(originalAmount);
        summary.setDiscount(discount);
        summary.setBulkDiscount(bulkDiscount);
        summary.setTotalAmount(totalAmount);
        
        return summary;
    }
    
    private static class OrderItem {
        private Product product;
        private int quantity;
        
        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
        
        public Product getProduct() {
            return product;
        }
        
        public int getQuantity() {
            return quantity;
        }
    }
} 