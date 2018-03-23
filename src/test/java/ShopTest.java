import db.DBHelper;
import models.Customer;
import models.Shop;
import org.junit.Before;

public class ShopTest {

    private Shop shop;

    @Before
    public void setUp() throws Exception {
        shop = new Shop("First", "Customer", "M", 49, "me@mine.com");
        DBHelper.save(shop);
    }
}
