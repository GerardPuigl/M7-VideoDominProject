package com.video.model;

import java.util.*;

public class Video {

	private static int idCounter;
	private int idVideo;
	private String url;
	private String title;
	private int userId;
	private List<String> tags= new ArrayList<String>();
	
	public Video(String url,String title,int userId) throws Exception{
		
		if (url.equals(""))
			throw new NullPointerException("La Url no pot estar buida.");
		if (title.equals(""))
			throw new NullPointerException("EL títol no pot estar buit");
		idVideo=idCounter++;
		this.userId=userId;
		this.url=url;
		this.title=title;
	}
	
	public int getId() {
		return idVideo;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
}
