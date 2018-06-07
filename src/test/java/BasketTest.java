import db.DBHelper;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    private GregorianCalendar orderDate;
    private Bike bike;
    private Order order;
    private Basket basket;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("John", "Smith", "M", 49, "me@me.com", "aintnosmith");
        order = new Order("John Order", orderDate, bike, 2);
        bike = new Bike("Yamaha R1", 2995.99,  null, "blue", 1000, false);
        basket = new Basket(customer);
    }

    @Test
    public void canAddOrderToBasket() {
        basket.addOrderToBasket(order);
        assertEquals(order, basket.getBasketOrders());
    }

//    @Test
//    public void testCanGetBasketItems() {
//        assertEquals(1, DBHelper.findBasketItems(2).size());
//    }
}
