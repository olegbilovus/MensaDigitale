<%@ page import="business.prenotazioni.PrenotazioneBean"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
PrenotazioneBean<String> prenotazione =
    (PrenotazioneBean<String>) request.getSession().getAttribute("prenotazione");
%>


<!DOCTYPE html>
<html>
<head>
<title>Prenotazione</title>
</head>
<body>
	<%
	Boolean error = (Boolean) request.getAttribute("error");
	if (error != null && error) {
	%>
	<h1>error</h1><%
	}
	%>
	<form action="<%=response.encodeURL("prenotazione")%>" method="post">
		<label>Fascia Oraria <select name="fasciaOraria">
				<option value="1">x</option>
				<option value="2">y</option>
				<option value="3">z</option>
		</select>
		</label> <label>Sala <select name="sala">
				<option value="1">x</option>
				<option value="2">y</option>
				<option value="3">z</option>
		</select>
			<button type="submit">Conferma</button>
	</form>

	<form action="<%=response.encodeURL("prenotazione")%>" method="get">
		<button type="submit">Elimina</button>
	</form>
	<img alt="QRCode" src="<%=response.encodeURL("qrcode?height=500&width=500")%>">
</body>
</html>