package com.example.glovobot.Model;

public class Product {
    public String name;
    public String description;
    public int quantity;
    public String thumbnailUrl;
    public String imageUrl;
    public String imageId;

    public Product(String name, String description, int quantity, String thumbnailUrl, String imageUrl, String imageId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.thumbnailUrl = thumbnailUrl;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
