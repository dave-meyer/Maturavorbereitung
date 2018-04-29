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

import model.DBManager;
import model.Produkt;
import model.Shop;

/**
 * Servlet implementation class AendernServlet
 */
@WebServlet("/AendernServlet")
public class AendernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AendernServlet() {
    }

	/**
	 * Aufgabe 2.3
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Neuen DB Manager erzeugen
		DBManager manager = new DBManager();
		// ID Des Produktes wird als Parameter mitgegeben
		int id = Integer.parseInt(request.getParameter("id")); // Annahme dass der name des parameters id ist
		// Produkt und Shops aus DB holen
		Produkt p = null;
		List<Shop> shops = null;
		try {
			p = manager.holeProdukt(id);
			shops = manager.holeShops(); // laut 2.2 bereits vorhanden
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Produkt in der Session speichern
		request.getSession().setAttribute("produkt", p);
		// Shops in der Session speichern
		request.getSession().setAttribute("shops", shops);
		
		// Zur aendern.jsp Seite umleiten
		RequestDispatcher rd = request.getRequestDispatcher("aendern.jsp");
		rd.forward(request, response);
		// Alternative:
		//response.sendRedirect("aendern.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
