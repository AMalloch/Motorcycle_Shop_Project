package controllers;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class MainController {

    public static void main(String[] args) {

        CustomerController customerController = new CustomerController();
        ShopController shopController = new ShopController();

//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String loggedInUser = LoginController.getLoggedInUserName(req, res);
//            model.put("user", loggedInUser);
//            model.put("template","templates/main.vtl");
//
//            return new ModelAndView(model, "templates/layout.vtl");
//        }, new VelocityTemplateEngine());

    }
}
