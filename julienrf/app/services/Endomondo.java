package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import play.libs.F;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.libs.WS.WSRequestHolder;
import play.mvc.Result;
import scala.NotImplementedError;
//import static play.libs.F.Promise;

/**
 * Bibliothèque utilitaire pour effectuer des requêtes HTTP sur votre Web service Endomondo.
 *
 * Votre code ne devrait pas être bloquant si vous souhaitez que votre application supporte une importante
 * montée en charge.
 */
public class Endomondo {

    /**
     * (Exemple de méthode à implémenter)
     *
     * Appelle le Web service Endomondo pour savoir si un couple (nom, mot de passe) identifie effectivement un utilisateur existant.
     *
     * @param name Nom de l’utilisateur
     * @param password Mot de passe
     * @return Une promesse de booléen qui vaudra `true` ssi l’utilisateur a pu être identifié
     */
    public Promise<Boolean> authenticate(String name, String password) {
  
             return null;
    }

    
    public List<String> getPromiseForRegisteredUsersXXX() {
        
    	System.out.println(" Inside of Get Priomise For registered user ! ");
    	
    	 
    	@SuppressWarnings("unused")
		Promise<WS.Response> wsPromise = WS.url("http://localhost:8080/rest/user/allusers")
                .setContentType("application/json; charset=utf-8")
                .get();
    	
    	Promise<List<String>> resultPromise = wsPromise.map(new F.Function<WS.Response, List<String> >() {
    	    @Override
    	    public List<String> apply(WS.Response response) throws Throwable {
    	    	System.out.println("Reponse from server  .... is : ");
    	        System.out.println(response.getBody());
    	        List<String> li = new LinkedList<String>();
    	        li.add(response.getBody());
    	        return   li;
    	    }
    	});	
    	    return  resultPromise.get();
    }
    
    
    
    public List<String> getPromiseForRegisteredUsers() {
    
    	System.out.println(" Inside of Get Priomise For registered user ! ");
    	
    	 
    	@SuppressWarnings("unused")
		Promise<WS.Response> wsPromise = WS.url("http://localhost:8080/rest/user/allusernames")
                .setContentType("application/json; charset=utf-8")
                .get();
    	
    	Promise<List<String>> resultPromise = wsPromise.map(new F.Function<WS.Response, List<String> >() {
    	    @Override
    	    public List<String> apply(WS.Response response) throws Throwable {
    	    	System.out.println("Reponse from server  .... is : ");
    	        System.out.println(response.getBody());
    	        List<String> li = new LinkedList<String>();
    	        li.add(response.getBody());
    	        return   li;
    	    }
    	});	
    	    return  resultPromise.get();
    }
       

}
