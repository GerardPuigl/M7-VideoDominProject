package com.video.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.video.model.Video;
import com.video.model.Video.Status;
import com.video.model.Video.UploadStatus;
import com.video.persitence.VideoRepository;

public class VideoController implements IVideoDao {

	private VideoRepository videoList = new VideoRepository();
	UserController userController=new UserController();

	//afegir nou vídeo
	@Override
	public void addVideo(String url, String title, LocalTime duration, int userId) {
		
		for (Video video : videoList.getAllVideos()) {
			if (video.geturl().equals(url)) {
				throw new KeyAlreadyExistsException("El vídeo introduit ja existeix.");
			}
		}
		
		try {
			Video video = new Video(url, title, userId, duration);
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
		
	//veure estat de pujada del vídeo
	@Override
	public Enum<UploadStatus> getVideoUploadStatus(int videoId) {
		for (Video video:videoList.getAllVideos()) {
			if (video.getIdVideo()==videoId){
				return video.getVideoState();
			}
		}
		throw new NullPointerException("No existeix cap vídeo amb aquesta url");
	}

	//iniciar reproducció vídeo
	@Override
	public void playVideo(int videoId) {
		for (Video video:videoList.getAllVideos()) {
			if (video.getIdVideo()==videoId){
				video.playVideo();
			}
		}
	}

	//pausar reproducció vídeo
	@Override
	public void pauseVideo(int videoId) {
		for (Video video:videoList.getAllVideos()) {
			if (video.getIdVideo()==videoId){
				video.pauseVideo();
			}
		}
	}

	//parar reproducció vídeo
	@Override
	public void stopVideo(int videoId) {
		for (Video video:videoList.getAllVideos()) {
			if (video.getIdVideo()==videoId){
				video.stopVideo();
			}
		}
	}

	//obetenir el temps actual de reproducció
	@Override
	public LocalTime getVideoReproductionTime(int videoId) {
		for (Video video:videoList.getAllVideos()) {
			if (video.getIdVideo()==videoId){
				return video.getReproductionTime();
			}
		}
		throw new NullPointerException("El vídeo no existeix.");
	}

	//Obtenir l'estat actual de reproducció
	@Override
	public Status getVideoReproductionStatus(int videoId) {
		for (Video video:videoList.getAllVideos()) {
			if (video.getIdVideo()==videoId){
				return video.getReproductionStauts();
			}
		}
		throw new NullPointerException("El vídeo no existeix.");
	}
}
