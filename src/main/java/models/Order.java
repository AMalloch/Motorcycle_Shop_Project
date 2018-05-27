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
    private GregorianCalendar orderDate;

    public Order(int id, String name, ArrayList<StockItem> stockItems, GregorianCalendar orderDate) {
        this.id = id;
        this.name = name;
        this.stockItems = stockItems;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "order_date")
    public GregorianCalendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(GregorianCalendar orderDate) {
        this.orderDate = orderDate;
    }

    @ManyToMany(mappedBy = "orders")
    public ArrayList<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(ArrayList<StockItem> stockItems) {
        this.stockItems = stockItems;
    }
}
