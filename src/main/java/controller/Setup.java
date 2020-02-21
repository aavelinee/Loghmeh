package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Setup implements ServletContextListener {

    private ServletContext context;
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // get servlet context
        context = servletContextEvent.getServletContext();
        // set attribute in context
        String attributeValue = "Context Param Value";
        String attributeName ="ContextParam";
        context.setAttribute(attributeName, attributeValue);
    }
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed");
    }

}
