package cookMe.processing.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anis on 24/05/2016.
 */
@FacesValidator(value ="validators.pwd1")
public class RepeatPasswordValidator implements Validator{


    private static final String PATTERN = "";

    private Pattern pattern;
    private Matcher matcher;

    public RepeatPasswordValidator() {
        pattern = Pattern.compile(PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        pattern = Pattern.compile((String) ((HtmlInputSecret) uiComponent.getParent().findComponent("pwd")).getValue());
        matcher = pattern.matcher(o.toString());

        if(!matcher.matches()){
            FacesMessage msg = new FacesMessage("repeat password isValid failed.", "Reapeat Password Validation failed please write the same password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }



}
