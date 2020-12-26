package com.video.view;

import java.util.Scanner;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.security.sasl.AuthenticationException;

import com.video.controller.Factory;
import com.video.controller.UserController;
import com.video.model.User;

public class MenuNoLogin {

	Factory constructor = new Factory();
	UserController userController = new UserController();
	
	private int option = 100;
	private boolean repeat;
	private Scanner scan = new Scanner(System.in);
	private User userValidated = null;
	
	// menú d'usuari sense iniciar sessió
	public User userLogin() {

		do {
			System.out.println("No heu iniciat sessió. \n Què voleu fer?\n" + 
								"  (1) Afegir nou usuari\n"	+ 
								"  (2) Iniciar sessió\n" + 
								"  (0) Finalitzar el programa.\n");

			option= askOption();
			switch (option) {

			// crear usuari i el valida
			case 1:
				System.out.println("Crear nou usuari.");
				String name = askName();
				String surname = askSurname();
				String password = askPassword();
				try {
					constructor.createUser(name, surname, password);
				} catch (KeyAlreadyExistsException e) {
					System.out.println("No s'ha pogut crear l'usuari\n" + e.getMessage());
				}
				try {
					userValidated = userController.authenticationUser(name, surname, password);
				} catch (AuthenticationException e) {
					System.out.println(e.getMessage());
				}
				break;

				
			// validar usuari i rebre ID
			case 2:
				System.out.println("Iniciar sessió.");
				try {
					userValidated = userController.authenticationUser(askName(), askSurname(), askPassword());
				} catch (AuthenticationException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 0:
				break;
			default:
				System.out.println("L'opció introduia no existeix.");
				break;
			}
		} while (option != 0 && userValidated == null);

		return userValidated;
	}

	//introduir opció per número
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

	public String askName() {
		System.out.println("Introduïu el nom d'usuari:");
		return scan.next();
	}

	public String askSurname() {
		System.out.println("Introduïu el cognom d'usuari:");
		return scan.next();
	}

	public String askPassword() {
		System.out.println("Introduïu el Password:");
		return scan.next();
	}

}
