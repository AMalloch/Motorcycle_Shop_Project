package models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Order")
public class Order {

    private int id;
    private String name;
    private ArrayList<StockItem> stockItems;

    public Order(int id, String name, ArrayList<StockItem> stockItems) {
        this.id = id;
        this.name = name;
        this.stockItems = stockItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(ArrayList<StockItem> stockItems) {
        this.stockItems = stockItems;
    }
}
