import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/OrderDetailsServlet")
public class OrderDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain;charset=UTF-8");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

        boolean success = orderDetailsDAO.saveOrderDetails(username, email, address, totalPrice);

        PrintWriter out = response.getWriter();
        if (success) {
            out.print("success");
        } else {
            out.print("failure");
        }
    }
}
