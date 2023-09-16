package lk.ijse.pos.listner;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");
        ServletContext servletContext = servletContextEvent.getServletContext();
        BasicDataSource pool = ConnectionPoolManager.getInstance().getDataSource();
        servletContext.setAttribute("dbcp", pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
        // Perform cleanup or resource release if needed
    }
}
