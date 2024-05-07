package com.kvfood.dao;

import java.util.List;

import com.kvfood.model.Menu;

public interface MenuDao {

	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	List<Menu> getAllMenu();
	List<Menu> getMenuByRestaurantId(int restaurantId);
	
}
