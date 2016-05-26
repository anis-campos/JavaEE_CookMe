package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.UserListModelBean;
import cookMe.model.UserModelBean;
import cookMe.model.UserSubmissionModelBean;
import cookMe.view.DataGridView;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Created by Brandon PD d'anis on 24/05/2016.
 */
@ManagedBean
@ApplicationScoped
public class UserControlerBean {

    private UserDao userDao;

    public UserControlerBean() {
        this.userDao = DaoFabric.getInstance().createUserDao();
    }

    public void getUserList() {

        UserListModelBean userList = new UserListModelBean(userDao.getAllUser());

        DataGridView dgv = new DataGridView<UserListModelBean, UserModelBean>(userList);

        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> requestMap = externalContext.getRequestMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        requestMap.put("dataGridView", dgv);


    }

    public String checkUser(LoginBean loginBean) {
        UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        if (user != null) {

            Map<String, Object> sessionMap = externalContext.getSessionMap();

            sessionMap.put("loggedUser", user);


        }
        return request.getRequestURI() + "?faces-redirect=true";
    }

    public String checkUserAdmin(LoginBean loginBean) {
        UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());


        if (user != null && user.getType() == UserModelBean.UserType.admin) {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            String uri = request.getRequestURI();
            Map<String, Object> sessionMap = externalContext.getSessionMap();

            if (user.getType() == UserModelBean.UserType.admin) {
                sessionMap.put("loggedUser", user);
                return toMenu();
            }

        }
        return "";
    }



    public boolean isAdmin(UserModelBean loggedUser) {
        return loggedUser != null && loggedUser.getType() == UserModelBean.UserType.admin;
    }

    public void logOut() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.remove("loggedUser");
    }

    public void checkAndAddUser(UserSubmissionModelBean userSubmitted) {
        //Vérifier les propriétés de l'utilisateur
        if (userSubmitted != null) {
            boolean ok = true;
            if (userSubmitted.getFirstname() != null && Pattern.compile("[a-zA-Z0-9]").matcher(userSubmitted.getFirstname()).matches()) {

            } else {
                ok = false;
            }
            if (userSubmitted.getLastname() != null && Pattern.compile("[a-zA-Z0-9]").matcher(userSubmitted.getLastname()).matches()) {

            } else {
                ok = false;
            }
            if (userSubmitted.getAge() > 0 && userSubmitted.getAge() < 100) {

            } else {
                ok = false;
            }
            if (userSubmitted.getEmail() != null && Pattern.compile("[a-zA-Z0-9-._]+@[a-zA-Z0-9-._].[a-z]+").matcher(userSubmitted.getEmail()).matches()) {

            } else {
                ok = false;
            }
            if (userSubmitted.getLogin() != null && Pattern.compile("[a-zA-Z0-9-._]").matcher(userSubmitted.getLogin()).matches()) {

            } else {
                ok = false;
            }
            if (userSubmitted.getPwd() != null && userSubmitted.getPwd1() != null && userSubmitted.getPwd().equals(userSubmitted.getPwd1())) {

            } else {
                ok = false;
            }

            if (ok)
                this.userDao.addUser(userSubmitted);
        }


    }

    public String toMenu() {
        return "adminMenu.jsf?faces-redirect=true";
    }
}
