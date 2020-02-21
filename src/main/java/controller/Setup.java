package controller;

import domain.Loghmeh;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Setup implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String restaurantsJson = external_services.ExternalServices.getFromExtenalAPI("http://138.197.181.131:8080/restaurants");
        Loghmeh loghmeh = Loghmeh.getInstance();
        if(restaurantsJson != null){
            System.out.println(loghmeh.addRestaurants(restaurantsJson));
        }
    }
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed");
    }

}
