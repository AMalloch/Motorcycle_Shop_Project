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
    private ArrayList<StockItem> stockItems;
    private ArrayList<Customer> customers;
    Double totalCash = 0.00;

    public Shop(String name, Double totalCash) {
        this.name = name;
        this.totalCash = totalCash;
        this.stockItems = new ArrayList<>();
        this.customers = new ArrayList<>();
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

    public ArrayList<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(ArrayList<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    @Column(name = "total_cash")
    public Double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Double totalCash) {
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
