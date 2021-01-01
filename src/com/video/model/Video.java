package com.video.model;

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
	private List<String> tags= new ArrayList<String>();
	
	private enum State {
		Uploading, Verifying, Public;
	}
	
	
	public Video(String url,String title,int userId) throws Exception{
		
		if (url.equals(""))
			throw new NullPointerException("La Url no pot estar buida.");
		if (title.equals(""))
			throw new NullPointerException("EL títol no pot estar buit");
		idVideo=idCounter++;
		this.userId=userId;
		this.url=url;
		this.title=title;
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
	public String getVideoState() {
		long d1 = Duration.between(uploadDate, LocalDateTime.now()).toMinutes();
		if (d1 <= 1) {
			return State.Uploading.toString();
		} else if (d1 >= 1 && d1 <= 3) {
			return State.Verifying.toString();
		} else {
			return State.Public.toString();
		}
	}
}
