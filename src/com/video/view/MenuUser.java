package com.video.view;

import java.util.Scanner;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.video.controller.VideoController;
import com.video.model.User;
import com.video.model.Video;


public class MenuUser {
	
	VideoController videoController = new VideoController();
	
	private int option = 100;
	private boolean repeat;
	private Scanner scan = new Scanner(System.in);
	
	// menú d'usuari amb sessió iniciada
	public void menuUser(User user) {
		System.out.println("\nBenvingut "+user.getName()+"\n");
		do {
			System.out.println("Tria una opció:\n" + 
								"  (1) Afegir un vídeo.\n"	+ 
								"  (2) Veure els meus vídeos.\n" + 
								"  (0) Finalitzar el programa.\n");

			option= askOption();
			switch (option) {

			// crear vídeo
			case 1:
				try {
					videoController.addVideo(askUrl(), askTitle(), user.getId());
				} catch (KeyAlreadyExistsException e) {
					System.out.println("No s'ha pogut afegir el vídeo.\n" + e.getMessage());
				}
				
			break;

				
			// rebre llistat videos de l'usuari
			case 2:
				try {
					for(Video video:videoController.getVideoList(user.getId())) {
						System.out.println("VideoID: "+video.getIdVideo()+" Títol: "+video.getTitle()+" Url: "+ video.geturl());
					}					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			break;

			case 0:
				break;
			default:
				System.out.println("L'opció introduia no existeix.");
				break;
			}
		} while (option != 0);

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
	
	private String askUrl() {
		System.out.println("Introduïu la url del vídeo:");
		return scan.next();
	}

	private String askTitle() {
		System.out.println("Introduïu el títol del vídeo:");
		return scan.next();
	}

}
