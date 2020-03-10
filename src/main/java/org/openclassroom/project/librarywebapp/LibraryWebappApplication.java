package org.openclassroom.project.librarywebapp;

import generated.libraryservice.LibraryService;
import generated.libraryservice.LibraryServiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class LibraryWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryWebappApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//			LibraryService libraryService = new LibraryServiceService().getLibraryServicePort();
//			System.out.println(libraryService.getBooksWithKeyword(""));
//		};
//	}

}
