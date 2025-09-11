package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.DocumentoMaestro;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String accion = request.getParameter("accion");
		HttpSession session = request.getSession();

		switch (accion) {
		case "Ingresar":

			String usuario = request.getParameter("nombres");
			String contraseña = request.getParameter("contrasena");
			
			String sql = "SELECT * FROM tbl_usuarios WHERE nombres= ? and contrasena= ?";

			try {
				Connection conn = Conexion.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);

				ps.setString(1, usuario);
				ps.setString(2, contraseña);

				ResultSet rs = ps.executeQuery();

				if (rs.next() == false) {

					session.setAttribute("mensaje", "El usuario o contraseña digitados son incorrectos");
					session.setAttribute("tipo", "error");
					response.sendRedirect("login.jsp");

				} else {
					response.sendRedirect("index.jsp");
				}

			} catch (SQLException e) {
				System.out.println("Error: " + e.getMessage());
			}
			break;

		default:
			session.setAttribute("mensaje", "No se reconocio la accion");
			session.setAttribute("tipo", "error");
            response.sendRedirect("login.jsp");
			break;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
