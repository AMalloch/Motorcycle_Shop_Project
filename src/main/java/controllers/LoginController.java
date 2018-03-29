package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class LoginController {


    public LoginController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get ("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String referer = req.headers("referer");
            model.put("referer", referer);
            return new ModelAndView(model, "templates/login.vtl");
        }, new VelocityTemplateEngine());

        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            req.session().attribute("username", username);
            res.redirect(req.queryParams("referer"));
            return null;
        }, new VelocityTemplateEngine());

        get ("/logout", (req, res) -> {
            req.session().removeAttribute("username");
            res.redirect(req.headers("referer"));
            return null;
        }, new VelocityTemplateEngine());
    }

    public static String getLoggedInUserName(Request req, Response res) {
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()) {
            return "";
        }
        return username;
    }

    public static String getLoggedInUserNameForBasket(Request req, Response res) {
        String username = req.session().attribute("username");
        if (username == null || username.isEmpty()) {
            res.redirect("/login");
        }
        return username;
    }
}
