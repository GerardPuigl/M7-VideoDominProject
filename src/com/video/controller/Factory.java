package com.video.controller;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.video.model.User;
import com.video.model.Video;

public class Factory {

	UserController userController = new UserController();
	VideoController videoController = new VideoController();

	public Factory() {
	}

	public void createUser(String name, String surname, String password) {
		for (User user : userController.getUsers()) {
			if (user.getName().equals(name) && user.getSurname().equals(surname)) {
				throw new KeyAlreadyExistsException("El nom i cognom introduits ja existeixen");
			}
		}
		
		try {
			User user = new User(name, surname, password);
			userController.addUser(user);
		} catch (Exception e) {
			System.out.println("No s'ha pogut crear l'usuari.\n" + e.getMessage());
		}
	}

	public void createVideo(String url, String title, int userId) {
		try {
			Video video = new Video(url, title, userId);
			videoController.addVideo(video);
		} catch (Exception e) {
			System.out.println(e + "\nNo se ha podido crear el video.");
		}
	}

}
