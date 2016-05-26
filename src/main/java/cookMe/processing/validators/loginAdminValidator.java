package cookMe.processing.validators;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.UserModelBean;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Anis on 25/05/2016.
 */
@FacesValidator("loginAdminValidator")
public class LoginAdminValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        String submittedValue = facesContext.getExternalContext().getRequestParameterMap().get(uiComponent.getClientId(facesContext));

        UserDao userDao = DaoFabric.getInstance().createUserDao();

        UserModelBean user = userDao.findByLogin(submittedValue);

         if(user == null || user.getType()!= UserModelBean.UserType.admin){
            throw new ValidatorException(new FacesMessage("Pas admin"));
        }

    }
}
