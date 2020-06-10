package org.openclassroom.project.librarywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot App with embedded Tomcat allowing to launch the application from the IDE
 * */
@SpringBootApplication
public class LibraryWebappApplication {

	/**
	 * Main class that launches the Spring Application.
	 * */
	public static void main(String[] args) {
		SpringApplication.run(LibraryWebappApplication.class, args);
	}

}
