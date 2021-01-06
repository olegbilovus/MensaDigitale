<%@ page import="business.utente.Utente" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || !Utente.class.isInstance(utente)){
        // qua non ci puo' stare. TODO cambiare modo di fare il check (non riguarda i ragazzi del front-end)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<html>
<head>
    <title>Inserisci la tua valutazione</title>
</head>
<body>
    <form method="post" action="<%=response.encodeURL("valutazione")%>">
        <%-- da cambiare: si potrebbe implementare un menu a tendina con nomi dei piatti del giorno (??) o elenco da cui selezionare --%>
        <label for="piatto">Nome piatto da valutare:</label>
        <input type="text" name="piatto" id="piatto">

        <label for="valutazione">Valutazione: </label>
        <input id="valutazione" type="number" name="valutazione">

        <input name="email" value="<%=utente.getEmail()%>" style="visibility: hidden">
        <input name="action" value="aggiungiValutazione" style="visibility: hidden">

        <input type="submit" value="Invia!">
    </form>
</body>
</html>
