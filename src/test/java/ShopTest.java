import db.DBHelper;
import models.Bike;
import models.Customer;
import models.Shop;
import models.StockItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopTest {

    private Shop shop;
    private Bike bike;
    private Customer customer;
//
//    String name, double price, int quantity, Shop shop, String colour, int capacity, boolean isNew

    @Before
    public void setUp() throws Exception {
        shop = new Shop("Jurassic Motorcycles", 0);
        bike = new Bike("Shredder", 99.9, 2, "Black", 900, true, null);
        customer = new Customer("Jane", "Losi", "F", 26, "fortheducksgmail.com");
//        String firstName, String lastName, String gender, int age, String emailAddress
    }

    @Test
    public void addToStock(){
        shop.addToStock(bike);
        assertEquals(1, shop.countStock());
    }

    @Test
    public void addCustomer(){
        shop.addCustomer(customer);
        assertEquals(1, shop.countCustomers());
    }

//    @Test
//    public void canDeleteStock(){
//        shop.addToStock(bike);
//        shop.addToStock(bike);
//        DBHelper.delete(bike);
//        assertEquals(1, shop.countStock());
//    }
//
//    @Test
//    public void canDeleteCustomer(){
//        shop.addCustomer(customer);
//        shop.addCustomer(customer);
//        DBHelper.delete(customer);
//        assertEquals(1, shop.countCustomers());
//    }

    @Test
    public void canGetTotalCash(){
        assertEquals(0, shop.getTotalCash());
    }

    @Test
    public void canGetShopName(){
        assertEquals("Jurassic Motorcycles", shop.getName());
    }

}
