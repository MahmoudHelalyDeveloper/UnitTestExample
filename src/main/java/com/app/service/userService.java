package com.app.service;

import com.app.model.User;

import java.util.List;

public  abstract class userService {

	
	public abstract User checkLogin(String userName, String password) throws Exception;

	public  abstract  User getEmployees(Integer id);

	public abstract  User getUser(Integer userId) throws  Exception;


	public  abstract List<User> getUsers();

	public abstract User updateUser(User user);

	public abstract  User checkEmail(String email);
}
