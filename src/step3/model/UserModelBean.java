package step3.model;

import java.io.Serializable;

/**
 * Created by djbranbran on 23/05/16.
 */
public class UserModelBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
    private String lastname;
    private String firstname;
    private int age;
    private String login;
    private String pwd;
    private String email;

    public UserModelBean() {
    }

    public UserModelBean(String firstname, String lastname, int age, String login, String pwd) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.pwd = pwd;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setId(Integer id){
    	this.id = id;
    }

    @Override
    public String toString() {
        return "UserModelBean{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
