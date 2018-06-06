package models;

import db.CustomerDBHelper;
import db.DBHelper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bikes")
public class Bike extends StockItem{

    private String colour;
    private int capacity;
    private boolean isNew;

    public Bike() {
    }

    public Bike(String name, double price, String imageUrl, String colour, int capacity, boolean isNew) {
        super(name, price, imageUrl);
        this.colour = colour;
        this.capacity = capacity;
        this.isNew = isNew;
    }

    @Column(name = "colour")
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name = "is_new")
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

}
