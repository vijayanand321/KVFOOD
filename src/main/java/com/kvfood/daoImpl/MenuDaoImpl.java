package com.kvfood.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kvfood.dao.MenuDao;
import com.kvfood.model.Menu;

public class MenuDaoImpl implements MenuDao {

	private static Connection connection;

	public MenuDaoImpl() {

		String url = "jdbc:mysql://localhost:3306/kvfood";
		String username = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addMenu(Menu menu) {

		String insertMenu = "insert into `menu` (`menuId`, `resturantId`, `itemName`, `description`, `price`, `isAvailable`,`menuImagePath`) values (?,?,?,?,?,?,?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(insertMenu)) {

			preparedStatement.setInt(1, menu.getMenuId());
			preparedStatement.setInt(2, menu.getRestaurantId());
			preparedStatement.setString(3, menu.getItemName());
			preparedStatement.setString(4, menu.getDescription());
			preparedStatement.setInt(5, menu.getPrice());
			preparedStatement.setBoolean(6, menu.isAvailable());
			preparedStatement.setString(7, menu.getMenuImagePath());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Menu getMenu(int menuId) {

		String selectMenuById = "select * from `menu` where `menuId` = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(selectMenuById)) {

			preparedStatement.setInt(1, menuId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return new Menu(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
							resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6),resultSet.getString(7));
				}
			}
			;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void updateMenu(Menu menu) {

		String updatemenu = "update `menu` set  `resturantId`=?, `itemName`=?, `description`=?, `price`=?, `isAvailable`=?,`menuImagePath`=? where menuId=? ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updatemenu)) {

			preparedStatement.setInt(1, menu.getRestaurantId());
			preparedStatement.setString(2, menu.getItemName());
			preparedStatement.setString(3, menu.getDescription());
			preparedStatement.setInt(4, menu.getPrice());
			preparedStatement.setBoolean(5, menu.isAvailable());
			preparedStatement.setString(6, menu.getMenuImagePath());
			
			preparedStatement.setInt(7, menu.getMenuId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMenu(int menuId) {

		String deleteMenu = "delete from `menu` where `menuId`=? ";

		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteMenu)) {

			preparedStatement.setInt(1, menuId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Menu> getAllMenu() {

		String selectAllMenu = "select * from `menu`";

		ArrayList<Menu> menuList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectAllMenu)) {

			while (resultSet.next()) {
				menuList.add(new Menu(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6),resultSet.getString(7)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuList;
	}

	@Override
	public List<Menu> getMenuByRestaurantId(int restaurantId) {
		String selectQuery="select * from `menu` where `restaurantId`=?";
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		ArrayList<Menu> menuList = new ArrayList<>();
		
		try {
			 preparedStatement=connection.prepareStatement(selectQuery);
			preparedStatement.setInt(1, restaurantId);
			
			 resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				menuList.add(new Menu(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getBoolean(6),resultSet.getString(7)));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(resultSet!=null)
				resultSet.close();
				
				if(preparedStatement!=null)
					preparedStatement.close();
				
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return menuList;
	}

}
