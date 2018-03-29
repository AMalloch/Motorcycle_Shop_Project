package models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class StockItem {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String imageUrl;
    private int pendingPurchaseQuantity;
    private Set<Basket> baskets;

    public StockItem() {
    }

    public StockItem(String name, double price, int quantity, String imageUrl) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.pendingPurchaseQuantity = 0;
        this.baskets = new HashSet<>();
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

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "pp_quantity")
    public int getPendingPurchaseQuantity() {
        return pendingPurchaseQuantity;
    }

    public void setPendingPurchaseQuantity(int pendingPurchaseQuantity) {
        this.pendingPurchaseQuantity = pendingPurchaseQuantity;
    }

    @ManyToMany(cascade = CascadeType.REMOVE , mappedBy = "stockItems", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Set<Basket> baskets) {
        this.baskets = baskets;
    }

    @Override
    public boolean equals(Object obj) {
        StockItem otherItem = (StockItem) obj;

        if(otherItem.getId() == this.getId()){
            return true;
        }

        return false;
    }


    //    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "shop_id", nullable = false)
//    public Shop getShop() {
//        return shop;
//    }
//
//    public void setShop(Shop shop) {
//        this.shop = shop;
//    }
}
