<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("consumatore"); //TODO replace with Utente when ready
%>
<html>
<head>
    <title>Index</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <meta name="google-signin-client_id" content="112892589115-l441sfpsdcid969k4b23jva30fs8igod.apps.googleusercontent.com">
    <script>
        function logOut(){
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
                console.log('User signed out.');
            });
        }
        function onLoad() {
            gapi.load('auth2', function() {
                gapi.auth2.init();
            });
        }
    </script>
</head>
<body>
<div>
    <p>Just to test: </p>
    <% if (consumatore != null) {%>
    <p>Nome: <%=consumatore.getNome()%></p>
    <p>Email: <%=consumatore.getEmail()%></p>
    <p>Codice Fiscale: <%=consumatore.getCodiceFiscale()%></p>
    <form action="<%=response.encodeURL("login")%>" method="post">
        <input name="action" value="logOut" style="visibility:hidden;">
        <input type="submit" onclick="logOut()" value="Log Out">
    </form>
    <% } else { %>
    <p>null</p>
    <% } %>
</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>