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
 * Servlet implementation class stuLoginServlet
 */
@WebServlet("/stuLoginServlet")
public class stuLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stuLoginServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		Student s = new Student();
		ArrayList<Student> result = new ArrayList<Student>();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		con = dbu.createConnection();
		try {
			sm = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs=sm.executeQuery("select * from student where id="+id+" and password='"+password+"'");

			if(rs.next()) {
				s.setId(rs.getInt("id"));
				s.setPassword(rs.getString("password"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setGender(rs.getString("gender"));
				s.setMajor(rs.getString("major"));
				result.add(s);
				request.setAttribute("result", result);
				request.getRequestDispatcher("stuIndex.jsp").forward(request, response);
			}else {
				request.setAttribute("message2", "<font color='red'>등록되지않는 학생입니다.동록하십시오</font>");
				request.getRequestDispatcher("stuLogin.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	}
	


}
