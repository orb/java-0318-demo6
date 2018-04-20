package tacos;

import javax.annotation.sql.DataSourceDefinition;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@DataSourceDefinition(
        name = "java:app/jdbc/nachoDB",
        className = "org.apache.derby.jdbc.ClientDataSource",
        url = "jdbc:derby://localhost:1527/",
        databaseName = "nachos",
        user = "app",
        password = "app")
@WebListener
public class DBSetupListener implements ServletContextListener {    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("+++++++++++++ TACOS");
        
        // In the non-CDI version we were managing things like this
        //sce.getServletContext().setAttribute("taqueriaManager", new TaqueriaManager());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("------------- TACOS");
    }
}
