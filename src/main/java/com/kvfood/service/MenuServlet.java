package com.kvfood.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kvfood.dao.MenuDao;
import com.kvfood.dao.RestaurantDao;
import com.kvfood.daoImpl.MenuDaoImpl;
import com.kvfood.daoImpl.RestaurantDaoImpl;
import com.kvfood.model.Menu;

@WebServlet(urlPatterns = {"/menus"})
public class MenuServlet extends  HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		
		MenuDao menuDao=new MenuDaoImpl();
		List<Menu> menuList = menuDao.getMenuByRestaurantId(restaurantId);
		
		RestaurantDao restaurantDao = new RestaurantDaoImpl();
				
		req.setAttribute("menuList", menuList);
		req.setAttribute("restaurantName", 
				restaurantDao.getRestaurant(restaurantId).getRestaurantName());
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("menu.jsp");
		requestDispatcher.forward(req, resp);
		
	}
	
	
	
}
