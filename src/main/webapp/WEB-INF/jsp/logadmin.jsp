<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
  	<title>Login administrateur</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" type="image/png" href="plugins/images/favicon.png">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="stylesheet" href="/loginadmin/css/style.css">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section"></h2>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
		      	<div class="icon d-flex align-items-center justify-content-center">
		      		<span class="fa fa-user-o"></span>
		      	</div>
		      	<h3 class="text-center mb-4">Admininistrateur</h3>

				<form method="POST" action="/login" class="login-form">
		      		<div class="form-group">
		      			<input type="text" class="form-control rounded-left" placeholder="Identifiant" name="identifiant" required>
		      		</div>
                    <div class="form-group d-flex">
                      <input type="password" class="form-control rounded-left" placeholder="Mot de passe" name="mdp" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="form-control btn btn-primary rounded submit px-3">connexion</button>
                    </div>
                    <a href="/region/">Se connecter en tant que région</a>
                    <div class="form-group d-md-flex">
                        <%
                            if(request.getParameter("error")!=null){ %>
                                <p style="color:red;">Connexion échouée, veuillez réessayer</p>
                        <% } %>
                    </div>
	          </form>
	        </div>
				</div>
			</div>
		</div>
	</section>

	<script src="/loginadmin/js/jquery.min.js"></script>
    <script src="/loginadmin/js/popper.js"></script>
    <script src="/loginadmin/js/bootstrap.min.js"></script>
    <script src="/loginadmin/js/main.js"></script>

	</body>
</html>

