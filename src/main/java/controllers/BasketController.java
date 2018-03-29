package controllers;

import db.DBHelper;
import models.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class BasketController {

    private Basket basket;


    public BasketController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        LoginController loginController = new LoginController();

        get("/basket", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            Set<StockItem> basketItems = DBHelper.findBasketItems(customer.getBasket());
            Double totalBasketPrice = DBHelper.calculateTotalBasketPrice(basketItems);
            DecimalFormat df =new DecimalFormat("#0.00");
            Map<String, Object> model = new HashMap<>();
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("basketItems", basketItems);
            model.put("totalBasketPrice", totalBasketPrice);
            model.put("df", df);
            model.put("template", "templates/basket/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/basket/add/:stockItemId", (req, res) -> {
            String strItemId = req.params(":stockItemId");
            Integer intItemId = Integer.parseInt(strItemId);
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
            DBHelper.addToBasket(stockItem, 1, customer);
            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());

        post("/basket/delete/:stockItemId", (req, res) -> {
            String strItemId = req.params(":stockItemId");
            Integer intItemId = Integer.parseInt(strItemId);
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
            DBHelper.deleteFromBasket(stockItem, customer);
            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());

        post("/basket/buy", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            Set<StockItem> purchasedItems = DBHelper.findBasketItems(customer.getBasket());
            Shop shop = DBHelper.find(1, Shop.class);
            DBHelper.addSaleToShopCash(DBHelper.calculateTotalBasketPrice(purchasedItems), shop);
            for (StockItem purchasedItem : purchasedItems){
                DBHelper.deleteFromBasket(purchasedItem, customer);
            }
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());

    }

}
