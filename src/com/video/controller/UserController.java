package com.video.controller;

import java.util.List;
import javax.management.openmbean.KeyAlreadyExistsException;
import javax.security.sasl.AuthenticationException;

import com.video.model.User;
import com.video.persitence.UserRepository;

public class UserController implements IUserDao {

	private UserRepository userList = new UserRepository();

	//crear usuari
	@Override
	public void addUser(String name, String surname, String password) {
		for (User user : getUsers()) {
			if (user.getName().equalsIgnoreCase(name) && user.getSurname().equalsIgnoreCase(surname)) {
				throw new KeyAlreadyExistsException("El nom i cognom introduits ja existeixen.");
			}
		}
		try {
			User user = new User(name, surname, password);
			userList.addUser(user);
		} catch (Exception e) {
			System.out.println("No s'ha pogut crear l'usuari.\n" + e.getMessage());
		}
	}
	
	//autentificació d'usuari en escala.
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
	public List<User> getUsers() {
		return 	userList.getAllUsers();
	}
	

}
