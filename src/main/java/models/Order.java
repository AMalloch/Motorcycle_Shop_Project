package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

@Entity
@Table(name = "Order")
public class Order {

    private int id;
    private String name;
    private ArrayList<StockItem> stockItems;
    private GregorianCalendar stockDate;

    public Order(int id, String name, ArrayList<StockItem> stockItems, GregorianCalendar stockDate) {
        this.id = id;
        this.name = name;
        this.stockItems = stockItems;
        this.stockDate = stockDate;
    }

    public Order() {
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

    public GregorianCalendar getStockDate() {
        return stockDate;
    }

    public void setStockDate(GregorianCalendar stockDate) {
        this.stockDate = stockDate;
    }

    public ArrayList<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(ArrayList<StockItem> stockItems) {
        this.stockItems = stockItems;
    }
}
