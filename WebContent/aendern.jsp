<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3SC//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<!-- Aufgabe 2.2 -->

<html>
<head>
<meta charset='UTF-8'>
<title>Produkt ändern</title>
</head>
<body>
<%@page import="control.*" %>
<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%
// Produktdaten holen
Produkt produkt = (Produkt) request.getSession().getAttribute("produkt");
// Verfuegbare Shops holen
List<Shop> shops = (List<Shop>) request.getSession().getAttribute("shops");
%>
<form>
<label for='pId'>Produkt ID:</label>
<input id='pId' type='text' disabled value='<%=produkt.getId() %>'/> <!-- Ist disabled, da ID nicht veraendert werden sollte -->
<label for='pName'>Produkt Name:</label>
<input id='pName' type='text' value='<%=produkt.getName() %>'/>
<label for='pAmount'>Produkt Amount:</label>
<input id='pAmount' type='text' value='<%=produkt.getAmount() %>'/>
<label for='pShop'>Produkt Shop:</label>
<select id='pShop'>
	<%
	for(Shop s : shops) { // Alle Shops ausgeben
		%>
		<option value='<%=s.getId() %>'><%=s.getName() %></option>		
		<%
	}
	%>
</select>
<button type="submit">Bestätigen</button>
<!-- Theoretisch bräuchte es noch ein Servlet, was die Änderungen des Produktes übernimmt,
	wird jedoch nicht von der Aufgabenstellung verlangt -->
</form>
</body>
</html>