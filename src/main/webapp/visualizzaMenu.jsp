<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="business.utente.Utente" %>
<%@ page import="business.addetto.AddettoBean" %>
<%@ page import="business.piatti.MenuBean" %>
<%@ page import="business.piatti.PiattoBean" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page import="java.util.Collection" %>
<%
    Collection<PiattoBean> piatti = (Collection<PiattoBean>) request.getAttribute("tuttiPiatti");
    String error = (String) request.getAttribute("error");
    if (piatti == null && error == null) {
        /*vuol dire che fin'ora non siamo mai passati per la Servlet*/
        response.sendRedirect("piatto?action=getTuttiPiatti&destination=visualizzaMenu.jsp");
        return;
    }

    MenuBean menu = (MenuBean) request.getAttribute("menu");
    if (menu == null) {
        String destination = "/visualizzaMenu.jsp";
        request.getRequestDispatcher("menu?action=getMenu&destination=" + destination).forward(request, response);
        return;
    }
%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Visualizza Menu</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet" href="assets/css/Google-Style-Login.css">
    <link rel="stylesheet" href="assets/css/Header-Dark.css">
    <link rel="stylesheet"
          href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
<jsp:include page="navbar.jsp"/>
<form id="elimina" action="<%=response.encodeURL("./menu?action=cancellaMenu")%>" method="post"></form>
<div style="margin-top: 200px;">
    <div class="container">
        <div class="row" style="background-color: rgba(255,255,255,0.92);">
            <div class="col-md-12">
                <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 15px;">Menù del
                    Giorno</h1>
                <div>
                    <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Primi</p>
                    <%
                        for (PiattoBean p : menu.getPrimi()) {
                    %>
                    <div><label style="font-family: Montserrat, sans-serif;font-size: 18px;"><%=p.getNome()%>
                    </label></div>
                    <%
                        }
                    %>
                </div>
                <div>
                    <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Secondi</p>
                    <%
                        for (PiattoBean p : menu.getSecondi()) {
                    %>
                    <div><label style="font-family: Montserrat, sans-serif;font-size: 18px;"><%=p.getNome()%>
                    </label></div>
                    <%
                        }
                    %>
                </div>
                <div>
                    <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Contorni</p>
                    <%
                        for (PiattoBean p : menu.getContorni()) {
                    %>
                    <div><label style="font-family: Montserrat, sans-serif;font-size: 18px;"><%=p.getNome()%>
                    </label></div>
                    <%
                        }
                    %>
                </div>

                <%
                    Utente consumatore = (Utente) request.getSession().getAttribute("utente");
                    if (consumatore != null) {

                        if (consumatore.getClass() == AddettoBean.class) {
                %>
                <div class="text-center">
                    <button class="btn btn-warning" type="button" data-toggle="modal" data-target="#modificaMenu"
                            style="margin-right: 20px;">Modifica Menù
                    </button>
                    <form method="post" action="menu">
                        <div class="modal fade" id="modificaMenu" role="dialog" tabindex="-1">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Modifica Menù</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                                aria-hidden="true">×</span></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="text-left">
                                            <%
                                                for (PiattoBean p : piatti) {
                                            %>
                                            <div class="form-check"><input class="form-check-input" type="checkbox"
                                                                           id="<%=p.getNome()%>" name="piatti" value="<%=p.getNome()%>"><label
                                                    class="form-check-label"
                                                    for="<%=p.getNome()%>"
                                                    style="font-size: 17px;font-family: Montserrat, sans-serif;"><%=p.getNome()%>
                                            </label></div>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-warning" type="button" data-dismiss="modal">Annulla</button>
                                        <button class="btn btn-success" type="submit">Salva</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input name="action" value="modificaMenu" style="visibility: hidden; display: block">
                    </form>
                    <a onclick="document.getElementById('elimina').submit()">
                        <button class="btn btn-danger" type="button" style="margin-left: 20px;">Elimina Menù</button>
                    </a>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
<script>
    <%
    for (PiattoBean p : piatti){
      if (menu.getPrimi().contains(p) || menu.getSecondi().contains(p) || menu.getContorni().contains(p)){
    %>
        document.getElementById("<%=p.getNome()%>").checked = true;
    <%
      }
    }
    %>
</script>
</body>

</html>