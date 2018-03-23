import db.DBHelper;
import models.Accessory;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

public class AccessoryTest {

    private Shop shop;
    private Accessory accessory;

    @Before
    public void setUp() throws Exception {
        shop = new Shop("Jurassic Motorcycles", 0);
        accessory = new Accessory("Carbon Fibre Part", 149.99, 3, shop);
    }

    @Test
    public void testCanSaveAccessory() {
        DBHelper.save(accessory);
    }
}
