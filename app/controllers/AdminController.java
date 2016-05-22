package controllers;

import models.Usr;

import play.mvc.*;

import views.html.*;

/**
 * Created by admin on 5/18/2016.
 */
public class AdminController extends Controller {

    public Result showAdminPage() {
        return ok(admin.render());
    }

    public Result addUsers() {
        Usr u = new Usr();
        u.userId = "ravi";
        u.password = "ravish123";
        u.save();
        u = new Usr();
        u.userId = "suresh";
        u.password = "suresh123";
        u.save();
        u = new Usr();
        u.userId = "ashok";
        u.password = "ashok123";
        u.save();
        return redirect(routes.HomeController.showHomePage());
    }
}
