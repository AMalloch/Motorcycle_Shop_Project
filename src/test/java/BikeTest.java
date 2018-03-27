import db.DBHelper;
import models.Basket;
import models.Bike;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BikeTest {

    private Bike bike;
    private Basket basket;

    @Before
    public void setUp() {
        bike = new Bike("Yamaha R1", 2995.99, 1, null, "blue", 1000, false, null);
        DBHelper.save(bike);
    }

    @Test
    public void testCanSaveBike() {
        assertEquals(1, DBHelper.getAll(Bike.class).size());
    }
}
