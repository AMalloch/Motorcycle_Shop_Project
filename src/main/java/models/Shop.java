package models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "shop")
public class Shop {

    private int id;
    private String name;
    private ArrayList<Customer> customers;
    private ArrayList<StockItem> stockItems;
    private ArrayList<Order> shopOrderHistory;
    Double totalCash = 0.00;

    public Shop(String name, Double totalCash) {
        this.name = name;
        this.totalCash = totalCash;
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

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Order> getShopOrderHistory() {
        return shopOrderHistory;
    }

    public void setShopOrderHistory(ArrayList<Order> shopOrderHistory) {
        this.shopOrderHistory = shopOrderHistory;
    }
}
