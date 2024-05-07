package com.kvfood.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kvfood.dao.UserDao;
import com.kvfood.daoImpl.UserDaoImpl;
import com.kvfood.model.User;
import com.kvfood.security.PasswordEncryption;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user=null;
		 session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect("index.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDaoImpl();
		User user=null;
		if(userName!=null) {
		 user = userDao.getUserByName(userName);
		}
		
		session = request.getSession();
		
		response. setContentType("text/html; charset=UTF-8");
		
		if(user!=null) {
			
			if(PasswordEncryption.decryptPassword(user.getPassword()).equals(password)) {
			
			session.setAttribute("user", user);
			response.sendRedirect("index.jsp");
			
			}else {
				
				PrintWriter writer = response.getWriter();
				writer.print("userName or password is incorrect");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.include(request, response);
				
			}
			
		}else {
			PrintWriter writer = response.getWriter();
			writer.print("<h1> no user found plaese register </h1>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
			requestDispatcher.include(request, response);
		}
	}

}
