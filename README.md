# Library Web Application
P7 - DA JAVA Openclassroom - A community site around climbing



## Getting Started


You can get this project either by downloading it in ZIP file or cloning it.


This project works in duo with a web service that you will find [here](https://github.com/D4RT4GNaN/library-web-service.git).


To clone it, go to the folder of your choice in command line and use the following command :

```

$ git clone https://github.com/D4RT4GNaN/library-web-service.git

```
See deployment for notes on how to deploy the project on a live system.



### Prerequisites


You need to install :

* An IDE like [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/) or [Eclipse JEE](https://www.eclipse.org/downloads/packages/release/2019-06/r/eclipse-ide-enterprise-java-developers)

* The associated Web Service you can find [here](https://github.com/D4RT4GNaN/library-web-service.git).

First follow its deployment instructions before deploying the web application.



### Installing

To start with this project :

1. Open it in IDE.

2. Delete the *generated.libraryservice* folder.

3. In terminal, go to the project folder and enter the following command :
```

$ wsimport -Xnocompile -d ./src/main/java -p generated.libraryservice [your_address] 

```
where **[your_address]** is the address of the WSDL on your web service (ex: http://localhost:8080/libraryservice-webservice/libraryservice?wsdl)



## Deployment


To deploy the project on Tomcat server, you can just running the project into the IDE.

The site is normally accessible at [http://localhost:9090/](http://localhost:9090/).


You can also manually deploy the project.


Start by cleaning package (optional but advisor)

```

$ mvn clean

```

Continu by packaging the project

```

$ mvn package

```

Put the war which has just been created, into **apache-tomcat-X.X.XX/webapps**.

And finally, launch the tomcat server with the command

```

$ sh startup.sh

```

*You can found the script into the bin folder of the tomcat server*


Now, it's normally accessible at [http://localhost:8080/virtualclimbing-webapp/](http://localhost:8080/virtualclimbing-webapp/).



## Built With


* [Maven](https://maven.apache.org/) - Dependency Management and Multi-module Management

* [Spring](https://spring.io/projects/spring-framework) - Used to manage the backend service like DAO and Manager

* [Thymeleaf](https://www.thymeleaf.org/) - A modern server-side Java template engine for both web and standalone environments

* [JQuery](https://jquery.com/) - The famous Javascript library

* [Bootstrap](https://getbootstrap.com/) -  a free and open-source CSS framework directed at responsive, mobile-first front-end web development

* [JQuery.Flipster](https://github.com/drien/jquery-flipster) - Responsive, CSS3, touch-enabled jQuery Coverflow plugin

* [DataTables](https://datatables.net/) - An advanced interaction controls for HTML tables



## Versioning


I use [Git](https://git-scm.com/) for versioning.



## Authors


* **Maxime Blaise** - [D4RT4GNaN](https://github.com/D4RT4GNaN)


