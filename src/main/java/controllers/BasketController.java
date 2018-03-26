package controllers;

import db.DBHelper;
import models.Basket;
import models.Bike;
import models.Customer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class BasketController {


    public BasketController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        LoginController loginController = new LoginController();

        get("/basket/:custId", (req, res) -> {
            String strId = req.params(":custId");
            Integer intId = Integer.parseInt(strId);
            Customer customer = DBHelper.find(intId, Customer.class);
            List<Basket> basket = DBHelper.findBasket(intId);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserNameForBasket(req, res);
            model.put("user", loggedInUser);
            model.put("customer", customer);
            model.put("basket", basket);
            model.put("template", "templates/basket/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }


}
