package com.kvfood.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kvfood.dao.UserDao;
import com.kvfood.daoImpl.UserDaoImpl;
import com.kvfood.model.User;
import com.kvfood.security.PasswordEncryption;

/**
 * Servlet implementation class PasswordReset
 */
@WebServlet("/PasswordReset")
public class PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordReset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String req = request.getParameter("req");
		
		String email = request.getParameter("email");
		
		String resp=null;
		
		UserDao userDao=new UserDaoImpl();
		
		List<User> allUser = userDao.getAllUser();
		
		User user=isAvailable(allUser, email);
		
			
				
				if(req!=null ) {
					user=(User)request.getSession().getAttribute("user");
					String password=request.getParameter("confirmPassword");
					user.setPassword(PasswordEncryption.encryptPassword(password));
					new UserDaoImpl().updateUSer(user);
					response.sendRedirect("login.jsp");
				}
				else {
					
					if(user!=null) {
					resp="forgotPasswordReset";
					request.setAttribute("resp", resp);
					request.getSession().setAttribute("user", user);
				}
					else {
						
						PrintWriter writer = response.getWriter();
						writer.print("email is invalid");
					}
					response. setContentType("text/html; charset=UTF-8");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("passwordReset.jsp");
					requestDispatcher.include(request, response);
		    }
			
	}

	public static User isAvailable(List<User> allUser , String email) {
		
		for(User user : allUser ) {
			if(user.getEmail().equals(email))
				return user;
		}
		
		return null;
	}
}
