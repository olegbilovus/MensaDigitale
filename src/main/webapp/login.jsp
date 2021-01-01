<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-client_id" content="112892589115-l441sfpsdcid969k4b23jva30fs8igod.apps.googleusercontent.com">
</head>
<body>
<script>
    function onSignIn(googleUser) {
        var tokenId = googleUser.getAuthResponse().id_token;
        $("#tokenId").val(tokenId);
        $("#myForm").submit();
    }
</script>
<form id="myForm" action="<%=response.encodeURL("login")%>" method="post">
    <input id="tokenId" name="tokenId" value="" style="visibility: hidden">
</form>
<div class="g-signin2" data-onsuccess="onSignIn"></div>
</body>
</html>
