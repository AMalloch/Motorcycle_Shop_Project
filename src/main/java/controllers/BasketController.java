package controllers;

import db.DBHelper;
import models.Basket;
import models.Bike;
import models.Customer;
import models.StockItem;
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
            Basket basket = customer.getBasket();
            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
            DBHelper.addToBasket2(stockItem, 1, basket);
//            DBHelper.addToBasket(stockItem, 1, customer, basket);
            res.redirect("/basket");
            return null;
        }, new VelocityTemplateEngine());



////  addToBasket(StockItem item, int ppQuanity, Customer customer, Basket basket)
//
//
//            String strCustId = req.params(":custId");
//            String strItemId = req.params(":stockItemId");
//            Integer intCustId = Integer.parseInt(strCustId);
//            Integer intItemId = Integer.parseInt(strItemId);
//            Customer customer = DBHelper.find(intCustId, Customer.class);
////            Basket basket = null; //DBHelper.findBasket(intCustId);
//            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
//            Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
//            model.put("user", loggedInUser);
//            model.put("customer", customer);
//            model.put("basket", basket);
//            model.put("stockItem", stockItem);
//            model.put("template", "templates/basket/add.vtl");
//            return new ModelAndView(model, "templates/layout.vtl");
//            if (basket.getId() <= 1){
//                basket = new Basket();
//            }
//            basket.setCustomerId(customer.getId());
//            basket.setStockItemId(stockItem.getId());
//            Basket basket = new Basket(customer.getId(), stockItem.getId());
//            Basket newBasket = new Basket(1, 1);
//            DBHelper.save(newBasket);
//            res.redirect("/basket/" + customer.getId());
//            return null;
//        }, new VelocityTemplateEngine());
    }


}
