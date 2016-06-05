package cookMe.model.user;

import cookMe.model.ISubmissionModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.regex.Pattern;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class UserSubmissionModelBean extends UserModelBean implements ISubmissionModel {

    private String repeatedPassword;

    public UserSubmissionModelBean() {
        super();
    }

    public UserSubmissionModelBean(int id, String firstname, String lastname, int age, String login, String password, String email, UserType userType, String repeatedPassword) {
        super(id, firstname, lastname, age, login, password, email, userType);
        this.repeatedPassword = repeatedPassword;
    }

    public UserSubmissionModelBean(UserModelBean user) {
        this(user.getId(), user.getFirstname(), user.getLastname(), user.getAge(), user.getLogin(), user.getPassword(), user.getEmail(), user.getType(), user.getPassword());
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    @Override
    public boolean isValid() {
        boolean ok;
        ok = this.getFirstname() != null && Pattern.compile("[a-zA-Z0-9 ]+").matcher(this.getFirstname()).matches();
        ok &= this.getLastname() != null && Pattern.compile("[a-zA-Z0-9 ]+").matcher(this.getLastname()).matches();
        ok &= this.getAge() > 0 && this.getAge() < 100;
        ok &= this.getEmail() != null && Pattern.compile("[a-zA-Z0-9-._]+@[a-zA-Z0-9-._]+.[a-z]+").matcher(this.getEmail()).matches();
        ok &= this.getLogin() != null && Pattern.compile("[a-zA-Z0-9-._]+").matcher(this.getLogin()).matches();
        ok &= this.getPassword() != null && this.getRepeatedPassword() != null && this.getPassword().equals(this.getRepeatedPassword());
        return ok;
    }
}
