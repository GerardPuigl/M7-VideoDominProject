package com.video.model.service;

import java.util.List;

import com.video.model.domain.Video;

public interface IVideoDAO {
	
	public void addVideo(Video video);
	public List<Video> getAllVideos();

}
