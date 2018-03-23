package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clothing")
public class Clothing extends StockItem {

    private String colour;
    private String size;
    private ClothingType type;

    public Clothing() {
    }

    public Clothing(String name, double price, int quantity, String colour, String size, ClothingType type) {
        super(name, price, quantity);
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

    @Column(name = "type")
    public ClothingType getType() {
        return type;
    }

    public void setType(ClothingType type) {
        this.type = type;
    }
}
