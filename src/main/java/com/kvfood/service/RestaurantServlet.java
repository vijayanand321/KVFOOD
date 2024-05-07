package com.kvfood.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kvfood.dao.RestaurantDao;
import com.kvfood.daoImpl.RestaurantDaoImpl;
import com.kvfood.model.Restaurant;

@WebServlet(name = "RestaurantServlet", urlPatterns = { "/getRestaurant" })
public class RestaurantServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String search = req.getParameter("search");
		String city = req.getParameter("city");
		String all = (String) req.getAttribute("all");

		RestaurantDao restaurantDao = new RestaurantDaoImpl();

		List<Restaurant> allRestaurant = restaurantDao.getAllRestaurant();

		if ( search != null ) {

			allRestaurant = allRestaurant.stream().filter(e -> e.getRestaurantName().equalsIgnoreCase(search))
					.collect(Collectors.toList());

		}
		
		

		req.setAttribute("allRestaurant", allRestaurant);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(req, resp);

	}

}
