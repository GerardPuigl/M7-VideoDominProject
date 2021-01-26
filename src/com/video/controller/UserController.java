package com.video.controller;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.security.sasl.AuthenticationException;

import com.video.model.domain.User;
import com.video.model.service.IUserDAO;
import com.video.model.service.UserFactoryDAO;

public class UserController {

	// patró Singleton

	private static UserController instancia;

	private UserController() {
	}

	public static UserController getInstance() {
		if (instancia == null) {
			instancia = new UserController();
		}
		return instancia;
	}

	// patró Dao

	private IUserDAO iUserDao = getDAO();

	private UserFactoryDAO userFactoryDAO = null;

	private IUserDAO getDAO() {
		if (userFactoryDAO == null) {
			userFactoryDAO = new UserFactoryDAO();
		}

		return userFactoryDAO.getDefaultPersistence();

	}

	// crear usuari

	public void addUser(String name, String surname, String password) {
		for (User user : iUserDao.getUsersList()) {
			if (user.getName().equalsIgnoreCase(name) && user.getSurname().equalsIgnoreCase(surname)) {
				throw new KeyAlreadyExistsException("El nom i cognom introduits ja existeixen.");
			}
		}
		try {
			User user = new User(name, surname, password);
			iUserDao.addUser(user);
		} catch (Exception e) {
			System.out.println("No s'ha pogut crear l'usuari.\n" + e.getMessage());
		}
	}

	// autentificació d'usuari en escala.

	public User authenticationUser(String name, String surname, String password) throws AuthenticationException {
		for (User user : iUserDao.getUsersList()) {
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

}
