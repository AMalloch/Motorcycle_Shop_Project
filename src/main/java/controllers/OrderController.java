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

public class OrderController {

    private Order order;


    public OrderController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        LoginController loginController = new LoginController();

        get("/order", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            Set<StockItem> basketItems = DBHelper.findBasketItems(customer.getOrder());
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

        post("/order/add/:stockItemId", (req, res) -> {
            String strItemId = req.params(":stockItemId");
            Integer intItemId = Integer.parseInt(strItemId);
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
            DBHelper.addToBasket(stockItem, 1, customer);
            res.redirect("/order");
            return null;
        }, new VelocityTemplateEngine());

        post("/order/delete/:stockItemId", (req, res) -> {
            String strItemId = req.params(":stockItemId");
            Integer intItemId = Integer.parseInt(strItemId);
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
            DBHelper.deleteFromBasket(stockItem, customer);
            res.redirect("/order");
            return null;
        }, new VelocityTemplateEngine());

        post("/order/buy", (req, res) -> {
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            Customer customer = DBHelper.findCustomerByUsername(loggedInUser);
            Set<StockItem> purchasedItems = DBHelper.findBasketItems(customer.getOrder());
            Shop shop = DBHelper.find(1, Shop.class);
            for (StockItem purchasedItem : purchasedItems){
                DBHelper.deleteFromBasket(purchasedItem, customer);
            }
            DBHelper.addSaleToShopCash(DBHelper.calculateTotalBasketPrice(purchasedItems), shop);
            res.redirect("/");
            return null;
        }, new VelocityTemplateEngine());



////  addToBasket(StockItem item, int ppQuanity, Customer customer, Order order)
//
//
//            String strCustId = req.params(":custId");
//            String strItemId = req.params(":stockItemId");
//            Integer intCustId = Integer.parseInt(strCustId);
//            Integer intItemId = Integer.parseInt(strItemId);
//            Customer customer = DBHelper.find(intCustId, Customer.class);
////            Order order = null; //DBHelper.findBasket(intCustId);
//            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
//            Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
//            model.put("user", loggedInUser);
//            model.put("customer", customer);
//            model.put("order", order);
//            model.put("stockItem", stockItem);
//            model.put("template", "templates/order/add.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//            if (order.getId() <= 1){
//                order = new Order();
//            }
//            order.setCustomerId(customer.getId());
//            order.setStockItemId(stockItem.getId());
//            Order order = new Order(customer.getId(), stockItem.getId());
//            Order newBasket = new Order(1, 1);
//            DBHelper.save(newBasket);
//            res.redirect("/order/" + customer.getId());
//            return null;
//        }, new VelocityTemplateEngine());
    }


}
