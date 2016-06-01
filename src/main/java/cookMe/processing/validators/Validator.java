package cookMe.processing.validators;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@SessionScoped
public class Validator {


    private static final String PATTERN = "[a-zA-Z0-9-._]+@[a-zA-Z0-9-._]+.[a-z]+";

    private Pattern pattern;

    public Validator() {
        pattern = Pattern.compile(PATTERN);
    }


    public void isEmailValid(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        Matcher matcher = pattern.matcher(o.toString());
        if (!matcher.matches() || ((String) o).length() > 100 || ((String) o).length() < 0) {
            FacesMessage msg =
                    new FacesMessage("user name isValid failed.", "Email Validation failed please follow the contraint " + PATTERN + " and < 100 caracters");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }


}
