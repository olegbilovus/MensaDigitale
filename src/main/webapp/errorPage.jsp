<%@ page language="java" contentType="text/html; UFT-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

	<title>404</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:200,400,700" rel="stylesheet">


	<link href="assets/css/errorPage.css" rel="stylesheet" />

	

</head>

<body>

	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>Oops!</h1>
				<h2> Qualcosa è andato storto </h2>
			</div>
			<a href="<%= response.encodeURL("index.jsp") %>">Torna alla home</a>
		</div>
	</div>



</html>