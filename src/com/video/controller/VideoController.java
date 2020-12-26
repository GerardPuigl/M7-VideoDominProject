package com.video.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.video.model.Video;
import com.video.persitence.VideoRepository;

public class VideoController implements IVideoDao {

	private VideoRepository videoList = new VideoRepository();
	UserController userController=new UserController();

	//afegir nou vídeo
	@Override
	public void addVideo(String url, String title, int userId) {
		
		for (Video video : videoList.getAllVideos()) {
			if (video.geturl().equals(url)) {
				throw new KeyAlreadyExistsException("El vídeo introduit ja existeix.");
			}
		}
		
		try {
			Video video = new Video(url, title, userId);
			videoList.addVideo(video);
		} catch (Exception e) {
			System.out.println("No s'ha pogut afegir el vídeo."+e.getMessage());
		}
	}

	//llista de vídeos de l'usuari
	@Override
	public List<Video> getVideoList(int userId) {
		List<Video> userVideoList=new ArrayList<>();
		for (Video video:videoList.getAllVideos()) {
			if (video.getUserId()==userId) {
				userVideoList.add(video);
			}
		}
		
		if (userVideoList.size()==0) {
			throw new NullPointerException("Encara no teniu cap vídeo.");
		}
		return userVideoList;
	}

}
