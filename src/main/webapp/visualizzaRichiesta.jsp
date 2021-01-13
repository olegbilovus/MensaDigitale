<%@page import="java.util.Date"%>
<%@page import="business.richieste.RichiestaBean"%>
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
	RichiestaBean r = (RichiestaBean) request.getAttribute("richiesta");
	String cognome = (String) request.getAttribute("rCognome");
	String nome = (String) request.getAttribute("rNome");
	Date data = (Date) request.getAttribute("rData");
	String provincia = (String) request.getAttribute("rProvincia");
	String comune = (String) request.getAttribute("rComune");
	String cf = (String) request.getAttribute("rCodice");
	String cittadinanza = (String) request.getAttribute("rCittadinanza");
	boolean rifugiato = (boolean) request.getAttribute("rRifugiato");
	boolean residenza = (boolean) request.getAttribute("rResidenza");
	String indirizzo = (String) request.getAttribute("rIndirizzo");
	String telefono = (String) request.getAttribute("rTelefono");
	String cellulare = (String) request.getAttribute("rCellulare");
	String email = (String) request.getAttribute("rEmail");

	if (r == null) {
	%>
	<h1>Errore: Accesso illegale alla pagina</h1>
	<%
	} else {
	%>
	<div>
		Cognome:
		<%=cognome%>
	</div>
	<div>Nome:</div>
	<div>Data di nascita:</div>
	<div>Provincia di nascita:</div>
	<div>Comune di nascita:</div>
	<div>Codice fiscale:</div>
	<div>Cittadinanza:</div>
	<div>
		Studente apolide o rifugiato: <input type="checkbox" disabled
			<%if (rifugiato) {%> checked <%}%>>
	</div>
	<div>
		Residenza dell'intero nucleo familiare: <input type="checkbox"
			disabled <%if (residenza) {%> checked <%}%>>
	</div>
	<div>Indirizzo:</div>
	<div>Telefono:</div>
	<div>Cellulare:</div>
	<div>Indirizzo e-mail:</div>
	<div>
		<input type="checkbox" disabled checked> Presentazione dei
		dati relativi alla condizione economica ed al merito
	</div>
	<div>
		<input type="checkbox" disabled checked> Dichiarazioni
	</div>
	<div>
		<form action="<%=response.encodeURL("/ValutaRichiestaServlet")%>"
			method="post">
			<input type="hidden" name="esito" value="1"> <input
				type="submit" value="Approva">
		</form>
		<form>
			<input type="hidden" name="esito" value="2"> <input
				type="submit" value="Rifiuta">
		</form>
	</div>
	<%
	}
	%>
</body>
</html>