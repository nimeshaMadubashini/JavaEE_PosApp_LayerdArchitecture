package lk.ijse.pos;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/AssCompany");
        bds.setPassword("1234");
        bds.setUsername("root");
        bds.setMaxTotal(10);
        bds.setInitialSize(10);
        servletContext.setAttribute("dbcp",bds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            ((BasicDataSource)servletContextEvent.getServletContext().getAttribute("dbcp")).close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
