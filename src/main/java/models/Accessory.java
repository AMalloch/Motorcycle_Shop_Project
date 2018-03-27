package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "accessories")
public class Accessory extends StockItem{

    public Accessory() {
    }

    public Accessory(String name, double price, int quantity, String imageUrl, Basket basket) {
        super(name, price, quantity, imageUrl, basket);
    }
}
