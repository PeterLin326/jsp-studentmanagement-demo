package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBUtil;
/**
 * Servlet implementation class stuRegisterServlet
 */
@WebServlet("/stuRegisterServlet")
public class stuRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stuRegistServlet() {
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
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String major = request.getParameter("major");
		
		con = dbu.createConnection();
		try {
			sm = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = sm.executeQuery("select * from student where id="+id+"");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(rs.next()) {
				request.setAttribute("message", "<font color='red'>학생 이미 준재합니다.로그인하십시오.</font>");
				request.getRequestDispatcher("stuRegist.jsp").forward(request, response);
				dbu.close(con, sm, rs);
			} else {
				sm.execute("insert into student(id,password,name,age,gender,major) values(" + id + ",'" + password + "','" + name + "'," + age + ",'"
						+ gender + "','" + major + "')");
				request.setAttribute("message1", "<font color='green'>학생등록되었습니다.로그인하십시오.</font>");
				request.getRequestDispatcher("stuLogin.jsp").forward(request, response);
				dbu.close(con, sm,rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbu.close(con, sm, rs);
		}
		
	}

}
