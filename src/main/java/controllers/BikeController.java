package controllers;

import db.DBHelper;
import models.Bike;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.text.DecimalFormat;
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
            DecimalFormat df =new DecimalFormat("#0.00");
            Map<String, Object> model = new HashMap<>();
            List<Bike> bikes = DBHelper.getAvailableStock(Bike.class);
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("template", "templates/bikes/index.vtl");
            model.put("bikes", bikes);
            model.put("df", df);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/bikes/:id", (req, res) -> {
            String strId = req.params(":id");
            Integer intId = Integer.parseInt(strId);
            Bike bike = DBHelper.find(intId, Bike.class);
            DecimalFormat df =new DecimalFormat("#0.00");
            Map<String, Object> model = new HashMap<>();
            String loggedInUser = LoginController.getLoggedInUserName(req, res);
            model.put("user", loggedInUser);
            model.put("bike", bike);
            model.put("df", df);
            model.put("template", "templates/bikes/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }

}
