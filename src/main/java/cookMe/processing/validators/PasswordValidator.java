package cookMe.processing.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anis on 24/05/2016.
 */
@FacesValidator(value ="validators.pwd")
public class PasswordValidator implements Validator{


    private static final String PATTERN = "[a-zA-Z0-9]*";

    private Pattern pattern;
    private Matcher matcher;

    public PasswordValidator() {
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        matcher = pattern.matcher(o.toString());
        if(!matcher.matches() || ((String) o ).length() > 25 || ((String) o ).length() < 0){
            FacesMessage msg = new FacesMessage("password isValid failed.", "Password Validation failed please follow the contraint" + PATTERN + " and < 25 caracters");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }



}
