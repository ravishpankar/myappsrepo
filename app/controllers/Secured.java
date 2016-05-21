package controllers;

import play.mvc.*;


/**
 * Created by admin on 5/18/2016.
 */
public class Secured extends Security.Authenticator {

        @Override
        public String getUsername(Http.Context ctx) {
            return ctx.session().get("userId");
        }

        @Override
        public Result onUnauthorized(Http.Context ctx) {
            return redirect(routes.HomeController.showHomePage());
        }
}
