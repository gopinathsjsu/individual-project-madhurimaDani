package edu.sjsu.mdani.entity;

public class Item {
    String category;
    String item;
    int availableQuantity;
    int price;

    public Item(String category, String item, int availableQuantity, int price) {
        this.category = category;
        this.item = item;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "category='" + category + '\'' +
                ", item='" + item + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", price=" + price +
                '}';
    }
}
