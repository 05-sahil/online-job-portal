@WebServlet("/searchJobs")
public class JobSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Job> jobList = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JobPortal", "root", "password");
            String query = "SELECT * FROM Jobs WHERE title LIKE ? OR description LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job(rs.getInt("id"), rs.getString("title"), rs.getString("description"), 
                                  rs.getString("location"), rs.getDouble("salary"));
                jobList.add(job);
            }

            request.setAttribute("jobs", jobList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jobList.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
