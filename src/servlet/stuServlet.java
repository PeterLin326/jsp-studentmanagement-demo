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

import model.Student;
import util.DBUtil;

/**
 * Servlet implementation class addStudentServlet
 */
@WebServlet("/addStudentServlet")
public class stuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public stuServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		String method = request.getParameter("methodName");
		if ("addStudent".equals(method)) {
			try {
				addStudent(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("editStudent1".equals(method)) {
			try {
				editStudent1(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("editStudent".equals(method)) {
			try {
				editStudent(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("deleteStudent".equals(method)) {
			try {
				deleteStudent(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Student> searchStudent(String id, String name) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		con = dbu.createConnection();
		sm = con.createStatement();
		ArrayList<Student> result = new ArrayList<Student>();
		if (id == "" && name == "") {
			rs = sm.executeQuery("select * from student");
		}
		if (id != "" && name == "") {
			rs = sm.executeQuery("select * from student where id=" + id + "");
		}
		if (id == "" && name != "") {
			rs = sm.executeQuery("select * from student where name='" + name + "'");
		}
		if (id != "" && name != "") {
			rs = sm.executeQuery("select * from student where id=" + id + " and name='" + name + "'");
		}
		while (rs.next()) {
			Student st = new Student();
			st.setId(rs.getInt("id"));
			st.setPassword("password");
			st.setName(rs.getString("name"));
			st.setAge(rs.getInt("age"));
			st.setGender(rs.getString("gender"));
			st.setMajor(rs.getString("major"));
			result.add(st);
		}
		if (rs != null) {
			rs.close();
		}
		dbu.close(con, sm, rs);
		return result;
	} 
	
	public void search(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
    	String id=request.getParameter("id");
    	String name=request.getParameter("name");  
     if(searchStudent(id,name).isEmpty()){
        	request.getRequestDispatcher("searchFail.jsp").forward(request, response);
        }
       else{
    		request.setAttribute("result", searchStudent(id,name));
            request.getRequestDispatcher("searchStudent.jsp").forward(request, response);	
        }
    }

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		con = dbu.createConnection();
		sm = con.createStatement();
		String id2 = request.getParameter("id");
		sm.execute("delete from student where id=" + id2 + "");
		dbu.close(con, sm, rs);
		request.getRequestDispatcher("deleteStudent.jsp").forward(request, response);
	}

	private void editStudent1(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		request.setAttribute("result", searchStudent(id, ""));
		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	public void editStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		con = dbu.createConnection();
		sm = con.createStatement();
		sm.execute("update student set id=" + id + ",name='" + name + "',age=" + age + ",gender='" + gender
				+ "',major='" + major + "' where id=" + id + "");
		dbu.close(con, sm, rs);
		request.setAttribute("result", searchStudent(id, ""));
		request.getRequestDispatcher("updateStudent.jsp").forward(request, response);
	}

	private DBUtil dbu = new DBUtil();

	public boolean verify(String id, String name) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		con = dbu.createConnection();
		sm = con.createStatement();
		rs = sm.executeQuery("select * from student where id=" + id + " and name='" + name + "'");
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public void addStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sm = null;
		ResultSet rs = null;
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		con = dbu.createConnection();
		sm = con.createStatement();
		if (verify(id, name)) {
			sm.execute("insert into student(id,name,age,gender,major) values(" + id + ",'" + name + "'," + age + ",'"
					+ gender + "','" + major + "')");
			dbu.close(con, sm, rs);
		} else {
			response.getWriter().write("이미 �?재한 학생입니�?.");
			dbu.close(con, sm, rs);
		}
	}

}
