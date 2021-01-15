<%@ page import="business.utente.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente consumatore = (Utente) request.getSession().getAttribute("utente");
%>
<html>
<head>
    <title>Index</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <meta name="google-signin-client_id" content="112892589115-l441sfpsdcid969k4b23jva30fs8igod.apps.googleusercontent.com">
</head>
<body>
<div>
    <% if (consumatore != null) {%>
    <form action="<%=response.encodeURL("login")%>" method="post">
        <input name="action" value="logOut" style="visibility:hidden;">
        <input type="submit" value="Log Out">
    </form>
    <% } else { %>
    <p>null</p>
    <% } %>
    
</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>