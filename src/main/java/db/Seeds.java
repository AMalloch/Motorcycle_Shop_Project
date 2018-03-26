package db;

import models.*;
import sun.tools.asm.Cover;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Shop.class);
        DBHelper.deleteAll(Bike.class);
        DBHelper.deleteAll(Accessory.class);
        DBHelper.deleteAll(Clothing.class);
        DBHelper.deleteAll(Customer.class);

        Shop shop = new Shop("Jurassic Motorcycles", 0);
        DBHelper.save(shop);

        Bike bike1 = new Bike("Neiman Marcus Limited Edition Fighter", 11000000, 1, "Clockwork Metal", 120, true);
        DBHelper.save(bike1);

        Accessory accessory1 = new Accessory("DSYJ Windproof Face Mask Cover", 0.76, 20);
        DBHelper.save(accessory1);

        Clothing clothing1 = new Clothing("All Black Waterproof Armoured Motorcycle Trousers", 44, 3, "Black", "All Sizes", ClothingType.TROUSER);
        DBHelper.save(clothing1);

        Customer customer1 = new Customer("Will", "MacIntyre", "M", 32, "inferno@gmail.com");
        DBHelper.save(customer1);
    }
}
