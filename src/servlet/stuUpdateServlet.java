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
 * Servlet implementation class stuUpdateServlett
 */
@WebServlet("/stuUpdateServlett")
public class stuUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public stuUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("methodName");
		if("stuUpdate".equals(methodName)) {
			stuUpdate(request,response);
		}else if("stuUpdateRes".equals(methodName)){
			stuUpdateRes(request,response);
		}
	}

	DBUtil dbu = new DBUtil();

	public void stuUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		ArrayList<Student> result = new ArrayList<Student>();
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
		System.out.println("ok");
		Student s = new Student();

		try {
			rs = sm.executeQuery("select * from student where id=" + id + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				s.setId(rs.getInt("id"));
				s.setPassword(rs.getString("password"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setGender(rs.getString("gender"));
				s.setMajor(rs.getString("major"));
				result.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("result", result);
		dbu.close(con, sm, rs);
		request.getRequestDispatcher("stuUpdate.jsp").forward(request, response);
	}

	public void stuUpdateRes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		Student s = new Student();
		ArrayList<Student> result = new ArrayList<Student>();
		con = dbu.createConnection();
		try {
			sm = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sm.execute("update student set id=" + id + ",password = '" + password + "',name='" + name + "',age=" + age
					+ ",gender='" + gender + "',major='" + major + "' where id=" + id + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = sm.executeQuery("select*from student where id=" + id + "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				s.setId(rs.getInt("id"));
				s.setPassword(rs.getString("password"));
				s.setName(rs.getString("name"));
				s.setAge(rs.getInt("age"));
				s.setGender(rs.getString("gender"));
				s.setMajor(rs.getString("major"));
				result.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		System.out.println(s.getName());

		request.getRequestDispatcher("stuUpdateRes.jsp").forward(request, response);
	}

}
