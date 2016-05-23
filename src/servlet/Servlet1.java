package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private DB db;
	
	public Servlet1(){
		super();
		db = new DB();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws
			ServletException, IOException { 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) 
					throws
					ServletException, IOException {
	}
}
