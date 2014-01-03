package controllers;

import play.mvc.Http.Context;
import play.mvc.Result;

public class Authentificator  extends  play.mvc.Security.Authenticator{

	@Override
    public Result onUnauthorized(Context arg0) {
            return redirect(routes.Application.loginForm());
    }
}
