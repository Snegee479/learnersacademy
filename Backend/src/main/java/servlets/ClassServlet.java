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
import pojo.*;
import dao.*;

@WebServlet("/")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassDAO cdao;
	
	public void init() {
		cdao = new ClassDAO();
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
			default:
				listClass(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
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
		int id = Integer.parseInt(request.getParameter("id"));
		ClassObj existingClass = cdao.selectClass(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		request.setAttribute("existingClass", existingClass);
		dispatcher.forward(request, response);

	}

	private void insertClass(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
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

	private void deleteClass(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		cdao.deleteClass(id);
		response.sendRedirect("list");

	}

}
