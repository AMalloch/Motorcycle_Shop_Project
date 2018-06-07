package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    private int id;
    private String name;
    private StockItem cartItem;
    private GregorianCalendar orderDate;
    private int quantity;

    public Order(String name, GregorianCalendar orderDate, StockItem cartItem, int quantity) {
        this.id = id;
        this.name = name;
        this.cartItem = cartItem;
        this.orderDate = orderDate;
        this.quantity = quantity;
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

    @ManyToOne(optional=false)
    @JoinColumn(name="stockItem_id", referencedColumnName="id")
    public StockItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(StockItem cartItem) {
        this.cartItem = cartItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
