package cookMe.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Anis on 24/05/2016.
 */
@ManagedBean
@RequestScoped
public class UserSubmissionModelBean extends UserModelBean {

	private String pwd1;

	public UserSubmissionModelBean(){
		
	}

    public UserSubmissionModelBean(int id, String firstname, String lastname, int age, String login, String password, String email, UserType userType, String pwd1) {
        super(id, firstname, lastname, age, login, password, email, userType);
        this.pwd1 = pwd1;
    }

    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }
}
