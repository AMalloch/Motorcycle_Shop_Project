package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shop")
public class Shop {

    private int id;
    private String name;
    private Set<StockItem> stockItems;
    private Set<Customer> customers;
    int totalCash = 0;

    public Shop(String name, int totalCash) {
        this.name = name;
        this.totalCash = totalCash;
        this.stockItems = new HashSet<>();
        this.customers = new HashSet<>();
    }

    public Shop() {
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

    @OneToMany(mappedBy = "shop")
    public Set<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    @Column(name = "total_cash")
    public int getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }

    public int countStock() {
        return this.stockItems.size();
    }

    public int countCustomers() {
        return this.customers.size();
    }

    public void addToStock(StockItem stockItem) {
        this.stockItems.add(stockItem);
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }
}
