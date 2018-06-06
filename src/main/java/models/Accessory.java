package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "accessories")
public class Accessory extends StockItem{

    public Accessory() {
    }

    public Accessory(String name, double price, String imageUrl) {
        super(name, price, imageUrl);
    }
}
