package com.video.view;

import java.time.LocalTime;
import java.util.Scanner;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.video.controller.VideoController;
import com.video.model.User;
import com.video.model.Video;

public class MenuUser {

	VideoController videoController = VideoController.getInstance();
	MenuVideoReproduction menuVideo = new MenuVideoReproduction();

	private int option = 100;
	private boolean repeat;
	private Scanner scan = new Scanner(System.in);

	// menú d'usuari amb sessió iniciada
	public void menuUser(User user) {
		
		System.out.println("\nBenvingut " + user.getName());
		
		do {
			System.out.println("Tria una opció:\n" + 
								"  (1) Afegir un vídeo.\n" + 
								"  (2) Veure els meus vídeos.\n" + 
								"  (3) Veure l'estat de pujada d'un vídeo.\n" + 
								"  (4) Reproduir un dels teus vídeo.\n" + 
								"  (5) Reproduir qualsevol vídeo.\n" +
								"  (0) Finalitzar el programa.\n");

			option = askOption();
			switch (option) {

			// crear vídeo
			case 1:
				try {
					videoController.addVideo(askUrl(), askTitle(), askDuration(), user.getId());
				} catch (Exception e) {
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

			// veure stat de pujada d'un vídeo.
			case 3:
				try {
					viewVideoList(user);
					System.out.println("Estat: " + videoController.getVideoUploadStatus(askVideoId()) + ".");

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			// visualitzar vídeo
			case 4:
				try {
					viewVideoList(user);
					menuVideo.menuVideo(videoController.getVideoWithId(askVideoId()));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 5:
				try {
					for (Video video : videoController.getAllVideos()) {
						System.out.println(
								"VideoID: " + video.getIdVideo() + " Títol: " + video.getTitle() + " Url: " + video.geturl());
					}
					System.out.println("");
					menuVideo.menuVideo(videoController.getVideoWithId(askVideoId()));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 0:
				//Surt del programa 
				break;
				
			default:
				System.out.println("L'opció introduia no existeix.");
				break;
			}
		} while (option != 0);
	}

	// veure llistat de vídeos
	private void viewVideoList(User user) {
		for (Video video : videoController.getVideoList(user.getId())) {
			System.out.println(
					"VideoID: " + video.getIdVideo() + " Títol: " + video.getTitle() + " Url: " + video.geturl());
		}
		System.out.println("");
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

	// preguntar la url a l'usuari.
	private String askUrl() {
		System.out.println("Introduïu la url del vídeo:");
		return scan.next();
	}

	// preguntar el títol del vídeo a l'usuari
	private String askTitle() {
		System.out.println("Introduïu el títol del vídeo:");
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

	// preguntar durada del vídeo a l'usuari
	private LocalTime askDuration() {
		try {
			System.out.println("Introduïu la duració del vídeo en hh:mm:ss");
			return LocalTime.parse(scan.next());
		} catch (Exception e) {
			throw new NullPointerException("Cal la duració en hh:mm:ss");
		}
	}

}
