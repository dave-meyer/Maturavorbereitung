<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3SC//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>

<!-- Aufgabe 2.4 -->
<!-- Die einzelnen Produkte sollen als Tabelle dargestellt werden. Es soll in jeder Zeile ein Produkt
stehen. Eine Spaltenüberschrift soll anzeigen, um welchen Inhalt es sich handelt. Erzeuge eine
weitere Spalte mit einer leeren div-Box mit eindeutiger id zusätzlich für Aufgabe 2.5. -->

<%@page import="model.Produkt" %>
<%@page import="java.util.ArrayList" %>
<%
// Produkte aus der Session holen
ArrayList<Produkt> produkte = (ArrayList<Produkt>) request.getSession().getAttribute("produkte");

// Wenn Produkte gefunden wurden (optional)
if(produkte != null) {
	if(produkte.size() > 0) {
		%>
		<!-- Tabellen Header -->
		<table>
		<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Amount</th>
		<th>Shop</th>
		<th>Shops</th>
		</tr>
		<%
		// Alle Produkte in Tabelle darstellen
		for(int i = 0; i < produkte.size(); i++) {
			%>
			<tr onclick='getPriceInfo(<%=produkte.get(i).getId() %>)'> <!-- onclick-Event auf der ganzen Tabellenzeile;
																		Produkt ID wird als Paramenter mitgegeben -->
			<td><%=produkte.get(i).getId() %></td>
			<td><%=produkte.get(i).getName() %></td>
			<td><%=produkte.get(i).getAmount() %></td>
			<td><%=produkte.get(i).getShop().getName() %></td>
			<td id='produkt<%=produkte.get(i).getId() %>'></td> <!-- Feld ist anfangs leer; hat die ID des Produktes -->
			</tr>
			<%
		}
		%>
		</table>
		<%
	}
}
else {
	%>
	<p>Keine Produkte vorhanden</p>
	<%
}

%>
</body>
<script>

// Aufgabe 2.5
/*
 * Bei einem Klick auf ein Produkt sollen über die Produkt-Id aktuelle Preise in den
   einzelnen Shops abgefragt werden. Dazu wird ein Servlet aufgerufen (welches intern Webservices
   der Shops aufruft), das die Preisinformationen einzelner Geschäfte als JSON zurueckgibt
   Erweitere die Seite anzeige.jsp jetzt mittels AJAX/JSON so, dass diese Inhalte in einer Liste direkt
   beim Produkt angezeigt wird (also die leere div-Box bei der Anzeige des Produktes befüllt wird).
 */
// Wenn auf ein Produkt (Zeile) geklickt wird
function getPriceInfo(pId) {
	// AJAX Request
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(this.status == 200 && this.readyState == 4) { // Wenn Request erfolgreich
			// Erhaltenen JSON String in Objekt (Array) umwandeln
			var infos = JSON.parse(this.responseText);
			// infoText enthaelt den HTML Code zur Ausgabe
			var infoText = "<ul>"; // Liste beginnen
			// Alle Eintraege im Array durchlaufen (enthalten Shop und Preis)
			for(var i = 0; i < infos.length; i++) { 
				// Shop und Preis als List-Items ausgeben
				infoText += "<li>"+infos[i].shop+" "+infos[i].preis+"</li>";
			}
			infoText += "</ul>"; // Liste beenden
			document.getElementById("produkt"+pId).innerHTML = infoText;
		}
	}
	// Produkt ID an ShopServlet senden
	xhttp.open("GET", "ShopsServlet?pId="+pId, true);
	xhttp.send();
}
</script>
</html>