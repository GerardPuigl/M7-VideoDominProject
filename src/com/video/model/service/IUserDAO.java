package com.video.model.service;

import java.util.List;

import com.video.model.domain.User;

public interface IUserDAO {

	public void addUser(User user) throws Exception;
	public List<User> getUsersList();
}
