package com.video.view;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.management.openmbean.KeyAlreadyExistsException;

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
								"  (3) Veure l'estat de pujada d'un vídeo.\n" + 
								"  (4) Reproduir un vídeo.\n" +
								"  (0) Finalitzar el programa.\n");

			option= askOption();
			switch (option) {

			// crear vídeo
			case 1:
				try {
					videoController.addVideo(askUrl(), askTitle(), askDuration(), user.getId());
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

			// veure stat de pujada d'un vídeo.
			case 3:
				try {
					viewVideoList(user);
					System.out.println(videoController.getVideoUploadStatus(askVideoId()));
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			break;
			
			//visualitzar vídeo
			case 4:
				try {
					viewVideoList(user);
					PlayVideoMenu(askVideoId());
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
	
	//preguntar la url a l'usuari.
	private String askUrl() {
		System.out.println("Introduïu la url del vídeo:");
		return scan.next();
	}

	//preguntar el títol del vídeo a l'usuari
	private String askTitle() {
		System.out.println("Introduïu el títol del vídeo:");
		return scan.next();
	}
	
	//preguntar un id d'un vídeo a l'usuari
	private int askVideoId() {
		try {
			System.out.println("Introduïu la id del vídeo que voleu veure:");
			return scan.nextInt();
		} catch (Exception e) {
			throw new NullPointerException("Cal introduir un número.");
		}

	}
	
	//preguntar duració del vídeo a l'usuari
	private LocalTime askDuration() {
		try {
			System.out.println("Introduïu la duració del vídeo en hh:mm:ss");
			return LocalTime.parse(scan.next());
		} catch (Exception e) {
			throw new NullPointerException("Cal la duració en hh:mm:ss");
		}
	}
	
	//menú reproducció d'un vídeo.
	private void PlayVideoMenu(int videoId) {
		do {
			System.out.println("Tria una opció:\n" + 
								"  (1) Iniciar.\n"	+ 
								"  (2) Pausar.\n" + 
								"  (3) Parar.\n" + 
								"  (4) Veure estat de reproducció del vídeo.\n" +
								"  (0) Sorti de la reproducció del vídeo.\n");

			option= askOption();
			switch (option) {

			// iniciar vídeo
			case 1:
				videoController.playVideo(videoId);
				
				System.out.println(videoController.getVideoReproductionStatus(videoId));
				System.out.println(videoController.getVideoReproductionTime(videoId) + "\n");
			break;

				
			// pausar el vídeo
			case 2:
				videoController.pauseVideo(videoId);
				System.out.println(videoController.getVideoReproductionStatus(videoId));
				System.out.println(videoController.getVideoReproductionTime(videoId) + "\n");
			break;

			// parar el vídeo
			case 3:
				videoController.stopVideo(videoId);
				System.out.println(videoController.getVideoReproductionStatus(videoId));
				System.out.println(videoController.getVideoReproductionTime(videoId) + "\n");
			break;
			
			// veure status del vídeo
			case 4:
				System.out.println(videoController.getVideoReproductionStatus(videoId));
				System.out.println(videoController.getVideoReproductionTime(videoId) + "\n");
			break;		
			case 0:
				break;
			default:
				System.out.println("L'opció introduia no existeix.");
				break;
			}
		} while (option != 0);
		option=100;
	}

}
