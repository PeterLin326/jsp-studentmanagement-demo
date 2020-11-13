package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
import model.Student;

/**
 * Servlet implementation class stuEditResServlet
 */
@WebServlet("/stuEditResServlet")
public class stuUpdateResServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stuUpdateResServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	private DBUtil dbu = new DBUtil();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		ArrayList<Student> result = new ArrayList<Student>();
		Student s = new Student();
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		con = dbu.createConnection();
		try {
			sm = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = sm.executeQuery("select * from student where id="+id+"");
			if(rs.next()) {
				s.setId(rs.getInt("id"));
				s.setPassword(rs.getString("password"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setGender(rs.getString("gender"));
				s.setMajor(rs.getString("major"));
				result.add(s);
				request.setAttribute("result", result);
				request.getRequestDispatcher("stuUpdate.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbu.close(con, sm, rs);
		}
		
		
	}

}
