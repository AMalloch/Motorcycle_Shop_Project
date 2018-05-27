package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "accessories")
public class Accessory extends StockItem{

    public Accessory() {
    }

    public Accessory(int id, String name, double price, int quantity, String imageUrl, ArrayList<Order> orderOfStockItem) {
        super(id, name, price, quantity, imageUrl, orderOfStockItem);
    }

}
