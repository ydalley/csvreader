<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="description" content="Metro, a sleek, intuitive, and powerful framework for faster and easier web development for Windows Metro Style.">
    <meta name="keywords" content="HTML, CSS, JS, JavaScript, framework, metro, front-end, frontend, web development">
    <meta name="author" content="Sergey Pimenov and Metro UI CSS contributors">

    <link rel='shortcut icon' type='image/x-icon' href='../favicon.ico' />

    <title>Login form :: Metro UI CSS - The front-end framework for developing projects on the web in Windows Metro Style</title>

    <link href="css/metro.css" rel="stylesheet">
    <link href="css/metro-icons.css" rel="stylesheet">
    <link href="css/metro-responsive.css" rel="stylesheet">

    <script src="js/jquery-2.1.3.min.js"></script>
    <script src="js/metro.js"></script>
 
    <style>
        .login-form {
            width: 25rem;
            height: 18.75rem;
            position: fixed;
            top: 50%;
            margin-top: -9.375rem;
            left: 50%;
            margin-left: -12.5rem;
            background-color: #ffffff;
            opacity: 0;
            -webkit-transform: scale(.8);
            transform: scale(.8);
        }
    </style>

    <script>
        $(function(){
            var form = $(".login-form");

            form.css({
                opacity: 1,
                "-webkit-transform": "scale(1)",
                "transform": "scale(1)",
                "-webkit-transition": ".5s",
                "transition": ".5s"
            });
        });
    </script>
</head>
<body class="bg-darkTeal">
<s:if test="%{#request.shiroLoginFailure != null}">
		<s:hidden name="loginstatus" id="loginstatus" value="%{#request.shiroLoginFailure}"/>
	</s:if>
    <div class="login-form padding20 block-shadow">
       <s:form role="form">
            <h1 class="text-light">Login to Cafe Admin</h1>
            <hr class="thin"/>
            <br />
            <div class="input-control text full-size" data-role="input">
                <label for="user_login">User Id:</label>
                <input type="text" name="username" id="user_login">
                <button class="button helper-button clear"><span class="mif-cross"></span></button>
            </div>
            <br />
            <br />
            <div class="input-control password full-size" data-role="input">
                <label for="user_password">User password:</label>
                <input type="password" name="password" id="user_password">
                <button class="button helper-button reveal"><span class="mif-looks"></span></button>
            </div>
            <br />
            <br />
            <div class="form-actions">
                <button type="submit" class="button primary">Login to...</button>
                <button type="button" class="button link">Cancel</button>
            </div>
        </s:form>
    </div>

    <!-- hit.ua -->
    
    <!-- / hit.ua -->
	<script language="javascript" type="text/javascript">
		$(document).ready(function() {
			$('#loginstatus').each(function(index,element) {
				var mess = $(element).val();
				pushMessage(mess);
			});
		});
		function pushMessage(t) {
			var mes = 'Info|Implement independently';
			$.Notify({
				caption : "Login attempt was unsuccessful",
				content : t,
				type : "alert",keepOpen: true
			});
		}
	</script>
</body>
</html>