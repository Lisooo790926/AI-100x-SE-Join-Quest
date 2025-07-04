package com.order.steps;

import com.order.OrderService;
import com.order.OrderSummary;
import com.order.Product;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderSteps {
    
    private OrderService orderService;
    private OrderSummary orderSummary;
    
    public OrderSteps() {
        this.orderService = new OrderService();
    }
    
    @Given("no promotions are applied")
    public void no_promotions_are_applied() {
        // No promotions setup needed for this scenario
    }
    
    @Given("the threshold discount promotion is configured:")
    public void the_threshold_discount_promotion_is_configured(DataTable dataTable) {
        List<Map<String, String>> promotionData = dataTable.asMaps(String.class, String.class);
        Map<String, String> promotion = promotionData.get(0);
        
        int threshold = Integer.parseInt(promotion.get("threshold"));
        int discount = Integer.parseInt(promotion.get("discount"));
        
        // Configure threshold discount promotion - implementation will be added later
        orderService.configureThresholdDiscount(threshold, discount);
    }
    
    @Given("the buy one get one promotion for cosmetics is active")
    public void the_buy_one_get_one_promotion_for_cosmetics_is_active() {
        // Configure buy one get one promotion for cosmetics category
        orderService.configureBuyOneGetOnePromotion("cosmetics");
    }
    
    @Given("the Double Eleven bulk discount promotion is active:")
    public void the_double_eleven_bulk_discount_promotion_is_active(DataTable dataTable) {
        List<Map<String, String>> promotionData = dataTable.asMaps(String.class, String.class);
        Map<String, String> promotion = promotionData.get(0);
        
        int minQuantity = Integer.parseInt(promotion.get("minQuantity"));
        int discountRate = Integer.parseInt(promotion.get("discountRate"));
        
        // Configure Double Eleven bulk discount promotion
        orderService.configureDoubleElevenBulkDiscount(minQuantity, discountRate);
    }
    
    @When("a customer places an order with:")
    public void a_customer_places_an_order_with(DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> productData : products) {
            String productName = productData.get("productName");
            int quantity = Integer.parseInt(productData.get("quantity"));
            int unitPrice = Integer.parseInt(productData.get("unitPrice"));
            String category = productData.getOrDefault("category", "general");
            
            Product product = new Product(productName, category, unitPrice);
            orderService.addProduct(product, quantity);
        }
        orderSummary = orderService.calculateOrder();
    }
    
    @Then("the order summary should be:")
    public void the_order_summary_should_be(DataTable dataTable) {
        List<Map<String, String>> expectedSummary = dataTable.asMaps(String.class, String.class);
        Map<String, String> expected = expectedSummary.get(0);
        
        if (expected.containsKey("totalAmount")) {
            int expectedTotal = Integer.parseInt(expected.get("totalAmount"));
            assertEquals(expectedTotal, orderSummary.getTotalAmount());
        }
        
        if (expected.containsKey("originalAmount")) {
            int expectedOriginal = Integer.parseInt(expected.get("originalAmount"));
            assertEquals(expectedOriginal, orderSummary.getOriginalAmount());
        }
        
        if (expected.containsKey("discount")) {
            int expectedDiscount = Integer.parseInt(expected.get("discount"));
            assertEquals(expectedDiscount, orderSummary.getDiscount());
        }
        
        if (expected.containsKey("bulkDiscount")) {
            int expectedBulkDiscount = Integer.parseInt(expected.get("bulkDiscount"));
            assertEquals(expectedBulkDiscount, orderSummary.getBulkDiscount());
        }
    }
    
    @And("the customer should receive:")
    public void the_customer_should_receive(DataTable dataTable) {
        List<Map<String, String>> expectedProducts = dataTable.asMaps(String.class, String.class);
        
        for (Map<String, String> expectedProduct : expectedProducts) {
            String productName = expectedProduct.get("productName");
            int expectedQuantity = Integer.parseInt(expectedProduct.get("quantity"));
            
            int actualQuantity = orderSummary.getProductQuantity(productName);
            assertEquals(expectedQuantity, actualQuantity);
        }
    }
} 