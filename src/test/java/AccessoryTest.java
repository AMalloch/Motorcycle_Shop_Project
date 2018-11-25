import db.DBHelper;
import models.Accessory;
import models.Order;
import org.junit.Before;
import org.junit.Test;

public class AccessoryTest {

    private Accessory accessory;
    private Order order;

    @Before
    public void setUp() throws Exception {
        accessory = new Accessory("Carbon Fibre Part", 149.99, 3, null);
    }

    @Test
    public void testCanSaveAccessory() {
        DBHelper.save(accessory);
    }
}
