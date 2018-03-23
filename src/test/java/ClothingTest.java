import db.DBHelper;
import models.Clothing;
import models.ClothingType;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClothingTest {

    private Shop shop;
    private Clothing clothing;

    @Before
    public void setUp() {
        shop = new Shop("Jurassic Motorcycles", 0);
        clothing = new Clothing("Alpinestars Summer Gloves", 59.99, 3, shop, "black", "large", ClothingType.GLOVES);
        DBHelper.save(clothing);
    }

    @Test
    public void testCanSaveClothing() {
        assertEquals(1, DBHelper.getAll(Clothing.class).size());
    }
}
