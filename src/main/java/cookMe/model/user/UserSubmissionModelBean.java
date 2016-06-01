package cookMe.model.user;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.regex.Pattern;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class UserSubmissionModelBean extends UserModelBean {

    private String pwd1;

    public UserSubmissionModelBean() {

    }

    public UserSubmissionModelBean(int id, String firstname, String lastname, int age, String login, String password, String email, UserType userType, String pwd1) {
        super(id, firstname, lastname, age, login, password, email, userType);
        this.pwd1 = pwd1;
    }

    public UserSubmissionModelBean(UserModelBean user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getAge(), user.getLogin(), user.getPwd(), user.getEmail(), user.getType(), user.getPwd());
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public boolean isValid() {
        boolean ok;
        ok = this.getFirstname() != null && Pattern.compile("[a-zA-Z0-9 ]+").matcher(this.getFirstname()).matches();
        ok &= this.getLastname() != null && Pattern.compile("[a-zA-Z0-9 ]+").matcher(this.getLastname()).matches();
        ok &= this.getAge() > 0 && this.getAge() < 100;
        ok &= this.getEmail() != null && Pattern.compile("[a-zA-Z0-9-._]+@[a-zA-Z0-9-._]+.[a-z]+").matcher(this.getEmail()).matches();
        ok &= this.getLogin() != null && Pattern.compile("[a-zA-Z0-9-._]+").matcher(this.getLogin()).matches();
        ok &= this.getPwd() != null && this.getPwd1() != null && this.getPwd().equals(this.getPwd1());
        return ok;
    }
}
