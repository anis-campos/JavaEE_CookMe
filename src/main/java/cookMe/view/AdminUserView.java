package cookMe.view;

import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserSubmissionModelBean;
import cookMe.processing.UserControllerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created by Anis on 05/06/2016.
 */
@ManagedBean
@SessionScoped
public class AdminUserView extends AbstractAdminView<UserModelBean, UserSubmissionModelBean> {

    @ManagedProperty(value = "#{userControllerBean}")
    private UserControllerBean controler;

    public AdminUserView() {
        super("userSubmissionModelBean");
    }

    @Override
    public String getTitle() {
        return isCreationMode() ? "Create User" : "Update User";
    }

    @Override
    public String getButtonFormSubmit() {
        return isCreationMode() ? "Submit New User" : "Update User";
    }

    public void setControler(UserControllerBean controler) {
        this.controler = controler;
    }

    @Override
    protected void putIntoSession(UserModelBean userModelBean) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        viewMap.put(beanName, new UserSubmissionModelBean(userModelBean));
    }


    @Override
    public void update() {

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        UserSubmissionModelBean recipeSubmissionModelBean = (UserSubmissionModelBean) sessionMap.get(beanName);
        controler.update(recipeSubmissionModelBean);
        sessionMap.remove(beanName);
    }

    @Override
    public void create(UserSubmissionModelBean bean) {
        controler.addUser(bean);
    }

}
