import db.DBHelper;
import models.Basket;
import models.Clothing;
import models.ClothingType;
import models.Shop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClothingTest {

    private Clothing clothing;
    private Basket basket;

    @Before
    public void setUp() {
        clothing = new Clothing("Alpinestars Summer Gloves", 59.99, null, "black", "large", ClothingType.GLOVES);
        DBHelper.save(clothing);
    }

    @Test
    public void testCanSaveClothing() {
        assertEquals(1, DBHelper.getAll(Clothing.class).size());
    }
}
