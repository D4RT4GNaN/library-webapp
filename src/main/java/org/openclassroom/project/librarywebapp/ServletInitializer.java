package org.openclassroom.project.librarywebapp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Class transforming the application into a servlet that can be used from the embedded Tomcat server.
 * */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 * Transforms the application into a servlet thanks to the {@link SpringApplicationBuilder} class that is configured by giving it the application as source.
	 *
	 * @param application - The application to be transformed into a servlet.
	 *
	 * @return Servlet application.
	 * */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibraryWebappApplication.class);
	}

}
