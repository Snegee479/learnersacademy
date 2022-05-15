package servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojo.*;
import dao.*;

@WebServlet("/")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassDAO cdao;
	private StudentDAO sdao;
	private TeacherDAO tdao;
	public void init() {
		cdao = new ClassDAO();
		sdao = new StudentDAO();
		tdao = new TeacherDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertClass(request, response);
				break;
			case "/delete":
				deleteClass(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateClass(request, response);
				break;
			case "/newStudent":
				showNewStudentForm(request, response);
				break;
			case "/insertStudent":
				insertStudent(request, response);
				break;
			case "/deleteStudent":
				deleteStudent(request, response);
				break;
			case "/editStudent":
				showEditStudentForm(request, response);
				break;
			case "/updateStudent":
				updateStudent(request, response);
				break;
			case "/listStudent":
				listStudent(request, response);
				break;
			case "/newTeacher":
				showNewTeacherForm(request, response);
				break;
			case "/insertTeacher":
				insertTeacher(request, response);
				break;
			case "/deleteTeacher":
				deleteTeacher(request, response);
				break;
			case "/editTeacher":
				showEditTeacherForm(request, response);
				break;
			case "/updateTeacher":
				updateTeacher(request, response);
				break;
			case "/listTeacher":
				listTeacher(request, response);
				break;
			default:
				listClass(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TeacherPOJO> listTeacher = tdao.selectAllTeachers();
		request.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-list.jsp");
		dispatcher.forward(request, response);
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String designation=request.getParameter("designation");
		TeacherPOJO c = new TeacherPOJO(id,name,designation);
		tdao.updateTeacher(c);
		response.sendRedirect("listTeacher");
	}

	private void showEditTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		TeacherPOJO existingTeacher = tdao.selectTeacher(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		session.setAttribute("et", existingTeacher);
		dispatcher.forward(request, response);
	}

	private void insertTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String designation=request.getParameter("designation");
		TeacherPOJO pojo = new TeacherPOJO (name,designation);
		tdao.insertTeacher(pojo);
		response.sendRedirect("listTeacher");
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		tdao.deleteTeacher(id);
		response.sendRedirect("listTeacher");
	}

	private void listClass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ClassObj> listClass = cdao.selectAllClasses();
		request.setAttribute("listClass", listClass);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		ClassObj existingClass = cdao.selectClass(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		session.setAttribute("ec", existingClass);
		dispatcher.forward(request, response);

	}

	private void insertClass(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = "";
		//System.out.print("---> "+name);
		try {
			name = request.getParameter("name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("---> "+name);
		 
		ClassObj pojo = new ClassObj (name);
		cdao.insertClass(pojo);
		response.sendRedirect("list");
	}

	private void updateClass(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		ClassObj c = new ClassObj(id,name);
		cdao.updateClass(c);
		response.sendRedirect("list");
	}
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<StudentPOJO> listStudent = sdao.selectAllStudents();
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteClass(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		cdao.deleteClass(id);
		response.sendRedirect("list");
	}
	
	private void showNewStudentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditStudentForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		StudentPOJO existingStudent = sdao.selectStudent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		session.setAttribute("es", existingStudent);
		dispatcher.forward(request, response);

	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		StudentPOJO pojo = new StudentPOJO (name);
		sdao.insertStudent(pojo);
		response.sendRedirect("listStudent");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		StudentPOJO c = new StudentPOJO (id,name);
		sdao.updateStudent(c);
		response.sendRedirect("listStudent");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		sdao.deleteStudent(id);
		response.sendRedirect("listStudent");

	}


}
