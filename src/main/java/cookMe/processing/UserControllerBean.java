package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.search.SearchUserBean;
import cookMe.model.user.UserListModelBean;
import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserSubmissionModelBean;
import cookMe.model.user.UserType;
import cookMe.view.DataGridView;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import java.util.List;
import java.util.Map;

import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;


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

        DataGridView dgv = new DataGridView<>(userList);

        getRequestMap().put("dataGridView", dgv);

        return dgv;

    }

    public String checkUser(LoginBean loginBean) {

        UserModelBean user = dao.checkUser(loginBean.getLogin(), loginBean.getPassword());
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
        UserModelBean user = dao.checkUser(loginBean.getLogin(), loginBean.getPassword());


        if (user != null && user.getType() == UserType.admin) {


            Map<String, Object> sessionMap = getSessionMap();

            if (user.getType() == UserType.admin) {
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
        return loggedUser != null && loggedUser.getType() == UserType.admin;
    }

    public boolean isLoggedUserAdmin(ComponentSystemEvent event) {

        UserModelBean user = (UserModelBean) getSessionMap().get("loggedUser");
        if (user == null) return false;

        FacesContext context = FacesContext.getCurrentInstance();
        boolean rep = user.getType() == UserType.admin;
        if (rep) {
            context.addMessage(event.getComponent().getClientId(), new FacesMessage(SEVERITY_INFO, "You can go", "You are already logged ^^"));
        } else {
            context.addMessage(event.getComponent().getClientId(), new FacesMessage(SEVERITY_FATAL, "Authenfication Failded", "Wrong Login and//or Password !"));

        }
        return rep;
    }

    public String logOut() {
        getSessionMap().clear();
        return getRequestUri() + "?faces-redirect=true";
    }

    public String checkAndAddUser(UserSubmissionModelBean userSubmitted) {
        //Vérifier les propriétés de l'utilisateur
        if (userSubmitted != null && userSubmitted.isValid()) {
            UserModelBean user = dao.create(userSubmitted);

            getSessionMap().put("loggedUser", user);

            return getRequestUri() + "?faces-redirect=true";

        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(SEVERITY_FATAL, "Inscription Failded", "Wrong Login and//or Password !"));
        return "";

    }

    public void addUser(UserSubmissionModelBean userSubmitted) {
        if (userSubmitted != null && userSubmitted.isValid()) {
            UserModelBean user = dao.create(userSubmitted);
        }
    }


    public String toMenu() {
        return "adminMenu.jsf?faces-redirect=true";
    }

    public List<String> getUserTypes() {

        return ParsableEnumToList(UserType.class);
    }

    public void update(UserSubmissionModelBean userSubmissionModelBean) {
        if (userSubmissionModelBean != null && userSubmissionModelBean.isValid())
            dao.update(userSubmissionModelBean);
    }
}
