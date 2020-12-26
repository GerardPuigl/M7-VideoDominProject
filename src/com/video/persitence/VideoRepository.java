package com.video.persitence;

import java.util.ArrayList;
import java.util.List;

import com.video.model.Video;

public class VideoRepository {

	private static List<Video> videosList = new ArrayList<>();

	public VideoRepository() {
	}
	
	public List<Video> getAllVideos(){
		return new ArrayList<>(videosList);
	}	
	
	public void addVideo(Video video) throws Exception{
		if(video==null) throw new NullPointerException("El video no pot ser null.");
		videosList.add(video);
	}
	
	
}
