package com.example.techstore;

public class Product {

    private int id;
    private String name;
    private double price;
    private float rating;
    private String category;
    private String description;
    private int imageResId;
    private boolean isFavorite;

    public Product(int id, String name, double price, float rating,
                   String category, String description, int imageResId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.category = category;
        this.description = description;
        this.imageResId = imageResId;
        this.isFavorite = false;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public float getRating() { return rating; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public int getImageResId() { return imageResId; }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
}