package db;

import models.*;
import sun.misc.BASE64Decoder;
import sun.tools.asm.Cover;

import java.util.List;

public class Seeds {
    public static void seedData() {
        DBHelper.deleteAll(Shop.class);
        DBHelper.deleteAll(Bike.class);
        DBHelper.deleteAll(Accessory.class);
        DBHelper.deleteAll(Clothing.class);
        DBHelper.deleteAll(Customer.class);

        Customer customer1 = new Customer("Will", "MacIntyre", "M", 32, "inferno@gmail.com", "willmac");
        DBHelper.save(customer1);

        Shop shop = new Shop("Jurassic Motorcycles", 0);
        DBHelper.save(shop);

        Basket basket = new Basket(customer1);
        DBHelper.save(basket);

        Bike bike1 = new Bike("Neiman Marcus Limited Edition Fighter", 11000000, 1, "/images/neiman_marcus.jpeg", "Clockwork Metal", 120, true);
        DBHelper.save(bike1);

        Accessory accessory1 = new Accessory("DSYJ Windproof Face Mask Cover", 0.76, 20, "/images/DSYJ_facemask.jpeg");
        DBHelper.save(accessory1);
        Accessory accessory2 = new Accessory("Arai RX7", 499.99, 0, "/images/arai_rx7.jpg");
        DBHelper.save(accessory2);

        Clothing clothing1 = new Clothing("All Black Waterproof Armoured Motorcycle Trousers", 44, 3, "/images/black_trousers.jpeg", "Black", "All Sizes", ClothingType.TROUSER);
        DBHelper.save(clothing1);

        basket.addItem(bike1);
        customer1.setBasket(basket);


//        basket = new Basket(customer1);
//        DBHelper.save(basket);

//        int itemCount = customer1.getBasket().countItemsInBasket();

//        customer1.addToBasket(clothing1, basket);

//        int itemCount2 = customer1.getBasket().countItemsInBasket();

        Customer testCust = DBHelper.findCustomerByUsername(customer1.getUsername());
        testCust.setBasket(basket);

//        List<StockItem> testBasket = DBHelper.findItemsInBasket(basket);
    }




}
