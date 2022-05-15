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
    private LoginDao loginDao;
    private SubjectDAO sudao;
    private ClassReportDAO crdao;
    public void init() {
        loginDao = new LoginDao();
		cdao = new ClassDAO();
		sdao = new StudentDAO();
		tdao = new TeacherDAO();
		sudao= new SubjectDAO();
		crdao= new ClassReportDAO();
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
			case "/login":
				loginForm(request, response);
				break;	
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
			case "/subjectsandteachers":
				subjectsAndTeachers(request,response);
				break;
			case "/classreport":
				classReport(request,response);
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
			case "/newSubject":
				showNewSubjectForm(request, response);
				break;
			case "/insertSubject":
				insertSubject(request, response);
				break;
			case "/deleteSubject":
				deleteSubject(request, response);
				break;
			case "/editSubject":
				showEditSubjectForm(request, response);
				break;
			case "/updateSubject":
				updateSubject(request, response);
				break;
			case "/listSubject":
				listSubject(request, response);
				break;
				case "/submitsubandteacher":
					submitsubandteacher(request,response);
					break;
			case "/logout":
				logout(request,response);
			default:
				listClass(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
}

	private void classReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {		
		System.out.println("servlet method for classreport");
		HttpSession session = request.getSession();
		List<StudentPOJO> c=crdao.classReportStudent(Integer.parseInt(request.getParameter("id")));
		List<classReportPOJO> c1=crdao.classReportTeacherSubject(Integer.parseInt(request.getParameter("id")));
		session.setAttribute("c", c);
		session.setAttribute("c1", c1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-report.jsp");
		dispatcher.forward(request, response);
	}
	private void submitsubandteacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		int classId = (int)session.getAttribute("classId");
		int subId = Integer.parseInt(request.getParameter("subject"));
		int teaId = Integer.parseInt(request.getParameter("teacher"));
		crdao.subjectsAndTeachers(classId, subId, teaId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-list.jsp");
		dispatcher.forward(request, response);
		
	}
	private void subjectsAndTeachers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("subjectsandteachersform.jsp");
		int classId = Integer.parseInt(request.getParameter("id"));
		System.out.println("classId--->"+classId);
		HttpSession session = request.getSession();
		session.setAttribute("classId",classId);
		dispatcher.forward(request, response);
	}

	private void listSubject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<SubjectPOJO> listSubject = sudao.selectAllSubjects();
		session.setAttribute("listSubject", listSubject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-list.jsp");
		dispatcher.forward(request, response);
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		SubjectPOJO c = new SubjectPOJO(id,name);
		System.out.println("INSIDE SERVLET, subject name to update is "+name);
		sudao.updateSubject(c);
		response.sendRedirect("listSubject");
	}

	private void showEditSubjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		SubjectPOJO existingSubject = sudao.selectSubject(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		session.setAttribute("es", existingSubject);
		dispatcher.forward(request, response);
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		sudao.deleteSubject(id);
		response.sendRedirect("listSubject");
	}

	private void insertSubject(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		SubjectPOJO pojo = new SubjectPOJO (name);
		sudao.insertSubject(pojo);
		response.sendRedirect("listSubject");
	}

	private void showNewSubjectForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		dispatcher.forward(request, response);
	}

	protected void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			        String username = request.getParameter("username");
			        String password = request.getParameter("password");
			        LoginPOJO loginBean = new LoginPOJO();
			        loginBean.setUsername(username);
			        loginBean.setPassword(password);
			        try {
			            if(loginDao.validate(loginBean)){
			                HttpSession session = request.getSession();
			                session.setAttribute("username",username);
			                response.sendRedirect("class-list.jsp");
			            } else {
			                HttpSession session = request.getSession();
			                session.setAttribute("user", username);
			                response.sendRedirect("login.jsp");
			            }
			        } catch (ClassNotFoundException e) {
			            e.printStackTrace();
			        }
	}

	private void showNewTeacherForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		dispatcher.forward(request, response);
	}

	private void listTeacher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<TeacherPOJO> listTeacher = tdao.selectAllTeachers();
		session.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-list.jsp");
		dispatcher.forward(request, response);
	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String designation=request.getParameter("designation");
		TeacherPOJO c = new TeacherPOJO(id,name,designation);
		System.out.println("INSIDE SERVLET, name to update is "+name);
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
