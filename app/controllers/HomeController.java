package controllers;

import java.util.List;
import models.Usr;

import models.Usr;
import play.data.Form;
import play.mvc.*;

import views.html.*;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result showHomePage() {
        Login loginCreds = new Login();
        return ok(home.render("", loginCreds));
    }

    public Result login() {
        Login loginCreds = Form.form(Login.class).bindFromRequest().get();

        List<Usr> user = Usr.find.where().eq("userId",loginCreds.userId).findList();
        if (user != null && user.size() != 0) {
            if (user.get(0).password.equals(loginCreds.password)) {
                session().clear();
                session("userId", loginCreds.userId);
                return redirect(routes.PostController.getPosts());
            }
        }
        return ok(home.render("Invalid User name or Password", loginCreds));
    }


    @Security.Authenticated(Secured.class)
    public Result logout() {
        session().clear();
        return redirect(routes.HomeController.showHomePage());
    }
}
