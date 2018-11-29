package models;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    private int id;
    private StockItem stockItem;
    private int quantity;
    private Order order;

    public CartItem(int id, StockItem stockItem, int quantity, Order order) {
        this.id = id;
        this.stockItem = stockItem;
        this.quantity = quantity;
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name="stockItem_id", nullable = true)
    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
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
