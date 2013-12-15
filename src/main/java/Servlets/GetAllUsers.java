package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;

import database.PostgreSQLAdaptor;
import database.UsersModel;


/**
 * Servlet implementation class for Servlet: GetAllUsers
 *
 */
 public class GetAllUsers extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GetAllUsers() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userAgent =  request.getHeader("user-agent");
		String clientBrowser =  "Not known!";	
		if( userAgent != null)
			clientBrowser = userAgent;
		request.setAttribute("fuck",PostgreSQLAdaptor.getInstance().initialize());
		UsersModel user1 = new UsersModel();
		user1.fromDatabase(3);
		System.out.println(user1.toString());
		String exist =user1.remove() ? "REMOVED":"NOT REMOVED"; 
		System.out.println(exist+"  "+user1.toString());
		
		
		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}   	  	    
}