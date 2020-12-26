package com.video.controller;

import java.util.List;

import com.video.model.Video;

public interface IVideoDao {
	public void addVideo(String url, String title, int userId);
	public List<Video> getVideoList(int userID);
}
