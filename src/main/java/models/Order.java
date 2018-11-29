package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "baskets")
public class Order {
    private int id;
    private Customer customer;
    private Set<CartItem> cartItems;

    public Order() {
    }

    public Order(Customer customer) {
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

    @ManyToOne
    @JoinColumn(name="order_id", nullable = true)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Set<CartItem> getcardItems() {
        return cartItems;
    }

    public void setcartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

//    public int countItemsInBasket(){
//        if (cartItems == null){
//            return 0;
//        }
//        return cartItems.size();
//    }

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
