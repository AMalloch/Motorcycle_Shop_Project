package models;

import db.CustomerDBHelper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clothing")
public class Clothing extends StockItem {

    private String colour;
    private String size;
    private ClothingType type;

    public Clothing() {
    }

    public Clothing(String name, double price, String imageUrl, String colour, String size, ClothingType type) {
        super(name, price, imageUrl);
        this.colour = colour;
        this.size = size;
        this.type = type;
    }

    @Column(name = "colour")
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Column(name = "size")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public ClothingType getType() {
        return type;
    }

    public void setType(ClothingType type) {
        this.type = type;
    }
}
