package com.video.model;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Video {

	private static int idCounter;
	private int idVideo;
	private String url;
	private String title;
	private int userId;
	private LocalDateTime uploadDate;
	private LocalTime duration;
	private List<String> tags= new ArrayList<String>();
	
	public enum UploadStatus {
		Uploading, Verifying, Public;
	}
	
	public Video(String url,String title,int userId,LocalTime duration) throws Exception{
		
		if (url.equals(""))
			throw new NullPointerException("La Url no pot estar buida.");
		if (title.equals(""))
			throw new NullPointerException("EL títol no pot estar buit");
		idVideo=idCounter++;
		this.userId=userId;
		this.url=url;
		this.title=title;
		this.duration=duration;
		uploadDate=LocalDateTime.now();	
	}
	
	public int getUserId() {
		return userId;
	}
	
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public String geturl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public int getIdVideo() {
		return idVideo;
	}
	
	//retorna l'estat de vídeo en funció del temps que fa que s'ha pujat
	public UploadStatus getVideoState() {
		long d1 = Duration.between(uploadDate, LocalDateTime.now()).toMinutes();
		if (d1 <= 1) {
			return UploadStatus.Uploading;
		} else if (d1 >= 1 && d1 <= 3) {
			return UploadStatus.Verifying;
		} else {
			return UploadStatus.Public;
		}
	}

	// codi reproducció vídeo

	public enum Status {
		Stop, Pause, Play;
	}

	private Status videoStatus= Status.Stop;
	private LocalTime reproductionTime = LocalTime.of(0, 0, 0);

	private Timer timer = new Timer();
	private TimerTask play = new TimerTask() {

		@Override
		public void run() {
			if (duration.isAfter(reproductionTime)) {
				reproductionTime = reproductionTime.plusSeconds(1);
			} else {
				videoStatus = Status.Stop;
				System.out.println("El vídeo " + idVideo + " ha finalizado.");
				timer.cancel();
			}
		}
	};

	// play video
	public void playVideo() {
		videoStatus = Status.Play;
		timer.scheduleAtFixedRate(play, 1000, 1000);
	}

	// pause video
	public void pauseVideo() {
		videoStatus = Status.Pause;
		timer.cancel();
		timer.purge();
	}

	// stop video
	public void stopVideo() {
		videoStatus = Status.Stop;
		timer.cancel();
		timer.purge();
		reproductionTime = LocalTime.of(0, 0, 0);
	}

	// estat de reproducció
	public LocalTime getReproductionTime() {
		return reproductionTime;
	}

	// estat de reproducció del vídeo
	public Status getReproductionStauts() {
		return videoStatus;
	}
}
