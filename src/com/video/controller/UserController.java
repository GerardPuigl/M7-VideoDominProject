package com.video.controller;

import java.util.List;
import java.util.Scanner;

import javax.security.sasl.AuthenticationException;

import com.video.model.User;
import com.video.persitence.UserRepository;

public class UserController implements IUserDao {

	Scanner scan = new Scanner(System.in);
	private UserRepository userList = new UserRepository();
	
	//autentificació d'usuari en escala (més velocitat)
	@Override
	public User authenticationUser(String name, String surname, String password) throws AuthenticationException {
		for (User user : userList.getAllUsers()) {
			if (user.getName().equals(name)) {
				if (user.getSurname().equals(surname)) {
					if (user.getPassword().equals(password)) {
						return user;
					}
				}
			}
		}
		throw new AuthenticationException("L'usuari no existeix o el password és incorrecte.");
	}

	@Override
	public void addUser(User user) {
		try {
			userList.addUser(user);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<User> getUsers() {
		return 	userList.getAllUsers();
	}

}
