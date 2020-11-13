package dbservlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import model.Student;
import util.Page;

public class AllServlet extends HttpServlet {
	/**
	 * this is an example of CRUD
	 * 
	 * @author Administrator @2017/08/31
	 */
	private static final long serialVersionUID = 1L;

	// doPost方法
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String methodName = request.getParameter("methodName");
		int method = Integer.parseInt(methodName);
		try {
			switch (method) {
			case 0:
				insert(request, response);
				break;
			case 1:
				difpage(request, response);
				break;
			case 2:
				delete(request, response);
				break;
			case 3:
				update(request, response);
				break;
			case 4:
				update1(request, response);
				break;
			case 5:
				dispatch(request, response);
				break;
			case 6:
				dispatch1(request, response);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// doGet方法
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// 数据库连接方�?
	public Connection connect() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/student?"
				+ "user=root&password=960326&useUnicode=true&characterEncoding=UTF8&useSSL=true";
		conn = DriverManager.getConnection(url);
		return conn;
	}

	// 关闭数据库资�?
	public void close(Statement stat, Connection conn) throws SQLException {
		if (stat != null) {
			stat.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public boolean verify(String id) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement sm = null;
		ResultSet rs = null;
		conn = connect();
		sm = conn.createStatement();
		rs = sm.executeQuery("select * from student where id=" + id + "");
		if (rs.next()) {
			close(sm, conn);
			return true;
		} else {
			close(sm, conn);
			return false;
		}
	}

	// 插入方法
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		conn = connect();
		stat = conn.createStatement();
		rs = stat.executeQuery("select * from student where id=" + id + "");
		PrintWriter out = response.getWriter();
		if (rs.next()) {
			out.print("이미 �?재한 학생입니�?.");
			close(stat, conn);
		} else {
			stat.execute("insert into student(id,name,age,gender,major) values(" + id + ",'" + password + "','" + name
					+ "'," + age + ",'" + gender + "','" + major + "')");
			close(stat, conn);
		}
	}

	// 查询方法
	public ArrayList<Student> select(String id, String name) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		conn = connect();
		stat = conn.createStatement();
		ArrayList<Student> result = new ArrayList<Student>();
		if (id == "" && name == "") {
			rs = stat.executeQuery("select * from student");
		}
		if (id != "" && name == "") {
			rs = stat.executeQuery("select * from student where id=" + id + "");
		}
		if (id == "" && name != "") {
			rs = stat.executeQuery("select * from student where name='" + name + "'");
		}
		if (id != "" && name != "") {
			rs = stat.executeQuery("select * from student where id=" + id + " and name='" + name + "'");
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
		close(stat, conn);
		return result;
	}

	// 条件查询跳转
	public void dispatch(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		String id5 = request.getParameter("id");
		String name5 = request.getParameter("name");
		if (select(id5, name5).isEmpty()) {
			request.getRequestDispatcher("adminSearchF.jsp").forward(request, response);
		} else {
			request.setAttribute("result", select(id5, name5));
			request.getRequestDispatcher("adminSearchRes.jsp").forward(request, response);
		}
	}

	public void dispatch1(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		String id5 = request.getParameter("id");
		String name5 = request.getParameter("name");
		if (select(id5, name5).isEmpty()) {
			request.getRequestDispatcher("adminSearchF.jsp").forward(request, response);
		} else {
			request.setAttribute("result", select(id5, name5));
			request.getRequestDispatcher("stuSearch.jsp").forward(request, response);
		}
	}

	// 设置分页相关参数方法
	public Page setpage(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException {
		String crd = request.getParameter("currentRecord");
		// String id=request.getParameter("id");
		// String name=request.getParameter("name");
		ArrayList<Student> result = select("", "");
		Page pager = new Page();
		pager.setTotalRecord(result.size());
		pager.setTotalPage(result.size(), pager.getPageSize());
		if (crd != null) {
			int currentRecord = Integer.parseInt(crd);
			pager.setCurrentRecord(currentRecord);
			pager.setCurrentPage(currentRecord, pager.getPageSize());
		}
		return pager;
	}

	// 获得分页显示的子�?
	public void difpage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		// String id=request.getParameter("id");
		// String name=request.getParameter("name");
		ArrayList<Student> result = select("", "");
		Page pager = new Page();
		pager = setpage(request, response);
		List<Student> subResult = null;
		int currentRecord = pager.getCurrentRecord();
		if (currentRecord == 0) {
			if (pager.getTotalRecord() < 8) {
				subResult = (List<Student>) result.subList(0, pager.getTotalRecord());
			} else {
				subResult = (List<Student>) result.subList(0, pager.getPageSize());
			}
		} else if (pager.getCurrentRecord() + pager.getPageSize() < result.size()) {
			subResult = (List<Student>) result.subList(pager.getCurrentRecord(),
					pager.getCurrentRecord() + pager.getPageSize());
		} else {
			subResult = (List<Student>) result.subList(pager.getCurrentRecord(), result.size());
		}
		request.setAttribute("pager", pager);
		request.setAttribute("subResult", subResult);
		request.getRequestDispatcher("stuList.jsp").forward(request, response);
	}

	// 信息删除方法
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		Connection conn = null;
		Statement stat = null;
		conn = connect();
		stat = conn.createStatement();
		String id2 = request.getParameter("id");
		stat.execute("delete from student where id=" + id2 + "");
		request.getRequestDispatcher("adminDelRes.jsp").forward(request, response);
	}

	// 信息修改方法
	public void update1(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		String id4 = request.getParameter("id");
		request.setAttribute("result", select(id4, ""));
		request.getRequestDispatcher("stuUpdate.jsp").forward(request, response);
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		Connection conn = null;
		Statement stat = null;
		String id3 = request.getParameter("id");
		String password3 = request.getParameter("password");
		String name3 = request.getParameter("name");
		String age3 = request.getParameter("age");
		String gender3 = request.getParameter("gender");
		String major3 = request.getParameter("major");
		conn = connect();
		stat = conn.createStatement();
		stat.execute("update student set id=" + id3 + ",password = '" + password3 + "',name='" + name3 + "',age=" + age3
				+ ",gender='" + gender3 + "',major='" + major3 + "' where id=" + id3 + "");
		request.setAttribute("result", select(id3, ""));
		request.getRequestDispatcher("stuUpdateRes.jsp").forward(request, response);
	}

}