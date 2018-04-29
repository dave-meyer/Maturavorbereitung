package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBManager;
import model.Produkt;

/**
 * Servlet implementation class ShopsServlet
 */
@WebServlet("/ShopsServlet")
public class ShopsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopsServlet() {
        super();
    }

	/**
	 * Aufgabe 2.6
	 * Wie würde das Servlet aussehen, dass die JSON-Texte von Aufgabe 2.5 erstellt?
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Neuen DB Manager erzeugen
		DBManager manager = new DBManager();
		// Produkt ID wurde als Parameter mitgegeben. Parameter sind Strings, deshalb in Integer parsen
		int pId = Integer.parseInt(request.getParameter("pId"));
		// Produkte in Shops aus der DB holen
		Produkt p = null;
		String json = null;
		try {
			// Produkt aus DB holen
			p = manager.holeProdukt(pId);
			// Mit dem Produkt-Namen die Shops holen, in denen das Produkt angeboten wird
			// String Array enthaelt den Shop-Namen und den Preis des Produktes im Shop
			// Mehrere String Arrays werden in einer ArrayList gespeichern
			ArrayList<String[]> produktePerShop = manager.holeProduktePerShop(p.getName());
			// JSON Array beginnen (entspricht der obigen ArrayList)
			json = "[";
			// Alle Preise-pro-Shop ausgeben
			for(int i = 0; i < produktePerShop.size(); i++) {
				// JSON Objekte mit Beistrich trennen
				if(i > 0) json += ",";
				// Aktuelle Preis-per-Shop Kombination holen
				String[] temp = produktePerShop.get(i);
				// Neues JSON Objekt
				json += "{";
				// Shop Name (als Variable im JSON Objekt)
				json += "\"shop\": \""+temp[0]+"\",";
				// Preis des Produktes im Shop (als Variable im JSON Objekt)
				json += "\"preis\": \""+temp[1]+"\"";
				// JSON Objekt schliessen
				json += "}";
			}
			// JSON Array beenden
			json += "]";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// JSON String weitergeben
		response.getWriter().append(json);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}