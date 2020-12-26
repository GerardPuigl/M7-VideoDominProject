package com.video.view;

import com.video.controller.*;
import com.video.model.User;

public class Main {

	public static void main(String[] args) {

		MenuNoLogin menuNoLogin = new MenuNoLogin();
		MenuLogin menuLogin = new MenuLogin();
		
		User userValidated=null;
		
		//Test
		Factory constructor = new Factory();
		
		constructor.createUser("test1", "test1", "test1");
		constructor.createVideo("www.servidor.com/urlvideo1", "Video1", 0);
		System.out.println("Usuari de prova nom: test1,cognom: test1, password: test1\n");

		//menú de crear usuari o iniciar sessió
		if (userValidated ==null) {
			userValidated=menuNoLogin.userLogin();
		}
		
		//menú d'úsuari validad
		if (userValidated!=null) {
			
		}
		
	}

}