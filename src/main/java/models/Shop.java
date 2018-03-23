package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop")
public class Shop {

    int id;
    String name;
    List<StockItem> stockItems = new ArrayList<StockItem>();
    List<Customer> customers = new ArrayList<Customer>();
    int totalCash = 0;

    public Shop(String name, int totalCash) {
        this.name = name;
        this.totalCash = totalCash;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public Shop(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "shop")
    @Column(name = "stock_items")
    public List<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(List<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    @OneToMany(mappedBy = "shop")
    @Column(name = "customers")
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Column(name = "total_cash")
    public int getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }
}
