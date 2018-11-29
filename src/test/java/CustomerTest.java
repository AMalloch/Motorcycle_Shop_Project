import db.DBHelper;
import models.Order;
import models.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer customer;
    private Order order;

    @Before
    public void setUp() throws Exception {

        order = new Order();
        DBHelper.save(order);
        customer = new Customer("First", "Customer", "M", 49, "me@me.com", "wibble");
        DBHelper.save(customer);
    }

//    @After
//    public void tearDown() throws Exception {
//        DBHelper.deleteAll(Customer.class);
//    }

    @Test
    public void testCanAddCustomer() {
//        DBHelper.save(customer);
        assertEquals(1, DBHelper.getAll(Customer.class).size());
    }

//    @Test
//    public void testCanFindSpecificCustomer() {
////        Customer customer1 = new Customer("Found", "Customer", "M", 49, "me@mine.com");
////        DBHelper.save(customer);
//        Customer foundCustomer = DBHelper.find(customer.getId(), Customer.class);
//        assertEquals("First Customer", foundCustomer.displayName());
//    }
}
