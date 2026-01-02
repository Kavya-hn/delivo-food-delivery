package com.delivo.model;

public class CartItem {

    private int menuId;
    private String itemName;
    private String imagePath;
    private double price;          // Price per unit
    private int quantity;       // Quantity selected
    private double totalPrice;     // price * quantity
    private int restaurantId;

    public CartItem() {}

    public CartItem(int menuId, String itemName, double price, int quantity, String imagePath, int restaurantId) {
        this.menuId = menuId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = price * quantity;
        this.imagePath = imagePath;
        this.restaurantId = restaurantId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        updateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateTotal();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void updateTotal() {
        this.totalPrice = this.price * this.quantity;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
