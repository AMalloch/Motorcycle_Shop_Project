package models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Basket {
    int id;
    int CustomerId;
    List<StockItem> basketItems;

    public Basket(int id, int customerId, List<StockItem> basketItems) {
        this.id = id;
        CustomerId = customerId;
        this.basketItems = basketItems;
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

    public List<StockItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<StockItem> basketItems) {
        this.basketItems = basketItems;
    }
}
