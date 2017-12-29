package sec.project;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.h2.tools.RunScript;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyberSecurityBaseProjectApplication {
    
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CyberSecurityBaseProjectApplication.class);

        // Open the database connection
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");

        try {
            // Create content to the database
            RunScript.execute(connection, new FileReader("sql/createAccountTable.sql"));
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }
}
