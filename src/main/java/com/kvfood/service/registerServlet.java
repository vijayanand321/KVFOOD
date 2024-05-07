package com.kvfood.service;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public registerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		UserDao userDao = new UserDaoImpl();

		User user = userDao.getUserByName(userName);

		response.setContentType("text/html; charset=UTF-8");

		if (user == null) {

			user = new User();
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(PasswordEncryption.encryptPassword(confirmPassword));

			userDao.addUser(user);

			response.sendRedirect("login.jsp");

		} else {
			PrintWriter writer = response.getWriter();
			writer.print("username is already taken");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
			requestDispatcher.include(request, response);
		}

	}

}
