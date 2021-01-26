package com.video.view;

import java.util.Scanner;

import com.video.model.domain.Video;

public class MenuVideoReproduction {

	private int option = 100;
	private boolean repeat;
	private Scanner scan = new Scanner(System.in);

	public void menuVideo(Video video) {
		do {
			System.out.println("Tria una opció:\n" +
								"  (1) Iniciar.\n" + 
								"  (2) Pausar.\n" + 
								"  (3) Parar.\n" + 
								"  (4) Veure estat de reproducció del vídeo.\n" + 
								"  (0) Sortir de la reproducció del vídeo.\n");

			option = askOption();
			switch (option) {

			// iniciar vídeo
			case 1:
				video.playVideo();

				System.out.println(video.getReproductionStatus());
				System.out.println(video.getReproductionTime() + "\n");
				break;

			// pausar el vídeo
			case 2:
				video.pauseVideo();
				System.out.println(video.getReproductionStatus());
				System.out.println(video.getReproductionTime() + "\n");
				break;

			// parar el vídeo
			case 3:
				video.stopVideo();
				System.out.println(video.getReproductionStatus());
				System.out.println(video.getReproductionTime() + "\n");
				break;

			// veure status del vídeo
			case 4:
				System.out.println(video.getReproductionStatus());
				System.out.println(video.getReproductionTime() + "\n");
				break;
			case 0:
				//surt del reproductor de vídeos
				break;
			default:
				System.out.println("L'opció introduia no existeix.");
				break;
			}
		} while (option != 0);
		option = 100;
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

}
