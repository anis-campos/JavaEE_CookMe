package cookMe.processing;

import cookMe.dao.fabric.DaoFabric;
import cookMe.dao.instance.UserDao;
import cookMe.model.LoginBean;
import cookMe.model.UserModelBean;
import cookMe.model.UserSubmissionModelBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@ApplicationScoped
public class UserControlerBean {
	
	private UserDao userDao;
	
	public UserControlerBean(){
		this.userDao = DaoFabric.getInstance().createUserDao();
	}
	
	public String checkUser(LoginBean loginBean){
		UserModelBean user = this.userDao.checkUser(loginBean.getLogin(), loginBean.getPwd());
		
		if(user != null){
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> sessionMap = externalContext.getSessionMap();
			
			sessionMap.put("loggedUser", user);
			
			return "userDisplay.xhtml";
		}
		else{
			return "userLogin.xhtml";
		}
	}
	
	public void checkAndAddUser(UserSubmissionModelBean userSubmitted){
		//Vérifier les propriétés de l'utilisateur
		if(userSubmitted != null){
			if(userSubmitted.getFirstname() != null
				&& userSubmitted.getLastname() != null
				//&& userSubmitted.getAge() != null
				&& userSubmitted.getEmail() != null
				&& userSubmitted.getLogin() != null
				&& userSubmitted.getPwd() != null){

                if(Pattern.compile("[a-zA-Z0-9]").matcher(userSubmitted.getFirstname()).matches()
                        && Pattern.compile("[a-zA-Z0-9]").matcher(userSubmitted.getLastname()).matches()
                        && userSubmitted.getAge() < 100
                        && Pattern.compile("[a-zA-Z0-9-._]+@[a-zA-Z0-9-._].[a-z]+").matcher(userSubmitted.getEmail()).matches()
                        && Pattern.compile("[a-zA-Z0-9-._]").matcher(userSubmitted.getLogin()).matches()
                        && Pattern.compile("[a-zA-Z0-9]").matcher(userSubmitted.getFirstname()).matches()){

                }
			}
		}
		this.userDao.addUser(userSubmitted);
	}
}
