package step5.model;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Anis on 24/05/2016.
 */

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String login;
	private String pwd;
	
	public LoginBean(){
	
	}
	
	public String getLogin(){
		return login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public String getPwd(){
		return pwd;
	}
	
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	
}
