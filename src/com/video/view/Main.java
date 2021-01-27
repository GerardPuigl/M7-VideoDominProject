package com.video.view;

import java.time.LocalTime;

import com.video.controller.*;
import com.video.model.domain.User;

public class Main {

	public static void main(String[] args) {

		MenuNoLogin menuNoLogin = new MenuNoLogin();
		MenuUser menuLogin = new MenuUser();

		User userValidated = null;


		
		//*Test		
		
		UserController userController = UserController.getInstance();
		VideoController videoController = VideoController.getInstance();

		userController.addUser("Gerard", "Puig", "1234");
		videoController.addVideo("www.servidor.com/video1", "Video1", LocalTime.parse("00:03:30"), 0);
		videoController.addVideo("www.servidor.com/video2", "Video2", LocalTime.parse("00:01:10"), 0);

		userController.addUser("Joan", "Gordi", "1111");
		videoController.addVideo("www.servidor.com/video3", "Video3",LocalTime.parse("00:10:30"), 1);
		videoController.addVideo("www.servidor.com/video4", "Video4",LocalTime.parse("00:02:30"), 1);	

		userController.addUser("Jose", "Marín", "4444");
				
		System.out.println("Usuaris de prova\n" +
							"Nom: Gerard	Cognom: Puig	Password: 1234\n" + 
							"Nom: Joan	Cognom: Gordi	Password: 1111\n" + 
							"Nom: Jose	Cognom: Marín	Password: 4444\n");

		//*/

		// menú de crear usuari o iniciar sessió

		if (userValidated == null) {
			userValidated = menuNoLogin.userLogin();
		}

		// menú d'úsuari un cop validat l'usuari

		if (userValidated != null) {
			menuLogin.menuUser(userValidated);
		}

	}

}