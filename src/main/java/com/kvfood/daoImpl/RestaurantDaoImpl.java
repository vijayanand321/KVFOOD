package com.kvfood.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kvfood.dao.RestaurantDao;
import com.kvfood.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao{
	
	private static Connection connection;
	
	
	public RestaurantDaoImpl() {
		
		String url = "jdbc:mysql://localhost:3306/kvfood";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		String insertQuery ="insert into `restaurant`"
     		+ "( `restaurantName`, `rating`, "
     		+ "`imagePath`, `eta`, `cuisineType`, `address`, `isActive`, `restaurantOwnerId`)"
     		+ "values (?,?,?,?,?,?,?,?)";
     
        try {
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, restaurant.getRestaurantName());
			preparedStatement.setInt(2, restaurant.getRating());
			preparedStatement.setString(3, restaurant.getImagePath());
			preparedStatement.setInt(4, restaurant.getEta());
			preparedStatement.setString(5, restaurant.getCusineType());
			preparedStatement.setString(6 , restaurant.getAddress());
			preparedStatement.setBoolean(7, restaurant.isActive());
			preparedStatement.setInt(8,restaurant.getRestaurantOwnerId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		Restaurant restaurant=null;
		String selectQuery ="select * from `restaurant` where `restaurantId`=?";
		
		try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
			statement.setInt(1, restaurantId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
			restaurant=  new Restaurant(restaurantId,resultSet.getString(2),
			      resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), 
			      resultSet.getString(7), resultSet.getBoolean(8), resultSet.getInt(9));
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
      
		String updateQuery ="update `restaurant` set `restaurantName`=?, "
				+ "`rating`=?, `imagePath`=?, `eta`=?, `cuisineType`=?, `address`=?,"
				+ " `isActive`=?, `restaurantOwnerId`=? where `restaurantId`=?";
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, restaurant.getRestaurantName());
			preparedStatement.setInt(2, restaurant.getRating());
			preparedStatement.setString(3, restaurant.getImagePath());
			preparedStatement.setInt(4, restaurant.getEta());
			preparedStatement.setString(5, restaurant.getCusineType());
			preparedStatement.setString(6 , restaurant.getAddress());
			preparedStatement.setBoolean(7, restaurant.isActive());
			preparedStatement.setInt(8,restaurant.getRestaurantOwnerId());
			preparedStatement.setInt(9, restaurant.getRestaurantId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
      String deleteQuery ="delete from `restaurant` where `restaurantId`=?"	;
      
      try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
    	  
    	  preparedStatement.setInt(1, restaurantId);
    	  preparedStatement.executeUpdate();
    	  
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		ResultSet resultSet=null;
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		String selectAllQuery ="select * from `restaurant`";
		
		try (Statement statement = connection.createStatement()) {
			 resultSet = statement.executeQuery(selectAllQuery);
			
			while(resultSet.next()) {
				restaurantList.add(new Restaurant(resultSet.getInt(1),resultSet.getString(2),
			      resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), 
			      resultSet.getString(7), resultSet.getBoolean(8), resultSet.getInt(9)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 finally {
	    	  try {
	    		  if(connection!=null)
				connection.close();
	    		  
	    		  if(resultSet!=null)
	    			  resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	      }
		
		return restaurantList;
		
      
	}

}

