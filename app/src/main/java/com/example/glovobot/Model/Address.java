package com.example.glovobot.Model;

import java.util.List;

public class Address {
    public String name;
    public String address;
    public String street;
    public double latitude;
    public double longitude;
    public List<AddressField> addressFields;

    public Address(String name, String address, String street, double latitude, double longitude, List<AddressField> addressFields) {
        this.name = name;
        this.address = address;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
        this.addressFields = addressFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<AddressField> getAddressFields() {
        return addressFields;
    }

    public void setAddressFields(List<AddressField> addressFields) {
        this.addressFields = addressFields;
    }
}
