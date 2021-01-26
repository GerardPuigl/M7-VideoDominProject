package com.video.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.KeyAlreadyExistsException;

import com.video.model.domain.Video;
import com.video.model.domain.Video.UploadStatus;
import com.video.model.service.IVideoDAO;
import com.video.model.service.VideoFactoryDAO;

public class VideoController {

	//Patró Singleton
	
	private static VideoController instancia;
	
	private VideoController() {
	}
	
	public static VideoController getInstance() {
		if (instancia==null) {
			instancia= new VideoController();
		}
		return instancia;
	}
	
	// patró Dao

	private IVideoDAO iVideoDao = getDAO();

	private VideoFactoryDAO videoFactoryDAO = null;

	private IVideoDAO getDAO() {
		if (videoFactoryDAO == null) {
			videoFactoryDAO = new VideoFactoryDAO();
		}

		return videoFactoryDAO.getDefaultPersistence();

	}
	
	// afegir nou vídeo

	public void addVideo(String url, String title, LocalTime duration, int userId) {

		for (Video video : iVideoDao.getAllVideos()) {
			if (video.geturl().equals(url)) {
				throw new KeyAlreadyExistsException("El vídeo introduit ja existeix.");
			}
		}

		try {
			Video video = new Video(url, title, userId, duration);
			iVideoDao.addVideo(video);
		} catch (Exception e) {
			System.out.println("No s'ha pogut afegir el vídeo." + e.getMessage());
		}
	}

	// llista de vídeos de l'usuari
	
	public List<Video> getVideoList(int userId) {
		List<Video> userVideoList = new ArrayList<>();
		for (Video video : iVideoDao.getAllVideos()) {
			if (video.getUserId() == userId) {
				userVideoList.add(video);
			}
		}

		if (userVideoList.size() == 0) {
			throw new NullPointerException("Encara no teniu cap vídeo.");
		}
		return userVideoList;
	}

	// veure estat de pujada del vídeo

	public Enum<UploadStatus> getVideoUploadStatus(int videoId) {
		for (Video video : iVideoDao.getAllVideos()) {
			if (video.getIdVideo() == videoId) {
				return video.getVideoState();
			}
		}
		throw new NullPointerException("No existeix cap vídeo amb aquesta url");
	}

	// buscar i retornar un objecte Video

	public Video getVideoWithId(int videoId) {
		for (Video video : iVideoDao.getAllVideos()) {
			if (video.getIdVideo() == videoId) {
				return video;
			}
		}
		throw new NullPointerException("No existeix cap vídeo amb aquest id");
	}

	// Obtenir una llista de tots els vídeos diponibles

	public List<Video> getAllVideos() {
		return iVideoDao.getAllVideos();
	}

}
