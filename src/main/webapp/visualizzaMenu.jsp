<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="business.utente.Utente" %>
<%@ page import="business.addetto.AddettoBean" %>
<%@ page import="business.piatti.MenuBean" %>
<%@ page import="business.piatti.PiattoBean" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%
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
    <link rel="stylesheet" href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="background-image: url(&quot;assets/img/food.jpg&quot;);height: auto;width: auto;max-height: none;">
   <jsp:include page="navbar.jsp" />
    <div style="margin-top: 200px;">
        <div class="container">
            <div class="row" style="background-color: rgba(255,255,255,0.92);">
                <div class="col-md-12">
                    <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 15px;">Menù del Giorno</h1>
                    <div>
                        <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Primi</p>
                        <%
                            for (PiattoBean p : menu.getPrimi()){
                        %>
                        <div><label style="font-family: Montserrat, sans-serif;font-size: 18px;"><%=p.getNome()%></label></div>
                        <%
                            }
                        %>
                    </div>
                    <div>
                        <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Secondi</p>
                        <%
                            for (PiattoBean p : menu.getSecondi()){
                        %>
                        <div><label style="font-family: Montserrat, sans-serif;font-size: 18px;"><%=p.getNome()%></label></div>
                        <%
                            }
                        %>
                    </div>
                    <div>
                        <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Contorni</p>
                        <%
                            for (PiattoBean p : menu.getContorni()){
                        %>
                        <div><label style="font-family: Montserrat, sans-serif;font-size: 18px;"><%=p.getNome()%></label></div>
                        <%
                            }
                        %>
                    </div>
                    
                    <%
                    	Utente consumatore = (Utente) request.getSession().getAttribute("utente");
   						if(consumatore!=null) {
  							
		                    	if(consumatore.getClass()==AddettoBean.class)
		                    	 {
							%>
		                    		<div class="text-center">
				                    	<button class="btn btn-warning" type="button" style="margin-right: 20px;">Modifica Menù</button>
				                    	<button class="btn btn-danger" type="button" style="margin-left: 20px;">Elimina Menù</button>
				                    </div>
		                    <%} }%>
                    </div>
            </div>
        </div>
    </div>
    <div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);margin-top: 100px;">
        <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
            <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br></figcaption>
        </figure>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>