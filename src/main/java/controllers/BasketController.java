package controllers;

import db.DBHelper;
import models.Basket;
import models.Bike;
import models.Customer;
import models.StockItem;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class BasketController {

    private Basket basket;


    public BasketController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        LoginController loginController = new LoginController();

        get("/basket/:custId", (req, res) -> {
            String strCustId = req.params(":custId");
            Integer intCustId = Integer.parseInt(strCustId);
            Customer customer = DBHelper.find(intCustId, Customer.class);
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            int itemCount = customer.getBasket().countItemsInBasket();
            Map<String, Object> model = new HashMap<>();
            model.put("user", loggedInUser);
            model.put("customer", customer);
            if (itemCount > 0) {
                List<Basket> basketItems = DBHelper.findBasketItems(intCustId);
//                Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
//                model.put("user", loggedInUser);
//                model.put("customer", customer);
                model.put("basketItems", basketItems);
            }
            model.put("template", "templates/basket/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/basket/:custId/add/:stockItemId", (req, res) -> {
            String strCustId = req.params(":custId");
            String strItemId = req.params(":stockItemId");
            Integer intCustId = Integer.parseInt(strCustId);
            Integer intItemId = Integer.parseInt(strItemId);
            Customer customer = DBHelper.find(intCustId, Customer.class);
//            Basket basket = null; //DBHelper.findBasket(intCustId);
            StockItem stockItem = DBHelper.find(intItemId, StockItem.class);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
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
            res.redirect("/basket/" + customer.getId());
            return null;
        }, new VelocityTemplateEngine());
    }


}
