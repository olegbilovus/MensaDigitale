<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Titolo</title>
</head>
<body>
	<form method="post"
		action="<%=response.encodeURL("/SubmitRichiestaServlet")%>">
		<div>
			Cognome: <input type="text" id="inCognome" name="cognome">
		</div>
		<div>
			Nome: <input type="text" id="inNome" name="nome">
		</div>
		<div>
			Data di nascita: <input type="text" id="inData" name="dataDiNascita"
				placeholder="dd/mm/yyyy">
		</div>
		<div>
			Provincia di nascita: <input type="text" id="inProvincia"
				name="provinciaDiNascita" maxlength="10">
		</div>
		<div>
			Comune di nascita: <input type="text" id="inComune"
				name="comuneDiNascita">
		</div>
		<div>
			Codice fiscale: <input type="text" id="inCodice" name="codiceFiscale"
				maxlenght="16">
		</div>
		<div>
			Cittadinanza: <input type="text" id="inCittadinanza"
				name="cittadinanza">
		</div>
		<div>
			Studente rifugiato: <input type="radio" id="inRifugiatoT"
				name="rifugiato" value="true"> <label for="inRifugiatoT">Sì</label><input
				type="radio" id="inRifugiatoF" name="rifugiato" value="false"
				checked> <label for="inRifugiatoF">No</label>
		</div>
		<div>
			Nucleo familiare residente all'estero: <input type="radio"
				id="inResidenzaT" name="residenzaNucleo" value="true"> <label
				for="inResidenzaT">Sì</label><input type="radio" id="inResidenzaF"
				name="residenzaNucleo" value="false" checked> <label
				for="inResidenzaF">No</label>
		</div>
		<div>
			Indirizzo: <input type="text" id="inIndirizzo" name="indirizzo">
		</div>
		<div>
			Telefono: <input type="text" id="inTelefono" name="telefono">
		</div>
		<div>
			Cellulare: <input type="text" id="inCellulare" name="cellulare">
		</div>
		<div>
			Email: <input type="email" id="inEmail" name="email">
		</div>
		<div>
			Conferma email: <input type="email" id="inConfermaEmail"
				name="confermaEmail">
		</div>
		<div>
			Richiedo di accedere ai servizi in II categoria di tariffazione di
			cui alla Programmazione Regionale Diritto Allo Studio Universitario
			a.a. 2020/2021 per il rilascio del tesserino magnetico e per
			l'accesso al servizio di ristorazione senza presentare ulteriori
			dati. <input type="radio" id="inPrelazioneT" name="prelazione"
				value="true"><label for="inPrelazioneT">Accetto</label> <input
				type="radio" id="inPrelazioneF" name="prelazione" value="false"
				checked> <label for="inPrelazioneF">Non accetto</label>
		</div>
		<div>
			Io sottoscritto consapevole delle responsabilità e sanzioni anche
			penali cui va incontro chi rende dichiarazioni false, parziali e non
			rispondenti al vero stabilite dall'art. 76 del D.P.R. 445/2000 D I C
			H I A R O Ai sensi degli art. 46 e 47 del D.P.R. 445/2000 di essere a
			conoscenza della Programmazione Regionale per il Diritto allo Studio
			Universitario e di confermare che i dati riportati nel presente
			modello e negli appositi prospetti allegati sono completi e
			veritieri. DECRETO LEGISLATIVO 30/06/2003 N. 196 "CODICE IN MATERIA
			DI PROTEZIONE DEI DATI PERSONALI" I dati richiesti in
			autocertificazione nonchè quelli contenuti nella documentazione
			richiesta sono destinati al complesso delle operazioni, svolto con
			mezzi elettronici ed automatizzati, finalizzate all'elaborazione
			delle graduatorie per l'assegnazione delle borse di studio di cui
			alla legge 390/91. La resa dei dati richiesti è obbligatoria per la
			partecipazione al concorso ed, alla mancata presentazione, consegue
			l'esclusione dal concorso medesimo. Lo studente all'atto della
			presentazione della domanda esprime il proprio consenso: - al
			trattamento, con modalità elettroniche e/o automatizzate, dei propri
			dati personali per fini istituzionali, sia da parte dell' Adisurc che
			da parte di eventuali Società Enti o Consorzi, che svolgono attività
			di elaborazione dati funzionali a quella dell'ADISURC; - alla
			pubblicazione dei propri dati personali che si rendono necessari ai
			fini istituzionali dell'Ente; - al trasferimento dei propri dati
			personali a soggetti aventi diritto di accesso ex lege, soggetti ai
			quali il trasferimento di detti dati risulti funzionale ai fini
			istituzionali dell'ADISURC. Il titolare del trattamento dei dati,
			riguardanti i benefici erogati, è l'ADISURC con sede legale in Via
			Alcide De Gasperi, 45 - 80133 - Napoli (NA). <input type="radio"
				id="inResponsabilitaT" name="responsabilita" value="true"><label
				for="inResponsabilitaT">Accetto</label> <input type="radio"
				id="inResponsabilitaF" name="responsabilita" value="false" checked>
			<label for="inResponsabilitaF">Non accetto</label>
		</div>
		<div>
			<input type="submit" value="Conferma richiesta">
		</div>
	</form>
</body>
</html>