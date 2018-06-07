import db.DBHelper;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class BasketTest {

    private GregorianCalendar orderDate;
    private Bike bike;
    private Clothing clothing;
    private Order order;
    private Order order2;
    private Basket basket;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("John", "Smith", "M", 49, "me@me.com", "aintnosmith");
        bike = new Bike("Yamaha R1", 2995.99,  null, "blue", 1000, false);
        clothing = new Clothing("Black Jacket", 59.99,  null, "blue", "large", ClothingType.JACKET);
        order = new Order("John Order", orderDate, bike, 2);
        order2 = new Order("John Order", orderDate, clothing, 1);
        basket = new Basket(customer);
    }

    @Test
    public void canAddOrderToBasket() {
        basket.addOrderToBasket(order);
        assertEquals(1, basket.countOrders());
    }

    @Test
    public void canRemoveOrderFromBasket() {
        basket.addOrderToBasket(order);
        basket.addOrderToBasket(order);
        basket.addOrderToBasket(order2);
        basket.removeOrderFromBasketOrders(order);
        assertEquals(2, basket.countOrders());
    }

    @Test
    public void canRemoveAllOrdersFromBasket() {
        basket.addOrderToBasket(order);
        basket.addOrderToBasket(order);
        basket.addOrderToBasket(order2);
        basket.removeAllOrders();
        assertEquals(0, basket.countOrders());
    }

//    @Test
//    public void testCanGetBasketItems() {
//        assertEquals(1, DBHelper.findBasketItems(2).size());
//    }
}
