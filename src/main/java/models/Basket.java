package models;

import db.DBHelper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baskets")
public class Basket {
    private int id;
    private Customer customer;
    private ArrayList<Order> basketOrders;
    private Order order;

    public Basket() {
    }

    public Basket(Customer customer) {
        this.customer = customer;
        this.basketOrders = new ArrayList<>();
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
    @JoinColumn(name = "username")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name="basket_id", nullable=false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<Order> getBasketOrders() {
        return basketOrders;
    }

    public void setBasketOrders(ArrayList<Order> basketOrders) {
        this.basketOrders = basketOrders;
    }

    public void addOrderToBasket(Order order){
        this.basketOrders.add(order);
    }

    public void removeOrderFromBasketOrders(Order order) {
        this.basketOrders.remove(order);
    }

    public void removeAllOrders() {
        this.basketOrders.clear();
    }

    public int countOrders() {
        return this.basketOrders.size();
    }

}
