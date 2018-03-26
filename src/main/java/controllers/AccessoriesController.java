package controllers;

import db.DBHelper;
import models.Accessory;
import models.Bike;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class AccessoriesController {


    public AccessoriesController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/accessories", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Accessory> accessories = DBHelper.getAvailableStock(Accessory.class);
//            String loggedInUser = LoginController.getLoggedInUserName(req, res);
//            model.put("user", loggedInUser);
            model.put("template", "templates/accessories/index.vtl");
            model.put("accessories", accessories);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/accessories/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Accessory accessory = DBHelper.find(intId, Accessory.class);
            Map<String, Object> model = new HashMap<>();
            model.put("accessory", accessory);
            model.put("template", "templates/accessories/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
