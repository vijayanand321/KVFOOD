package com.kvfood.daoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kvfood.dao.UserDao;
import com.kvfood.model.User;


public class UserDaoImpl implements UserDao {

	private static Connection connection = null;

	public UserDaoImpl() {

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
	public int addUser(User user) {

		String insertQuery = "insert into `user`(`userName`, `password`, `email`, `address`,`role`)" + "values(?,?,?,?,?)";
		int status = 0;

		try {
			PreparedStatement statement = connection.prepareStatement(insertQuery);

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getRole());

			status = statement.executeUpdate();

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public User getUser(int userId) {

		String selectQuery = "select * from `user` where `userId`=?";
		User user = null;

		try {
			PreparedStatement statement = connection.prepareStatement(selectQuery);

			statement.setInt(1, userId);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7),
						resultSet.getDate(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	@Override
	public User getUserByName(String userName) {
		
		String selectQuery = "select * from `user` where `userName`=?";
		User user = null;

		try {
			PreparedStatement statement = connection.prepareStatement(selectQuery);

			statement.setString(1, userName);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7),
						resultSet.getDate(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
		
	}
	
	@Override
	public void updateUSer(User user) {

		String updateQuery = "update `user` set  `userName`=?, `password`=?, `email`=?, `address`=?, `role`=? where `userId`=?";

		try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {

			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getRole());
			statement.setInt(6, user.getUserId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {

		String deleteQuery = "delete from `user` where `userId`=?";

		try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

			statement.setInt(1, userId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUser() {

		String selectAllQuery = "select * from `user`";
		User user = null;
		ArrayList<User> userList = new ArrayList<>();

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(selectAllQuery)) {

			while (resultSet.next()) {

				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7),
						resultSet.getDate(8));

				userList.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}


}
