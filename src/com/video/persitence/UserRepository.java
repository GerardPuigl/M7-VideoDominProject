package com.video.persitence;

import java.util.ArrayList;
import java.util.List;

import com.video.model.User;

public class UserRepository {

	private static List<User> usersList = new ArrayList<>();

	public UserRepository() {
	}
	
	public List<User> getAllUsers(){
		return new ArrayList<>(usersList);
	}
	
	public void addUser(User user) throws Exception{
		if(user==null) throw new NullPointerException("L'usuari no pot ser null.");
		usersList.add(user);
	}
	
	
}
