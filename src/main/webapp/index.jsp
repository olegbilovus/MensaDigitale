<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("consumatore");
%>
<html>
<head>
    <title>Index</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
</head>
<body>
<div>
    <p>Just to test: </p>
    <% if (consumatore != null) {%>
    <p>Nome: <%=consumatore.getNome()%></p>
    <p>Email: <%=consumatore.getEmail()%></p>
    <p>Codice Fiscale: <%=consumatore.getCodiceFiscale()%></p>
    <% } else { %>
    <p>null</p>
    <% } %>
</div>
</body>
</html>