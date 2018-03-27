package models;

import db.DBHelper;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baskets")
public class Basket {
    private int id;
    private Customer customer;
    private Set<StockItem> stockItems;

    public Basket() {
    }

    public Basket(Customer customer) {
        this.customer = customer;
        this.stockItems = new HashSet<>();
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "basket", cascade = CascadeType.ALL)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy = "basket")
    public Set<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public int countItemsInBasket(){
        if (stockItems == null){
            return 0;
        }
        return stockItems.size();
    }

    public void addItem(StockItem pendingItems) {
        stockItems.add(pendingItems);
    }
}
