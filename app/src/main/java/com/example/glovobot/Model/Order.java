package com.example.glovobot.Model;

import java.util.List;

public class Order {
    public String status;
    public long orderId;
    public String orderCode;
    public String pickupCode;
    public ReferenceData referenceData;
    public Customer customer;
    public List<Product> products;
    public List<TransactionDetail> transactionDetails;
    public Object receiptUpload;
    public Object receiptPriceConfirmation;
    public String messageForHiddenProducts;
    public boolean canSeeDetails;
    public PickupDetails pickupDetails;
    public Object cancellationDetails;
    public String orderDescription;
}
