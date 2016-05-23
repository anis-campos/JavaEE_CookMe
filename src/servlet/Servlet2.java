package servlet;

import db.UserDAO;
import model.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by djbranbran on 23/05/16.
 */
@WebServlet("/servlet2")
public class Servlet2 extends HttpServlet{
    private UserDAO userDao;

    public Servlet2(){
        super();
        userDao=new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        UserModel user = new UserModel(
                Integer.valueOf(request.getParameter("id")),
                request.getParameter("lastname"),
                request.getParameter("surname"),
                Integer.valueOf(request.getParameter("age")),
                request.getParameter("login"),
                request.getParameter("pwd"));
        userDao.create(user);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }


}
