package com.video.view;

import java.util.Scanner;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.security.sasl.AuthenticationException;

import com.video.controller.UserController;
import com.video.controller.VideoController;
import com.video.model.domain.User;
import com.video.model.domain.Video;

public class MenuNoLogin {

	private UserController userController = UserController.getInstance();
	private VideoController videoController = VideoController.getInstance();
	private MenuVideoReproduction menuVideo = new MenuVideoReproduction();

	private int option = 100;
	private boolean repeat;
	private Scanner scan = new Scanner(System.in);
	private User userValidated = null;

	// menú d'usuari sense iniciar sessió
	
	public User userLogin() {

		do {
			System.out.println("No heu iniciat sessió. \nTria una opció:\n" + 
								"  (1) Registrar-se.\n"	+ 
								"  (2) Iniciar sessió.\n" + 
								"  (3) Reproduir un vídeo sense inicia sessió.\n"+
								"  (0) Finalitzar el programa.\n");

			option = askOption();
			switch (option) {

			// crear usuari
			
			case 1:
				System.out.println("Crear nou usuari:");
				String name = askName();
				String surname = askSurname();
				String password = askPassword();
				try {
					userController.addUser(name, surname, password);
					System.out.println("Usuari Registrat.\n");
				} catch (KeyAlreadyExistsException e) {
					System.out.println("No s'ha pogut crear l'usuari\n" + e.getMessage());
				}
				break;

			// validar usuari i rebre ID
				
			case 2:
				System.out.println("Iniciar sessió:");
				try {
					userValidated = userController.authenticationUser(askName(), askSurname(), askPassword());
				} catch (AuthenticationException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				try {
					for (Video video : videoController.getAllVideos()) {
						System.out.println("VideoID: " + video.getIdVideo() + " Títol: " + video.getTitle() + " Url: "
								+ video.geturl());
					}
					System.out.println("");
					menuVideo.menuVideo(videoController.getVideoWithId(askVideoId()));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 0:				// surt del programa
				break;

			default:
				System.out.println("L'opció introduia no existeix.");
				break;
			}
		} while (option != 0 && userValidated == null);

		return userValidated;
	}

	// introduir opció per número
	public int askOption() {
		do {
			try {
				option = scan.nextInt();
				repeat = false;
			} catch (Exception e) {
				System.out.println("Cal introduir el número de l'acció que voleu realitzar. Torna-ho a intentar.");
				scan.nextLine();
				repeat = true;
			}
		} while (repeat);
		return option;
	}

	// preguntar el nom d'usuari
	public String askName() {
		System.out.println("Introduïu el nom d'usuari:");
		return scan.next();
	}

	// preguntar el cognom d'usuari
	public String askSurname() {
		System.out.println("Introduïu el cognom d'usuari:");
		return scan.next();
	}

	// preguntar el password
	public String askPassword() {
		System.out.println("Introduïu el Password:");
		return scan.next();
	}

	// preguntar un id d'un vídeo a l'usuari
	private int askVideoId() {
		try {
			System.out.println("Introduïu la id del vídeo que voleu veure:");
			return scan.nextInt();
		} catch (Exception e) {
			throw new NullPointerException("Cal introduir un número.");
		}
	}
}
