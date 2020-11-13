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
 * Servlet implementation class adminSearchServlet
 */
@WebServlet("/adminSearchServlet")
public class adminSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminSearchServlet() {
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
	DBUtil dbu = new DBUtil();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		con = dbu.createConnection();
		Student s = new Student();
		ArrayList<Student> result = new ArrayList<Student>();
		try {
			sm = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (id ==""&&name=="") {
			try {
				rs = sm.executeQuery("select * from student");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (id !=""&&name=="") {
			try {
				rs = sm.executeQuery("select * from student where id=" + id + "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (id ==""&&name!="") {
			try {
				rs = sm.executeQuery("select * from student where name='" + name + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (id !=""&&name!="") {
			try {
				rs = sm.executeQuery("select * from student where id=" + id + " and name='" + name + "'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if(rs.next()) {
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setGender(rs.getString("gender"));
				s.setMajor(rs.getString("major"));
				result.add(s);
				request.setAttribute("result", result);
				request.getRequestDispatcher("adminSearchRes.jsp").forward(request, response);
			}else {
				request.setAttribute("message4", "<font color='red'>등록되지않는 학생입니다.</font>");
				request.getRequestDispatcher("adminSearch.jsp").forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
