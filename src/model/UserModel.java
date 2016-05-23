package model;

public class UserModel {
    private String firstname;
    private String lastname;
    private int age;
    private String login;
    private String pwd;

    private int id;
    private String email;

    public UserModel(int id, String firstname, String lastname, int age, String login, String pwd) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.login = login;
        this.pwd = pwd;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String lastname) {
        this.firstname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
        return "UserModel [firstname=" + firstname + ", lastname=" + lastname
                + ", age=" + age + ", login=" + login + ", pwd=" + pwd + "]";
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
