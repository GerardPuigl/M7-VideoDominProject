package com.video.controller;

import java.time.LocalTime;
import java.util.List;

import com.video.model.Video;
import com.video.model.Video.*;

public interface IVideoDao {
	public void addVideo(String url, String title, LocalTime duration, int userId);
	public List<Video> getAllVideos();
	public List<Video> getVideoList(int userID);
	public Enum<UploadStatus> getVideoUploadStatus(int videoId);
	public Video getVideoWithId(int videoId);

	/* Dubte de patro de disseny a comentar amb el Mentor;
	 * 
	 * Cal que els següents mètodes estiguin en l'interfice DAO ho hauria de
	 * comunicar el MenuVideoReproduction diretcament amb la classe video??
	 * 
	 * Segons entenc jo, el Patro Dao és per comunicar el BackEnd amb la base de
	 * dades, correcte?? Per tant la meva idea és que el programa descarregui
	 * una instancia de l'objecte vídeo i l'operi per reproducir, pausar i parar.
	 * 
	 */
	
	public void playVideo(Video video);
	public void pauseVideo(Video video);
	public void stopVideo(Video video);
	public LocalTime getVideoReproductionTime(Video video);
	public Enum<Status> getVideoReproductionStatus(Video video);


}
