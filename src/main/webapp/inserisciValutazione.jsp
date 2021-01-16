<%@ page import="business.utente.Utente" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="business.valutazioni.ValutazioneBean" %>
<%@ page import="business.piatti.MenuBean" %>
<%@ page import="business.piatti.PiattoBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || !(utente instanceof Utente)) {
//         qua non ci puo' stare. TODO cambiare modo di fare il check (non riguarda i ragazzi del front-end)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
    
    ArrayList<ValutazioneBean> valutazioniByEmail = (ArrayList<ValutazioneBean>) request.getAttribute("valutazioniByEmail");
    if (valutazioniByEmail == null) {
        request.getRequestDispatcher("valutazione?action=ottieniValutazioni&email=" + utente.getEmail()).forward(request, response);
        return;
    }

    MenuBean menu = (MenuBean) request.getAttribute("menu");
    if (menu == null) {
        String destination = "/inserisciValutazione.jsp";
        request.getRequestDispatcher("menu?action=getMenu&destination=" + destination).forward(request, response);
        return;
    }
%>
<!DOCTYPE html>
<html style="height: 100%;width: 100%;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Recensioni</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
    <link rel="stylesheet" href="assets/css/Footer-Basic.css">
    <link rel="stylesheet"
          href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="height: auto;width: auto;background-image: url(&quot;assets/img/food.jpg&quot;);">
<jsp:include page="navbar.jsp" />
<div id="wrap" style="margin-top: 150px;">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col_main"
                 style="background-color: rgba(255,255,255,0.92);padding-bottom: 15px;padding-top: 15px;">
                <form method="post" action="<%=response.encodeURL("valutazione")%>">
                    <div>
                        <h1 style="font-family: Montserrat, sans-serif;">Piatti del Giorno</h1><select name="piatto" style="font-family: Montserrat, sans-serif;">
                        <optgroup label="Primi del Giorno">
                            <%
                                for (PiattoBean p : menu.getPrimi()){
                            %>
                            <option value="pasta al pomodoro"><%=p.getNome()%></option>
                            <%
                                }
                            %>
                        </optgroup>
                        <optgroup label="Secondi del Giorno">
                            <%
                                for (PiattoBean p : menu.getSecondi()){
                            %>
                            <option value="pasta al pomodoro"><%=p.getNome()%></option>
                            <%
                                }
                            %>
                        </optgroup>
                        <optgroup label="Contorni del Giorno">
                            <%
                                for (PiattoBean p : menu.getContorni()){
                            %>
                            <option value="pasta al pomodoro"><%=p.getNome()%></option>
                            <%
                                }
                            %>
                        </optgroup>
                    </select></div>
                    <div>
                        <h2 style="font-family: Montserrat, sans-serif;">Valutazione</h2>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"
                                                       name="valutazione" value="1"><label class="form-check-label"
                                                                                           for="formCheck-1">★☆☆☆☆</label>
                        </div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"
                                                       name="valutazione" value="2"><label class="form-check-label"
                                                                                           for="formCheck-1">★★☆☆☆<br></label>
                        </div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"
                                                       name="valutazione" value="3"><label class="form-check-label"
                                                                                           for="formCheck-1">★★★☆☆<br></label>
                        </div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"
                                                       name="valutazione" value="4"><label class="form-check-label"
                                                                                           for="formCheck-1">★★★★☆<br></label>
                        </div>
                        <div class="form-check"><input class="form-check-input" type="checkbox" id="formCheck-1"
                                                       name="valutazione" value="5"><label class="form-check-label"
                                                                                           for="formCheck-1">★★★★★<br></label>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit" style="background-color: rgb(66,160,22);">Invia
                        Valutazione
                    </button>
            </div>
            </form>
            <div class="col-md-6 col_main"
                 style="background-color: rgba(255,255,255,0.92);padding: 0px;padding-bottom: 15px;padding-top: 15px;">
                <div>
                    <h1 style="font-family: Montserrat, sans-serif;">I Tuoi Voti</h1>
                </div>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Piatto</th>
                            <th>Valutazione</th>
                            <th class="data-tabella">Data</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (ValutazioneBean bean : valutazioniByEmail) {
                        %>
                        <tr>
                            <td><%=bean.getPiatto()%></td>
                            <td><%=bean.getRecensione()%></td>
                            <td class="data-tabella"><%=bean.getDataValutazione()%></td>
                            <td><a href="<%=response.encodeURL("valutazione?action=rimuoviValutazione&piatto=" + bean.getPiatto() + "&email=" + bean.getEmail())%>"><button class="btn btn-primary" type="button" style="background-color: #d2453c;width: 30px;height: 30px;font-family: Montserrat, sans-serif;font-size: 15px;padding: 0px;">🗑</button></a></td>
                            <td><a><button class="btn btn-primary text-center" type="button" style="background-color: #e87f33;width: 30px;height: 30px;font-family: Montserrat, sans-serif;font-size: 17px;padding: 0px;">🔧</button></a></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="min-height: 310px;"></div>
<div class="text-center footer-basic" style="margin: 0px;background-color: rgb(20,20,20);margin-top: 0px;">
    <figure class="figure"><img class="img-fluid figure-img" src="assets/img/adisurclogo.png">
        <figcaption class="figure-caption">Agenzia per il Diritto allo Studio della Regione Campania © 2021<br>
        </figcaption>
    </figure>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>