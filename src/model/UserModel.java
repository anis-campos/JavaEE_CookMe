package model;

public class UserModel {
    private String firstname;
    private String surname;
    private int age;
    private String login;
    private String pwd;

    private int id;

    public UserModel(int id,String lastname, String surname, int age, String login, String pwd) {
        this.id = id;
        this.firstname = lastname;
        this.surname = surname;
        this.age = age;
        this.login = login;
        this.pwd = pwd;
    }


    public String getLastname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.firstname = lastname;
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
        return "UserModel [lastname=" + firstname + ", surname=" + surname
                + ", age=" + age + ", login=" + login + ", pwd=" + pwd + "]";
    }


}
