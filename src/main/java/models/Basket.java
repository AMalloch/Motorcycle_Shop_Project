package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

public class Basket {
    private int id;
    private int CustomerId;
    private Set<StockItem> basketItems;

    public Basket(int id, int customerId) {
        this.id = id;
        CustomerId = customerId;
        this.basketItems = new HashSet<>();
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

    @Column(name = "colour")
    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public Set<StockItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(Set<StockItem> basketItems) {
        this.basketItems = basketItems;
    }
}
