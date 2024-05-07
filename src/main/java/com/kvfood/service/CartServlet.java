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

import com.kvfood.dao.MenuDao;
import com.kvfood.daoImpl.MenuDaoImpl;
import com.kvfood.model.CartItem;
import com.kvfood.model.Menu;
import com.kvfoods.util.CartItemCreator;

@WebServlet(urlPatterns = {"/carts"})
public class CartServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int menuId= Integer.parseInt(req.getParameter("menuId"));
		int operator=Integer.parseInt(req.getParameter("operator"));
		HttpSession session = req.getSession();
		CartItemCreator cartItemCreator = (CartItemCreator) session.getAttribute("cartItemCreator");

		if(operator==1) {
		
		if (cartItemCreator.getCart().containsKey(menuId)) {
			cartItemCreator.updateCartItem(menuId,
					((CartItem)cartItemCreator.getCart().get(menuId)).getQuantity()+operator);
		} else {
			MenuDao menuDao = new MenuDaoImpl();
			Menu menu = menuDao.getMenu(menuId);
			CartItem cartItem = new CartItem(menu.getMenuId(), menu.getRestaurantId(), menu.getItemName(),
					menu.getPrice(), 1);
			cartItemCreator.addCartItem(cartItem);
			
		}
		
		}else {
			cartItemCreator.deleteCartItem(menuId);
			
		}

		 resp.sendRedirect("cart.jsp");

	}

}
