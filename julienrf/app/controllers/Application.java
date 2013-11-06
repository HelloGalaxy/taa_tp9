package controllers;

import controllers.forms.Login;
import play.data.Form;
import play.libs.EventSource;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Results.Chunks.Out;
import services.Twitter;
import static play.data.Form.form;

public class Application extends Controller {

    // Twitter service
    final static Twitter twitter = Twitter.taa();

    /**
     * Affiche le tableau de bord de l’utilisateur courant (p. ex. un résumé de ses dernières activités sportives).
     * Si l’utilisateur ne s’est pas identifié il doit être redirigé vers la page de login.
     */
    @Security.Authenticated(Authenticator.class)
    public static Result index() {
        return ok(views.html.index.render(request().username()));
    }

    /**
     * Affiche le formulaire d’identification.
     */
    public static Result loginForm() {    
        return ok(views.html.login.render(form(Login.class)));
    }

    /**
     * Gère la soumission du formulaire.
     * Si les données soumises ne sont pas valides (p. ex. le mot de passe est incorrect) l’action doit renvoyer un code
     * d’erreur 400 et réafficher le formulaire.
     * Sinon, authentifier l’utilisateur (enregistrer son nom dans sa session) et le rediriger vers son tableau de bord.
     */
    public static Result login() {
    	
    	Form<Login> form = Form.form(Login.class);
    	Form<Login> submission = form.bindFromRequest();
    	
    	if (submission.hasErrors()){
    		 return redirect(routes.Application.login());
    	}else{
    		session().put("username",submission.get().name ); // TODO Récupérer le nom de l’utilisateur depuis les données de soumission du formulaire
            return redirect(routes.Application.index());
    	}
        
    }

    /**
     * Déconnecte un utilisateur (retire son nom de sa session) et affiche un message d’au-revoir.
     */
    public static Result logout() {
        session().remove("username");
        return redirect(routes.Application.login());
    }

    /**
     * Envoie un flux de tweets d’utilisateurs d’Endomondo.
     */
    public static Result twitterFeed() {
    	
        final EventSource eventSource = new EventSource() {
            @Override
            public void onConnected() {
              
            }

        };
        
        controllers.EventSource pes = new controllers.EventSource() {
			
			@Override
			public void onReady(Out arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onConnected() {
				// TODO Auto-generated method stub
				
			}
		};
        twitter.publicStream("love", pes);
		return ok(pes.toString());
        //return ok(eventSource);
    }

}
