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
    private Set<StockItem> currentOrder;
    private GregorianCalendar orderDate;

    public Order(int id, String name, GregorianCalendar orderDate) {
        this.id = id;
        this.name = name;
        this.currentOrder = new HashSet<>();
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "order_stockItem", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "stockItem_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<StockItem> getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Set<StockItem> currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void addStockItemToOrder(StockItem orderItem){
        this.currentOrder.add(orderItem);
    }

    public void removeStockItemFromOrder(StockItem orderItem){
        this.currentOrder.remove(orderItem);
    }

}
