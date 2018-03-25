package controllers;

import db.DBHelper;
import models.Bike;
import models.Customer;
import models.Shop;
import models.StockItem;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ShopController {


    public ShopController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/shops", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Shop> shops = DBHelper.getAll(Shop.class);
            model.put("template", "templates/shops/index.vtl");
            model.put("shops", shops);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/shops/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Shop shop = DBHelper.find(intId, Shop.class);
            List<Bike> bikes = DBHelper.findBikeForShop(shop);
            Map<String, Object> model = new HashMap<>();
            model.put("bikes", bikes);
            model.put("template", "templates/shops/stock.vtl");
            model.put("shop", shop);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());



    }
}
