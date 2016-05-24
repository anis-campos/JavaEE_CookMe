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
@FacesValidator(value ="validators.age")
public class AgeValidator implements Validator{

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            Integer i = (Integer) o;

            if( i < 0 || i > 100){
                FacesMessage msg =
                        new FacesMessage("Age validation failed.","Age > 0 or Age > 100");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }catch (Exception e){
            FacesMessage msg =
                    new FacesMessage("Age validation failed.","Age needs to be an integer !");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }


    }



}
