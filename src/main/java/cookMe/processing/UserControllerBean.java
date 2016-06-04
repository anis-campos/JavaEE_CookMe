package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.search.SearchUserBean;
import cookMe.model.user.UserListModelBean;
import cookMe.model.user.UserModelBean;
import cookMe.model.user.UserSubmissionModelBean;
import cookMe.view.DataGridView;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


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

        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> requestMap = externalContext.getRequestMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        requestMap.put("dataGridView", dgv);

        return dgv;

    }

    public String checkUser(LoginBean loginBean) {
        UserModelBean user = dao.checkUser(loginBean.getLogin(), loginBean.getPwd());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        if (user != null) {


            sessionMap.put("loggedUser", user);


        }

        return request.getRequestURI() + "?faces-redirect=true";
    }

    public String checkUserAdmin(LoginBean loginBean) {
        UserModelBean user = dao.checkUser(loginBean.getLogin(), loginBean.getPwd());


        if (user != null && user.getType() == UserModelBean.UserType.admin) {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            String uri = request.getRequestURI();
            Map<String, Object> sessionMap = externalContext.getSessionMap();

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
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.remove("loggedUser");

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        return request.getRequestURI() + "?faces-redirect=true";
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