package com.video.controller;

import java.time.LocalTime;
import java.util.List;

import com.video.model.Video;
import com.video.model.Video.*;


public interface IVideoDao {
	public void addVideo(String url, String title, LocalTime duration, int userId);
	public List<Video> getVideoList(int userID);
	public Enum<UploadStatus> getVideoUploadStatus(int videoId);
	
	public void playVideo(int videoId);
	public void pauseVideo(int videoId);
	public void stopVideo(int videoId);
	public LocalTime getVideoReproductionTime(int videoId);
	public Enum<Status> getVideoReproductionStatus(int videoId);
	
}
