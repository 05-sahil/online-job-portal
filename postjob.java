@WebServlet("/postJob")
public class PostJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String location = request.getParameter("location");
        String salary = request.getParameter("salary");
        int employerId = Integer.parseInt(request.getParameter("employer_id"));

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortal", "root", "password");
            String query = "INSERT INTO Jobs (title, description, location, salary, employer_id) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, salary);
            ps.setInt(5, employerId);

            int result = ps.executeUpdate();
            if (result > 0) {
                response.sendRedirect("employerDashboard.jsp");
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
