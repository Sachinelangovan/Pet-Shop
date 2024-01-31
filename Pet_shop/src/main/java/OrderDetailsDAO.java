import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/Contact";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";


    public boolean saveOrderDetails(String username, String email, String address, double totalPrice) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USERNAME,PASSWORD)) {
                String insertQuery = "INSERT INTO orders (username, email, address, total_price) VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, address);
                    preparedStatement.setDouble(4, totalPrice);

                    int rowsAffected = preparedStatement.executeUpdate();
                    return rowsAffected > 0;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
