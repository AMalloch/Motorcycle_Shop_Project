package models;

public class Bike extends StockItem{

    private String colour;
    private int capacity;
    private boolean isNew;

    public Bike() {
    }

    public Bike(String name, double price, int quantity, String colour, int capacity, boolean isNew) {
        super(name, price, quantity);
        this.colour = colour;
        this.capacity = capacity;
        this.isNew = isNew;
    }
}
