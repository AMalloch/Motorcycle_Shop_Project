import db.DBHelper;
import models.Accessory;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

public class AccessoryTest {

    private Accessory accessory;

    @Before
    public void setUp() throws Exception {
        accessory = new Accessory("Carbon Fibre Part", 149.99, 3);
    }

    @Test
    public void testCanSaveAccessory() {
        DBHelper.save(accessory);
    }
}
