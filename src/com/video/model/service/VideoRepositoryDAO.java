package com.video.model.service;

import java.util.ArrayList;
import java.util.List;

import com.video.model.domain.Video;

public class VideoRepositoryDAO implements IVideoDAO {

	private static List<Video> videosList = new ArrayList<>();
	
	//Patron Singleton
	private static VideoRepositoryDAO instancia;
	
	private VideoRepositoryDAO() {
	}
	
	public static VideoRepositoryDAO getInstance() {
		if (instancia==null) {
			instancia= new VideoRepositoryDAO();
		}
		return instancia;
	}

	@Override
	public List<Video> getAllVideos(){
		return new ArrayList<>(videosList);
	}	
	
	@Override
	public void addVideo(Video video) throws NullPointerException{
		if(video==null) throw new NullPointerException("El video no pot ser null.");
		videosList.add(video);
	}
	
}
