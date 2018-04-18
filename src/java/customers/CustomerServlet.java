package customers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
    @Resource(lookup = "java:app/jdbc/nachoDB")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT CUSTOMER_ID,NAME,EMAIL FROM CUSTOMER");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer newCustomer = new Customer();
                newCustomer.setId(resultSet.getInt("CUSTOMER_ID"));
                newCustomer.setName(resultSet.getString("NAME"));
                newCustomer.setEmail(resultSet.getString("EMAIL"));

                customers.add(newCustomer);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/customers.jsp").forward(req, resp);

    }

}
