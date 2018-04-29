package control;

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

import model.DBManager;
import model.Produkt;

/**
 * Servlet implementation class AnzeigeServlet
 */
@WebServlet("/AnzeigeServlet")
public class AnzeigeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnzeigeServlet() {
        super();
    }

	/**
	 * Aufgabe 2.4
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Neuen DB Manager erzeugen
		DBManager manager = new DBManager();
		
		// Produkte aus der DB holen
		List<Produkt> p = null;
		try {
			p = manager.holeProdukte();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Produkte in der Session speichern
		HttpSession session = request.getSession();
		session.setAttribute("produkte", p);
		
		// Zur anzeige.jsp Seite umleiten
		RequestDispatcher rd = request.getRequestDispatcher("anzeige.jsp");
		rd.forward(request, response);
		// Alternative:
		//response.sendRedirect("anzeige.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}