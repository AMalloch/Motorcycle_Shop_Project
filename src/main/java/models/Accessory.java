package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accessories")
public class Accessory extends StockItem{

    public Accessory() {
    }

    public Accessory(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}
