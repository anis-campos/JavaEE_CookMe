package step2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import step2.db.UserDAO;
import step2.model.UserModelBean;

@WebServlet("/Servlet3")
public class Servlet3 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDAO db;
	
	public Servlet3(){
		super();
	}
	
	@Override
	public void init() throws ServletException{
		super.init();
		
		if(getServletContext().getAttribute("BD") != null){
			db = (UserDAO) getServletContext().getAttribute("BD");
		}
		else{
			db = new UserDAO();
			getServletContext().setAttribute("BD", db);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException{
		        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UserModelBean user = (UserModelBean) request.getSession().getAttribute("myUser");
		db.create(user);
		response.sendRedirect("/step2/display.jsp");
	}
	
}
