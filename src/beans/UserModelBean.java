package beans;

import java.io.Serializable;

/**
 * Created by djbranbran on 23/05/16.
 */
public class UserModelBean implements Serializable {
    private String lastName;
    private String surname;
    private int age;
    private String login;
    private String pwd;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String toString() {
        return "UserModelBean{" +
                "lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}