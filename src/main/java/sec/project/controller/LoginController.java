package sec.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @Autowired
    private HttpSession session;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(@RequestParam String username, @RequestParam String password) throws SQLException {

        if (username.isEmpty() || password.isEmpty()) {
            return "redirect:/login";
        }
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "sa", "");
        String query = "SELECT * FROM account WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while (resultSet.next()) {
            String dbusername = resultSet.getString("USERNAME");
            String dbpassword = resultSet.getString("PASSWORD");
            if (username.equals(dbusername) && password.equals(dbpassword)) {
                session.setAttribute("Login", "loggedIn");
                return "redirect:/memberpage";
            }
        }
        
        return "redirect:/login";
    }
    
    @RequestMapping(value = "/memberpage", method = RequestMethod.GET)
    public String memberpage() {
        return "memberpage";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "redirect:/login";
    }
}