package models;

import java.util.Set;

public class CartItem {

    private Set<StockItem> stockItems;
    private int quantity;
    private Basket basket;

    public CartItem(Set<StockItem> stockItems, int quantity, Basket basket) {
        this.stockItems = stockItems;
        this.quantity = quantity;
        this.basket = basket;
    }

    public Set<StockItem> getStockItems() {
        return stockItems;
    }

    public void setStockItems(Set<StockItem> stockItems) {
        this.stockItems = stockItems;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
