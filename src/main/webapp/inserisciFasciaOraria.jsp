<%@ page import="business.utente.Utente" %>
<%@ page import="business.admin.AdministratorBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%

    Utente user = (Utente) request.getSession().getAttribute("utente");
    if (user == null || user.getClass() != AdministratorBean.class){
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Questa pagina e' accessibile solo da admin");
        return;
    }

%>
<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Fascia Oraria</title>
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
    <form id="fascia" action="<%=response.encodeURL("./FasciaOrariaServlet")%>" >
    <div style="margin-top: 150px;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6" style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;max-width: 95%;width: 357px;">
                    <div>
                        <h1 class="text-center" style="font-family: Montserrat, sans-serif;">Fascia Oraria</h1>
                        <div class="text-center"><input type="text" name="fasciaOraria" placeholder="es. 13:00"></div>
                    </div>
                    <div class="text-center">
                    	<a onclick="document.getElementById('fascia').submit()"><input id="inviaFasciaInserisci" type="submit" class="btn btn-success" name="action" value="Inserisci" style="margin-right: 15px;"></a>
                    	<a onclick="document.getElementById('fascia').submit()"><input id="inviaFasciaElimina" type="submit" class="btn btn-danger" name="action" value="Elimina" style="margin-left: 15px;"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </form>
    <div style="height: 700px;"></div>
 	<jsp:include page="footer.jsp" />
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>