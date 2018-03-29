package controllers;

import db.DBHelper;
import models.Clothing;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class ClothingController {


    public ClothingController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/clothing", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Clothing> clothes = DBHelper.getAvailableStock(Clothing.class);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/clothing/index.vtl");
            model.put("clothes", clothes);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/clothing/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Clothing clothing = DBHelper.find(intId, Clothing.class);
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("clothing", clothing);
            model.put("template", "templates/clothing/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
