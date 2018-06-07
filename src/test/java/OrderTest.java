import models.Bike;
import models.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    private Bike bike;
    private Order order;

    @Before
    public void setUp() throws Exception {
        bike = new Bike("Shredder", 99.9,  null, "Black", 900, true);
        order = new Order();
    }

//    @Test
//    public void addStockItemAndQuantityToCurrentOrder(){
//        order.addStockItemToOrder(bike);
//        assertEquals(10, shop.getStockQuantity(bike));
//    }

}
