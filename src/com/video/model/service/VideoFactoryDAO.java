package com.video.model.service;

public class VideoFactoryDAO {

	public enum Tipus {
		MEMORIA, XML, JSON, CSV, MYSQL, MARIADB, ORACLE, SQL_SERVER, MONGODB
	}

	public IVideoDAO getDefaultPersistence()
	{
		return this.getDAO(Tipus.MEMORIA);
	}
	
	
	public IVideoDAO getDAO(Tipus tipus) {
		IVideoDAO dao = null;

		if (tipus == Tipus.MEMORIA) {
			dao = VideoRepositoryDAO.getInstance();
		} else {
			throw new RuntimeException("ERROR: Aquest tipus de DAO no est� implementat.");
		}

		return dao;
	}

}
