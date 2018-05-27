package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StockItem {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String imageUrl;
    private ArrayList<Order> orderOfStockItem;

    public StockItem() {
    }

    public StockItem(int id, String name, double price, int quantity, String imageUrl, ArrayList<Order> orderOfStockItem) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.orderOfStockItem = orderOfStockItem;
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

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "StockItem_Order",
            joinColumns = { @JoinColumn(name = "stockItem_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )
    public ArrayList<Order> getOrderOfStockItem() {
        return orderOfStockItem;
    }

    public void setOrderOfStockItem(ArrayList<Order> orderOfStockItem) {
        this.orderOfStockItem = orderOfStockItem;
    }

    @Override
    public boolean equals(Object obj) {
        StockItem otherItem = (StockItem) obj;
        if(otherItem.getId() == this.getId()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
