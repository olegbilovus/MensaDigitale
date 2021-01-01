<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ConsumatoreBean consumatore = (ConsumatoreBean) request.getSession().getAttribute("consumatore"); //TODO replace with Utente when ready
    if (consumatore != null){
      response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
%>
<html>
<head>
    <title>Login</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="112892589115-l441sfpsdcid969k4b23jva30fs8igod.apps.googleusercontent.com">
</head>
<body>
<script>
    function onSignIn(googleUser) {
        // 1. manda richiesta a Google
        // 2. ottiene email da Google
        // 3. imposta il value dell'input name 'email' di 'formGoogle' uguale all'email restituita da Google
        // 4. esegue il submit della form
        let profile = googleUser.getBasicProfile();
        let email = profile.getEmail();
        googleUser.disconnect();
        $("#emailGoogle").val(email);
        $("#formGoogle").submit();
    }
</script>
<%-- Login con email e password --%>
<%--<form action="<%=response.encodeURL("login")%>" method="post">--%>
<%--    <input id="email" type="email" name="email">--%>
<%--    <label for="email">Email</label>--%>
<%--    <input id="password" type="password" name="password">--%>
<%--    <label for="password">Password</label>--%>
<%--    <input name="action" value="loginEmailPassword" style="visibility: hidden">--%>
<%--    <input type="submit" value="Accedi">--%>
<%--</form>--%>

<%-- Login con Google --%>
<form id="formGoogle" action="<%=response.encodeURL("login")%>" method="post">
    <input id="emailGoogle" name="email" value="" style="visibility: hidden">
    <input name="action" value="loginGoogle" style="visibility: hidden">
    <div class="g-signin2" data-onsuccess="onSignIn"></div>
</form>
</body>
</html>
