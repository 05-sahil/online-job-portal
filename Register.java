@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("user_type");

        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortal", "root", "password");
            String query = "INSERT INTO Users (name, email, password, user_type) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, userType);

            int result = ps.executeUpdate();
            if (result > 0) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
