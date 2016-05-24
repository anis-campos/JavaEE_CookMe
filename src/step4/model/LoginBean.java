package step4.model;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

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
