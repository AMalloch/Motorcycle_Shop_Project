import db.DBHelper;
import models.Customer;
import models.Shop;
import models.StockItem;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class ShopTest {

    private Shop shop;

    @Before
    public void setUp() throws Exception {
        shop = new Shop("Jurassic Motorcycles", 0);
        DBHelper.save(shop);
    }

}
