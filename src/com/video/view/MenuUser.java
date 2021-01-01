package com.video.view;

import java.util.Scanner;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.DefaultRowSorter;

import com.video.controller.VideoController;
import com.video.model.User;
import com.video.model.Video;


public class MenuUser {
	
	VideoController videoController = new VideoController();
	
	private int option = 100;
	private int videoId;
	private boolean repeat;
	private Scanner scan = new Scanner(System.in);
	
	// menú d'usuari amb sessió iniciada
	public void menuUser(User user) {
		System.out.println("\nBenvingut "+user.getName());
		do {
			System.out.println("Tria una opció:\n" + 
								"  (1) Afegir un vídeo.\n"	+ 
								"  (2) Veure els meus vídeos.\n" + 
								"  (3) Veure l'estat d'un dels vídeos.\n" + 
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
					viewVideoList(user);					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			break;

			//imprimeix llista dels teus vídeos y pots veure el seu estat
			case 3:
				try {
					viewVideoList(user);
					System.out.println(videoController.getVideoState(askVideoId()));
					
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

	//veure llistat de vídeos
	private void viewVideoList(User user) {
		for(Video video:videoController.getVideoList(user.getId())) {
			System.out.println("VideoID: "+video.getIdVideo()+" Títol: "+video.getTitle()+" Url: "+ video.geturl());
		}
		System.out.println("");
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
	
	private int askVideoId() {
		try {
			System.out.println("Introduïu la id del vídeo que voleu veure:");
			return scan.nextInt();
		} catch (Exception e) {
			throw new NullPointerException("Cal introduir un número.");
		}

	}
	

}
