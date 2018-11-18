package models;

import db.DBHelper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baskets")
public class Basket {
    private int id;
    private Customer customer;
    private Set<CartItem> cartItems;

    public Basket() {
    }

    public Basket(Customer customer) {
        this.customer = customer;
        this.cartItems = new HashSet<>();
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "basket_stockItem", joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "stockItem_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<CartItem> getcartItems() {
        return cartItems;
    }

    public void setcartItems(Set<CartItem> cartItems) {
        this.cartItems = Basket.cartItems;
    }

    public int countItemsInBasket(){
        if (cartItems == null){
            return 0;
        }
        return cartItems.size();
    }

//    public void addItem(cartItems pendingItems, int pendingQuantity) {
//        if (availableStock(pendingItems, pendingQuantity)) {
//            pendingItems.setPendingPurchaseQuantity(pendingQuantity);
//            DBHelper.update(pendingItems);
//            cartItems.add(pendingItems);
//        }
//    }

//    public void deleteItem(cartItems item) {
//        item.setPendingPurchaseQuantity(0);
//        DBHelper.update(item);
//        cartItems.remove(item);
//    }

//    public boolean availableStock(cartItems requestedItem, int requestedQuantity){
//        if (requestedItem.getQuantity() >= requestedQuantity){
//            return true;
//        }
//        return false;
//    }
}
