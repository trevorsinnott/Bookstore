package net.codejava.javaee.bookstore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/" }, initParams = {
		@WebInitParam(name = "jdbcURL", value = "jdbc:mysql://localhost:3306/Bookstore"),
		@WebInitParam(name = "jdbcUsername", value = "root"),
		@WebInitParam(name = "jdbcPassword", value = "Telecaster_2019") })

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String jdbcURL = config.getInitParameter("jdbcURL");
		String jdbcUsername = config.getInitParameter("jdbcUsername");
		String jdbcPassword = config.getInitParameter("jdbcPassword");

		System.out.println("getInitParams URL - Username - Password: " + config.getInitParameter("jdbcURL") + " - "
				+ config.getInitParameter("jdbcUsername") + " - " + config.getInitParameter("jdbcPassword"));

		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("Hello" + action);

		try {
			switch (action) {
			case "/new": {
				showNewForm(request, response);
				break;
			}
			case "/edit": {
				showEditForm(request, response);
				break;
			}
			case "/update": {
				updateBook(request, response);
				break;
			}
			case "/insert": {
				insertBook(request, response);
				break;
			}
			case "/delete": {
				deleteBook(request, response);
				break;
			}
			default:
				listBooks(request, response);
				break;
			}
		} catch (SQLException ex) {
			// TODO: handle exception
			throw new ServletException(ex);
		}
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));

		Book book = new Book(id);
		bookDAO.deleteBook(book);
		response.sendRedirect("list");
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		float price = Float.parseFloat(request.getParameter("price"));

		Book book = new Book(title, author, price);

		bookDAO.insertBook(book);
		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		Book book = new Book(id, title, author, price);
		bookDAO.updateBook(book);
		response.sendRedirect("/list");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Book exisitingBook = null;
		try {
			exisitingBook = bookDAO.getBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		request.setAttribute("book", exisitingBook);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Book> listBook = bookDAO.listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
