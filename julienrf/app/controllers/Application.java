package controllers;

import java.util.Iterator;
import java.util.List;

import controllers.forms.Login;
import play.api.libs.oauth.ConsumerKey;
import play.api.libs.oauth.RequestToken;
import play.data.Form;
import play.libs.WS;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.Endomondo;
import services.Twitter;
import static play.data.Form.form;

/**
*  @author  Boussad Ghedamsi, Pu Chencheng , Houda Argani , m2gl 2013
*/
public class Application extends Controller {

    // Twitter service
    final static Twitter twitter = Twitter.taa();
    private static String messgError="";
    /**
     * Affiche le tableau de bord de l’utilisateur courant (p. ex. un résumé de ses dernières activités sportives).
     * Si l’utilisateur ne s’est pas identifié il doit être redirigé vers la page de login.
     */
    @Security.Authenticated(Authentificator.class)
    public static Result index() {
        return ok(views.html.index.render(request().username()));
    }

    /**
     * Affiche le formulaire d’identification.
     */
    public static Result loginForm() {
    	String tempMessgError=messgError;
    	messgError="";
        return ok(views.html.login.render(form(Login.class), tempMessgError));
    }

    /**
     * Gère la soumission du formulaire.
     * Si les données soumises ne sont pas valides (p. ex. le mot de passe est incorrect) l’action doit renvoyer un code
     * d’erreur 400 et réafficher le formulaire.
     * Sinon, authentifier l’utilisateur (enregistrer son nom dans sa session) et le rediriger vers son tableau de bord.
     */
    public static Result login() {
    	  session().put("username", "toto"); // TODO Récupérer le nom de l’utilisateur depuis les données de soumission du formulaire
          //System.out.println(" Login is called ");
          // return  status(NOT_IMPLEMENTED);
          Form<Login> form = Form.form(Login.class);
          Form<Login> submission = form.bindFromRequest();
          if( submission.hasGlobalErrors()){
       	      messgError="Invalide user name or pass word";
       	      return redirect(routes.Application.loginForm());
          }else{
       	      session().put("username", submission.get().name);
       	      return redirect(routes.Application.index());
          }
      
    }

    /**
     * Déconnecte un utilisateur (retire son nom de sa session) et affiche un message d’au-revoir.
     */
    public static Result logout() {
    	 session().remove("username");
         return ok(views.html.logout.render());
    }

    /**
     * Envoie un flux de tweets d’utilisateurs d’Endomondo.
     */
    public static Result twitterFeed() {
        final EventSource eventSource = new EventSource() {
            @Override
            public void onConnected() {
                // TODO utiliser le service Twitter pour obtenir un flux de tweets à afficher
            	System.out.println(" Twitter  SERVICE CALLED  !! ");
            	Twitter.taa().publicStream("rennes", this);
            }

        };
        eventSource.onConnected();
        return ok(eventSource);
    }

    public static  Result endoUsers(){
    	
    	List<String> response =(new Endomondo()).getPromiseForRegisteredUsers();
    	Iterator iterator = response.iterator();
    	if(iterator.hasNext()) {
    		String str = " The registered users in our site are : \n "+(String) iterator.next();
    		return ok(str);
    	}else{
    		return ok("");
    	}
    	
    }
    
    
}
