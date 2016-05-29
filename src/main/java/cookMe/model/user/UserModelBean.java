package cookMe.model.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by djbranbran on 24/05/16.
 */

@ManagedBean
@SessionScoped
public class UserModelBean implements Serializable{
    private String lastname;
    private String firstname;
    private int age;
    private String login;
    private String pwd;
    private String email;
    private UserType type;
    private int id;

    public UserModelBean(){
    	
    }
    
    public UserModelBean(int id, String firstname, String lastname, int age, String login, String password, String email, UserType type) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.pwd = pwd;
        this.email = email;
        this.type = type;
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "UserModelBean{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public enum UserType {
        none,
        standard,
        admin
    }
}
