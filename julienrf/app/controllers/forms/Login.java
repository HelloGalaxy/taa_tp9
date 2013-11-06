package controllers.forms;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Récupère les données soumises par le formulaire de login (défini dans la vue
 * `views/login.scala.html`).<br />
 * Exemple d’utilisation (dans un controller) :
 * 
 * <pre>
 * Form&lt;Login&gt; submission = form(Login.class).bindFromRequest();
 * </pre>
 */
public class Login {

	public String name;

	public String password;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (name.equals("")) {
			errors.add(new ValidationError("", "Name is not required."));
		}

		if (password.length() < 4) {
			errors.add(new ValidationError("",
					"Length of password shoulb be super 4."));
		}

		if (!(name.equals("toto") && password.equals("toto"))) {
			errors.add(new ValidationError("", "Invalid user name or password"));
		}

		//System.out.println(name);
		//System.out.println(errors.get(0));

		return errors.isEmpty() ? null : errors;
		// return "toto".equals(name) ? null : new ArrayList<ValidationError>()
		// {{ add(new ValidationError("", "Invalid user name or password")); }};
	}

}
