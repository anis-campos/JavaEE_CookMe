package step4.processing;

import java.util.Map;

import javax.annotation.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import step3.dao.fabric.DaoFabric;
import step4.model.UserSubmissionModelBean;

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
		//TODO
		
		this.userDao.addUser(userSubmitted);
	}
}
