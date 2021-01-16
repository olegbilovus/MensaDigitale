<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=response.encodeURL("./FasciaOrariaServlet")%>" >
		Fascia oraria: <input type="text" name="fasciaOraria" placeholder="es. 13:00">
		<input type="submit" name="action" value="inserisci"> <input type="submit" name="action" value="elimina">
	</form>
</body>
</html>