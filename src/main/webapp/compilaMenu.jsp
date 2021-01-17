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

<!DOCTYPE html>
<html style="height: auto;width: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Compila Menù</title>
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
                    <h1 class="text-center" style="font-family: Montserrat, sans-serif;margin-top: 15px;">Piatti Disponibili</h1>
                    <p class="text-left" style="font-family: Montserrat, sans-serif;font-size: 22px;">Seleziona i piatti da inserire nel menù</p>
                    <%
				            for (PiattoBean piatto : piatti){
				              String nome = piatto.getNome();
				    %>
                    <div class="form-check" style="font-family: Montserrat, sans-serif;"><input class="form-check-input" type="checkbox" id="<%=nome%>"><label class="form-check-label" for="formCheck-1"><strong><%=nome%></strong></label></div>
                    <%}%>
                    <div class="text-center">
                    	<button class="btn btn-primary" data-toggle="modal" data-target="#nuovoPiatto" type="button" style="margin-right: 20px;">Nuovo Piatto</button>
							    <div class="modal fade" id="nuovoPiatto" role="dialog" tabindex="-1">
							        <div class="modal-dialog" role="document">
							            <div class="modal-content">
							                <div class="modal-header">
							                    <h4 class="modal-title">Inserisci Piatto</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
							                <div class="modal-body">
							                    <div class="text-left"><label style="margin-right: 10px;">Nome</label><input type="text" name="nomePiatto"></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Ingredienti</label><input type="text" name="nomePiatto"></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Portata</label><select><option value="primo" selected="">Primo Piatto</option><option value="secondo">Secondo Piatto</option><option value="contorno">Contorno</option></select></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Calorie</label><input type="text" name="nomePiatto"></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Proteine</label><input type="text" name="nomePiatto"></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Grassi</label><input type="text" name="nomePiatto"></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Sodio</label><input type="text" name="nomePiatto"></div>
							                    <div class="text-left"><label style="margin-right: 10px;">Carboidrati</label><input type="text" name="nomePiatto"></div>
							                </div>
							                <div class="modal-footer"><button class="btn btn-warning" type="button" data-dismiss="modal">Annulla</button><button class="btn btn-success" type="button">Salva</button></div>
							            </div>
							        </div>
							    </div>
                    	<button class="btn btn-success" type="button" style="margin-left: 20px;">Conferma Menù</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="height: 130px;"></div>
   <jsp:include page="footer.jsp" />
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>