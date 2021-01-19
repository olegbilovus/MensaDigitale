<%@ page import="business.utente.Utente" %>
<%@ page import="business.consumatore.ConsumatoreBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
	Utente user = (Utente) request.getSession().getAttribute("utente");
	if (user == null || user.getClass() != ConsumatoreBean.class || ((ConsumatoreBean) user).isDocente()){
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Questa pagina e' accessibile solo da studenti");
	}
%>
<!DOCTYPE html>
<html style="height: auto; width: auto; color: rgb(255, 255, 255);">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Attivazione</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Bitter:400,700">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="assets/css/Footer-Basic.css">
<link rel="stylesheet"
	href="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.css">
<link rel="stylesheet" href="assets/css/styles.css">
</head>

<body
	style="background-image: url(&amp;quot;assets/img/food.jpg&amp;quot;); height: auto; width: auto;">
	<jsp:include page="navbar.jsp" />
	<div
		style="margin-top: 150px; background-color: rgba(255, 255, 255, 0.92);">
		<div>
			<h1 class="text-center"
				style="font-family: Montserrat, sans-serif; margin-top: 0px; padding-top: 10px;">Richiesta
				per l'attivazione dei servizi di ristorazione</h1>
		</div>
		<h5 class="text-center" style="padding-left: 10px;">Dati
			Anagrafici</h5>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col_main"
					style="background-color: rgba(255, 255, 255, 0.92); padding-bottom: 15px; padding-top: 15px;">
					<form method="post"
						action="<%=response.encodeURL("./SubmitRichiestaServlet")%>">
						<div>
							<label
								style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Nome</label><input
								type="text" name="nome"
								style="font-family: Montserrat, sans-serif;">
						</div>
						<div>
							<label
								style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Cognome</label><input
								type="text" name="cognome"
								style="font-family: Montserrat, sans-serif;">
						</div>
						<div>
							<label
								style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Data
								di Nascita</label><input type="text" name="dataDiNascita"
								placeholder="dd/mm/yyyy"
								style="font-family: Montserrat, sans-serif;">
						</div>
						<div>
							<label
								style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Comune
								di Nascita</label><input type="text" name="comuneDiNascita"
								style="font-family: Montserrat, sans-serif;">
						</div>
				</div>
				<div class="col-md-6 col_main"
					style="background-color: rgba(255, 255, 255, 0.92); padding: 0px; padding-bottom: 15px; padding-top: 15px; padding-right: 15px; padding-left: 15px;">
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Provincia
							di Nascita</label><input type="text" name="provinciaDiNascita"
							placeholder="es. SA" style="font-family: Montserrat, sans-serif;">
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Cittadinanza</label><input
							type="text" name="cittadinanza"
							style="font-family: Montserrat, sans-serif;">
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Codice
							Fiscale</label><input type="text" name="codiceFiscale"
							style="font-family: Montserrat, sans-serif;">
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Studente
							Apolide o Rifugiato</label>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rifugiato"
								value="true" id="formCheck-1"><label
								class="form-check-label" for="formCheck-1">Si</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="rifugiato"
								value="false" id="formCheck-2"><label
								class="form-check-label" for="formCheck-2">No</label>
						</div>
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Residenza
							dell'Intero Nucleo Familiare all'estero</label>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="residenzaNucleo" value="true" id="formCheck-1"><label
								class="form-check-label" for="formCheck-1">Si</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="residenzaNucleo" value="false" id="formCheck-2"><label
								class="form-check-label" for="formCheck-2">No</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h5 class="text-center" style="padding-left: 10px;">Residenza</h5>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col_main"
					style="background-color: rgba(255, 255, 255, 0.92); padding-bottom: 15px; padding-top: 15px;">
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Indirizzo</label><input
							type="text" name="indirizzo"
							style="font-family: Montserrat, sans-serif;">
					</div>
				</div>
				<div class="col-md-6 col_main"
					style="background-color: rgba(255, 255, 255, 0.92); padding: 0px; padding-bottom: 15px; padding-top: 15px; padding-right: 15px; padding-left: 15px;">
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Telefono</label><input
							type="text" name="telefono"
							style="font-family: Montserrat, sans-serif;">
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Cellulare</label><input
							type="text" name="cellulare"
							style="font-family: Montserrat, sans-serif;">
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Indirizzo
							Email</label><input type="text" name="email"
							style="font-family: Montserrat, sans-serif;">
					</div>
					<div>
						<label
							style="padding-left: 10px; padding-right: 10px; font-family: Montserrat, sans-serif;">Conferma
							Indirizzo Email</label><input type="text" name="confermaEmail"
							style="font-family: Montserrat, sans-serif;">
					</div>
				</div>
			</div>
		</div>
		<h5 class="text-center" style="padding-left: 10px;">Presentazione
			dati relativi alla condizione economica e al merito</h5>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col_main"
					style="background-color: rgba(255, 255, 255, 0.92); padding-bottom: 15px; padding-top: 15px;">
					<p class="text-break" id="datiRelativi">Richiedo di accedere ai
						servizi in II categoria di tariffazione di cui alla Programmazione
						Regionale Diritto Allo Studio Universitario a.a. 2020/2021 per il
						rilascio del tesserino magnetico e per l'accesso al servizio di
						ristorazione senza presentare ulteriori dati.</p>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="prelazione"
							value="true" id="formCheck-3"><label
							class="form-check-label" for="formCheck-3">Acconsento</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="background-color: rgba(255, 255, 255, 0.92);">
		<div class="container">
			<div class="row justify-content-center" style="margin-top: -15px;">
				<div class="col">
					<h5 class="text-center" style="padding-left: 10px;">Dichiarazione</h5>
				</div>
				<div class="col-md-12"
					style="background-color: rgba(255, 255, 255, 0.92);">
					<p class="text-break" id="datiRelativi">Io sottoscritto
						consapevole delle responsabilità e sanzioni anche penali cui va
						incontro chi rende dichiarazioni false, parziali e non rispondenti
						al vero stabilite dall'art. 76 del D.P.R. 445/2000 D I C H I A R O
						Ai sensi degli art. 46 e 47 del D.P.R. 445/2000 di essere a
						conoscenza della Programmazione Regionale per il Diritto allo
						Studio Universitario e di confermare che i dati riportati nel
						presente modello e negli appositi prospetti allegati sono completi
						e veritieri. DECRETO LEGISLATIVO 30/06/2003 N. 196 "CODICE IN
						MATERIA DI PROTEZIONE DEI DATI PERSONALI" I dati richiesti in
						autocertificazione nonchè quelli contenuti nella documentazione
						richiesta sono destinati al complesso delle operazioni, svolto con
						mezzi elettronici ed automatizzati, finalizzate all'elaborazione
						delle graduatorie per l'assegnazione delle borse di studio di cui
						alla legge 390/91. La resa dei dati richiesti è obbligatoria per
						la partecipazione al concorso ed, alla mancata presentazione,
						consegue l'esclusione dal concorso medesimo. Lo studente all'atto
						della presentazione della domanda esprime il proprio consenso: -
						al trattamento, con modalità elettroniche e/o automatizzate, dei
						propri dati personali per fini istituzionali, sia da parte dell'
						Adisurc che da parte di eventuali Società Enti o Consorzi, che
						svolgono attività di elaborazione dati funzionali a quella
						dell'ADISURC; - alla pubblicazione dei propri dati personali che
						si rendono necessari ai fini istituzionali dell'Ente; - al
						trasferimento dei propri dati personali a soggetti aventi diritto
						di accesso ex lege, soggetti ai quali il trasferimento di detti
						dati risulti funzionale ai fini istituzionali dell'ADISURC. Il
						titolare del trattamento dei dati, riguardanti i benefici erogati,
						è l'ADISURC con sede legale in Via Alcide De Gasperi, 45 - 80133 -
						Napoli (NA).</p>
					<div class="form-check">
						<input class="form-check-input" type="checkbox" name="responsabilita" value="true" id="formCheck-3"><label
							class="form-check-label" for="formCheck-3">Acconsento</label>
					</div>
				</div>
				<div class="col text-center">
					<input type="submit" value="Invia domanda" class="btn btn-success text-center">
				</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://unpkg.com/@bootstrapstudio/bootstrap-better-nav/dist/bootstrap-better-nav.min.js"></script>
</body>

</html>
