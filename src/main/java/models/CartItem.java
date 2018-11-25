package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cart_items")
public class CartItem {

    private int id;
    private Set<StockItem> stockItems;
    private int quantity;
    private Order order;

    public CartItem(int id, Set<StockItem> stockItems, int quantity, Order order) {
        this.id = id;
        this.stockItems = stockItems;
        this.quantity = quantity;
        this.order = order;
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


    public Set<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
