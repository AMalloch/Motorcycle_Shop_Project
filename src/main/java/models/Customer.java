package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String emailAddress;

    private Set<StockItem> purchasedItems;
    private Shop shop;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String gender, int age, String emailAddress, Shop shop) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.emailAddress = emailAddress;
        this.shop = shop;
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

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "email_address")
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Column(name = "display_name")
    public String displayName(){
        return getFirstName() + " " + getLastName();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    public Set<StockItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(Set<StockItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    @Column(name = "shop")
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
