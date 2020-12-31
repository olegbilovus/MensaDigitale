<%@ page import="java.util.Collection" %>
<%@ page import="business.piatti.PiattoBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        Collection<PiattoBean> piatti = (Collection<PiattoBean>) request.getAttribute("tuttiPiatti");
        String error = (String) request.getAttribute("error");
        if (piatti == null && error == null) {
            /*vuol dire che fin'ora non siamo mai passati per la Servlet*/
            response.sendRedirect("piatto?action=getTuttiPiatti&destination=compilaMenu.jsp");
            return;
        }
%>
<html>
<head>
    <title>Compila un nuovo Menu</title>
</head>
<body>
<div>
    <form action="<%=response.encodeURL("menu")%>" method="post">
        <%
            for (PiattoBean piatto : piatti){
              String nome = piatto.getNome();
        %>
        <input type="checkbox" id="<%=nome%>" name="piatti" value="<%=nome%>">
        <label for="<%=nome%>"><%=nome%></label>
        <%}%>
        <input name="action" value="aggiungiMenu" style="visibility: hidden">
        <input type="submit" value="Invia">
    </form>
</div>
</body>
</html>
