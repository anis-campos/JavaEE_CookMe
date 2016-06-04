package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.search.SearchUserBean;
import cookMe.model.user.UserListModelBean;
import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserSubmissionModelBean;
import cookMe.view.DataGridView;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

import static javax.faces.application.FacesMessage.SEVERITY_FATAL;


/**
 * Created by Brandon PD d'anis on 24/05/2016.
 */
@ManagedBean
@ApplicationScoped
public class UserControllerBean extends AbstractController<UserModelBean, UserDao, SearchUserBean> {


    public UserControllerBean() {
        super(DaoFabric.getInstance().createUserDao());
    }

    public DataGridView<UserListModelBean, UserModelBean> getUserList() {

        List<UserModelBean> allUser = dao.getAll();
        UserListModelBean userList = new UserListModelBean(allUser);

        DataGridView dgv = new DataGridView<UserListModelBean, UserModelBean>(userList);

        getRequestMap().put("dataGridView", dgv);

        return dgv;

    }

    public String checkUser(LoginBean loginBean) {

        UserModelBean user = dao.checkUser(loginBean.getLogin(), loginBean.getPwd());
        if (user != null) {

            getSessionMap().put("loggedUser", user);

            return getRequestUri() + "?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(SEVERITY_FATAL, "Authenfication Failded", "Wrong Login and//or Password !"));
            return "";
        }


    }

    public String checkUserAdmin(LoginBean loginBean) {
        UserModelBean user = dao.checkUser(loginBean.getLogin(), loginBean.getPwd());


        if (user != null && user.getType() == UserModelBean.UserType.admin) {


            Map<String, Object> sessionMap = getSessionMap();

            if (user.getType() == UserModelBean.UserType.admin) {
                sessionMap.put("loggedUser", user);
                String redirectFromLogin = (String) sessionMap.get("redirectFromLogin");
                if (redirectFromLogin != null) {
                    sessionMap.remove("redirectFromLogin");
                    return redirectFromLogin + "?faces-redirect=true";
                }
                return toMenu();
            }


        }
        return "";
    }

    public void remove(UserModelBean user) {
        this.dao.delete(user);
    }

    public boolean isAdmin(UserModelBean loggedUser) {
        return loggedUser != null && loggedUser.getType() == UserModelBean.UserType.admin;
    }

    public String logOut() {
        getSessionMap().remove("loggedUser");
        return getRequestUri() + "?faces-redirect=true";
    }

    public void checkAndAddUser(UserSubmissionModelBean userSubmitted) {
        //Vérifier les propriétés de l'utilisateur
        if (userSubmitted != null && userSubmitted.isValid())
            dao.create(userSubmitted);

    }


    public String toMenu() {
        return "adminMenu.jsf?faces-redirect=true";
    }

    public List<String> getUserTypes() {

        return enumToList(UserModelBean.UserType.class);
    }

    public void update() {
        UserSubmissionModelBean userSubmitted = (UserSubmissionModelBean) getSessionMap().get("userSubmissionModelBean");
        if (userSubmitted != null && userSubmitted.isValid())
            dao.update(userSubmitted);

        getSessionMap().remove("userSubmissionModelBean");
    }
}
