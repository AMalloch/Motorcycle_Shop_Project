package controllers;

import db.DBHelper;
import models.Bike;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class BikeController {


    public BikeController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/bikes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Bike> bikes = DBHelper.getAvailableStock(Bike.class);
//            String loggedInUser = LoginController.getLoggedInUserName(req, res);
//            model.put("user", loggedInUser);
            model.put("template", "templates/bikes/index.vtl");
            model.put("bikes", bikes);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
