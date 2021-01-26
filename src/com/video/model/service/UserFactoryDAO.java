package com.video.model.service;

public class UserFactoryDAO {

	public enum Tipus {
		MEMORIA, XML, JSON, CSV, MYSQL, MARIADB, ORACLE, SQL_SERVER, MONGODB
	}

	public IUserDAO getDefaultPersistence()
	{
		return this.getDAO(Tipus.MEMORIA);
	}
	
	
	public IUserDAO getDAO(Tipus tipus) {
		IUserDAO dao = null;

		if (tipus == Tipus.MEMORIA) {
			dao = UserRepositoryDAO.getInstance();
		} else {
			throw new RuntimeException("ERROR: Aquest tipus de DAO no est� implementat.");
		}

		return dao;
	}

}
