package com.video.model.service;


import java.util.ArrayList;
import java.util.List;

import com.video.model.domain.User;

public class UserRepositoryDAO implements IUserDAO {

	private static List<User> usersList = new ArrayList<>();

	//Patron Singleton
	private static UserRepositoryDAO instancia;
	
	private UserRepositoryDAO() {
	}
	
	public static UserRepositoryDAO getInstance() {
		if (instancia==null) {
			instancia= new UserRepositoryDAO();
		}
		return instancia;
	}
	
	@Override
	public List<User> getUsersList(){
		return new ArrayList<>(usersList);
	}
	
	@Override	
	public void addUser(User user) throws Exception{
		if(user==null) throw new NullPointerException("L'usuari no pot ser null.");
		usersList.add(user);
	}

	
}
