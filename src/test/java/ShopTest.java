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
//
//    String name, double price, int quantity, Shop shop, String colour, int capacity, boolean isNew

    @Before
    public void setUp() throws Exception {
        shop = new Shop("Jurassic Motorcycles", 0);
        bike = new Bike("Shredder", 99.9, 2, shop, "Black", 900, true);
        DBHelper.save(shop);
        DBHelper.save(bike);
    }

    @Test
    public void addToStock(){
        shop.addToStock(bike);
        assertEquals(1, shop.countStock());
    }

}
