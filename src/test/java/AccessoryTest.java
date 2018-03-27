import db.DBHelper;
import models.Accessory;
import models.Basket;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

public class AccessoryTest {

    private Accessory accessory;
    private Basket basket;

    @Before
    public void setUp() throws Exception {
        accessory = new Accessory("Carbon Fibre Part", 149.99, 3, null);
    }

    @Test
    public void testCanSaveAccessory() {
        DBHelper.save(accessory);
    }
}
