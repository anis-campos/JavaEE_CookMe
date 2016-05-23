package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import db.UserDAO;

@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private UserDAO db;
	
	public Servlet1(){
		super();
		db = new UserDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws
			ServletException, IOException { 
		String preRetour = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Servlet1</title></head><body>[<br>";
		String postRetour = "]</body>";
		String retour = "";
		List<UserModel> listUserModel = db.getAll();	
		if(listUserModel != null){
			for(UserModel um : listUserModel){
				retour += um + "<br>";
			}
		}
		
		PrintWriter wr = response.getWriter();
		wr.println(preRetour);
		wr.println(retour);
		wr.println(postRetour);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
	}
}
