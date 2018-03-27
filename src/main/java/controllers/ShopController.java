package controllers;

import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

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

        get("/shops/stock", (req, res) -> {
            List<Bike> bikes = DBHelper.getAll(Bike.class);
            List<Accessory> accessories = DBHelper.getAll(Accessory.class);
            List<Clothing> clothes = DBHelper.getAll(Clothing.class);
            Map<String, Object> model = new HashMap<>();
            model.put("bikes", bikes);
            model.put("accessories", accessories);
            model.put("clothes", clothes);
            model.put("template", "templates/shops/stock.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/shops/bike", (req, res) -> {
            List<Bike> bikes = DBHelper.getAll(Bike.class);
            Map<String, Object> model = new HashMap<>();
            model.put("bikes", bikes);
            model.put("template", "templates/shops/bike.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/shops/bike/create", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/shops/create_bike.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/shops/bike/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Bike bike = DBHelper.find(intId, Bike.class);
            Map<String, Object> model = new HashMap<>();
            model.put("bike", bike);
            model.put("template", "templates/shops/edit_bike.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/shops/stock", (req, res) -> {
            int basketId = Integer.parseInt(req.queryParams("basket"));
            Basket basket = DBHelper.find(basketId, Basket.class);
            String name = req.queryParams("name");
            Double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String colour = req.queryParams("colour");
            int capacity = Integer.parseInt(req.queryParams("capacity"));
            Boolean isNew = Boolean.parseBoolean(req.queryParams("isNew"));
            String imageUrl = req.queryParams("imageUrl");
            Bike bike = new Bike(name, price, quantity, imageUrl, colour, capacity, isNew, basket);
            DBHelper.save(bike);
            res.redirect("/shops/stock");
            return null;
        }, new VelocityTemplateEngine());

        post ("/shops/bike/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Bike bike = DBHelper.find(intId, Bike.class);
            String name = req.queryParams("name");
            Double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String imageUrl = req.queryParams("imageUrl");
            String colour = req.queryParams("colour");
            int capacity = Integer.parseInt(req.queryParams("capacity"));
            Boolean isNew = Boolean.parseBoolean(req.queryParams("isNew"));
            bike.setName(name);
            bike.setPrice(price);
            bike.setQuantity(quantity);
            bike.setImageUrl(imageUrl);
            bike.setColour(colour);
            bike.setCapacity(capacity);
            bike.setNew(isNew);
            DBHelper.update(bike);
            res.redirect("/shops/stock");
            return null;

        }, new VelocityTemplateEngine());

        post ("/shops/bike/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Bike bikeToDelete = DBHelper.find(id, Bike.class);
            DBHelper.delete(bikeToDelete);
            res.redirect("/shops/stock");
            return null;
        }, new VelocityTemplateEngine());

//        ---------------------------------------------------------

        get("/shops/clothing", (req, res) -> {
            List<Clothing> clothes = DBHelper.getAll(Clothing.class);
            Map<String, Object> model = new HashMap<>();
            model.put("clothes", clothes);
            model.put("template", "templates/shops/clothing.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/shops/clothing/create", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<ClothingType> clothes = DBHelper.getClothingType();
            model.put("template", "templates/shops/create_clothing.vtl");
            model.put("clothes", clothes);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
//
        get("/shops/clothing/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Clothing clothing = DBHelper.find(intId, Clothing.class);
            Map<String, Object> model = new HashMap<>();
            List<ClothingType> clothes = DBHelper.getClothingType();
            model.put("clothes", clothes);
            model.put("clothing", clothing);
            model.put("template", "templates/shops/edit_clothing.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/shops/clothing", (req, res) -> {
            int basketId = Integer.parseInt(req.queryParams("basket"));
            Basket basket = DBHelper.find(basketId, Basket.class);
            String name = req.queryParams("name");
            Double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String imageUrl = req.queryParams("imageUrl");
            String colour = req.queryParams("colour");
            String size = req.queryParams("size");
            String stringType = req.queryParams("type");
            ClothingType type = ClothingType.valueOf(stringType);
            Clothing clothing = new Clothing(name, price, quantity, imageUrl, colour, size, type, basket);
            DBHelper.save(clothing);
            res.redirect("/shops/stock");
            return null;
        }, new VelocityTemplateEngine());

        post ("/shops/clothing/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Clothing clothing = DBHelper.find(intId, Bike.class);
            String name = req.queryParams("name");
            Double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String imageUrl = req.queryParams("imageUrl");
            String colour = req.queryParams("colour");
            String size = req.queryParams("size");
            String stringType = req.queryParams("type");
            ClothingType type = ClothingType.valueOf(stringType);
            clothing.setName(name);
            clothing.setPrice(price);
            clothing.setQuantity(quantity);
            clothing.setImageUrl(imageUrl);
            clothing.setColour(colour);
            clothing.setSize(size);
            clothing.setType(type);
            DBHelper.update(clothing);
            res.redirect("/shops/stock");
            return null;

        }, new VelocityTemplateEngine());

        post ("/shops/clothing/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Clothing clothingToDelete = DBHelper.find(id, Clothing.class);
            DBHelper.delete(clothingToDelete);
            res.redirect("/shops/stock");
            return null;
        }, new VelocityTemplateEngine());

//        ---------------------------------------------------------

        get("/shops/accessory", (req, res) -> {
            List<Accessory> accessories = DBHelper.getAll(Accessory.class);
            Map<String, Object> model = new HashMap<>();
            model.put("accessories", accessories);
            model.put("template", "templates/shops/accessory.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get ("/shops/accessory/create", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/shops/create_accessory.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/shops/accessory/:id/edit", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Accessory accessory = DBHelper.find(intId, Accessory.class);
            Map<String, Object> model = new HashMap<>();
            model.put("accessory", accessory);
            model.put("template", "templates/shops/edit_accessory.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post ("/shops/accessory", (req, res) -> {
            String name = req.queryParams("name");
            Double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String imageUrl = req.queryParams("imageUrl");
            Accessory accessory = new Accessory(name, price, quantity, imageUrl);
            DBHelper.save(accessory);
            res.redirect("/shops/stock");
            return null;
        }, new VelocityTemplateEngine());

        post ("/shops/accessory/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Accessory accessory = DBHelper.find(intId, Accessory.class);
            String name = req.queryParams("name");
            Double price = Double.parseDouble(req.queryParams("price"));
            int quantity = Integer.parseInt(req.queryParams("quantity"));
            String imageUrl = req.queryParams("imageUrl");
            accessory.setName(name);
            accessory.setPrice(price);
            accessory.setQuantity(quantity);
            accessory.setImageUrl(imageUrl);
            DBHelper.update(accessory);
            res.redirect("/shops/stock");
            return null;

        }, new VelocityTemplateEngine());

        post ("/shops/accessory/:id/delete", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Accessory accessoryToDelete = DBHelper.find(id, Accessory.class);
            DBHelper.delete(accessoryToDelete);
            res.redirect("/shops/stock");
            return null;
        }, new VelocityTemplateEngine());
    }
}
