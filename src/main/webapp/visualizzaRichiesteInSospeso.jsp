<%@page import="business.richieste.RichiestaBean"%>
<%@page import="business.richieste.RichiesteInSospeso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	RichiesteInSospeso ris = RichiesteInSospeso.getInstance();
	if (ris.getListaRichieste().isEmpty()) {
	%>
	<h1>Non ci sono richieste in attesa di esito.</h1>
	<%
	} else {
	%>
	<table>
		<%
		for (RichiestaBean r : ris.getListaRichieste()) {
		%>
		<tr>
			<td>
				<form method="post" action="<%=response.encodeURL("/ValutaRichiesta")%>">
					<input type="hidden" value="<%=r.getId() %>" name="id">
					<input type="submit" value="<%=r.getEmail() %>">
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	}
	%>
</body>
</html>