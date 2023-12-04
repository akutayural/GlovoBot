package com.example.glovobot.Model;

public class Customer {
    public String name;
    public Address address;
    public String deliveryInstructions;

    public Customer(String name, Address address, String deliveryInstructions) {
        this.name = name;
        this.address = address;
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }
}
