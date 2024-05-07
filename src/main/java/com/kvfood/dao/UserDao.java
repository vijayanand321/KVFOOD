package com.kvfood.dao;

import java.util.List;

import com.kvfood.model.User;

public interface UserDao {

	int addUser(User user);
	User getUser(int userId);
	User getUserByName (String userName);
	void updateUSer(User user);
	void deleteUser(int userId);
	List<User> getAllUser();
	
}
