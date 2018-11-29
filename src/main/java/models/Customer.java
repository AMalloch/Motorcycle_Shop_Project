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
    private String username;
    private Set<Order> order;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String gender, int age, String emailAddress, String username, Set<Order> order) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.emailAddress = emailAddress;
        this.username = username;
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

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    //    @Column(name = "purchased_items")
//    public Set<StockItem> getPurchasedItems() {
//        return purchasedItems;
//    }
//
//    public void setPurchasedItems(Set<StockItem> purchasedItems) {
//        this.purchasedItems = purchasedItems;
//    }

    public String displayName(){
        return getFirstName() + " " + getLastName();
    }

//    public static void addToBasket(StockItem item, Order order){
//        order.addItem(item);
//        item.setBaskets(order);
//        DBHelper.saveOrUpdate(order);
//        DBHelper.saveOrUpdate(item);
//    }

//    @Column(name = "shop")
//    public Shop getShop() {
//        return shop;
//    }
//
//    public void setShop(Shop shop) {
//        this.shop = shop;
//    }

//    public List<Bike> findAllAccessories(){
//        List<Bike> allAvailableAccessories = CustomerDBHelper.getAvailableStock(Accessory.class);
//        return allAvailableAccessories;
//    }
//
//    public List<Bike> findAllClothing(){
//        List<Bike> allAvailableClothing = CustomerDBHelper.getAvailableStock(Clothing.class);
//        return allAvailableClothing;
//    }

}
