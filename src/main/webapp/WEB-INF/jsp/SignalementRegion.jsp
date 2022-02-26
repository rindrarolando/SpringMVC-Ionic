<%@page import="java.util.List"%>
<%@page import="com.projet.cloudmobile.models.Region"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/utilities.css">
    
    <link rel="stylesheet" href="css/stylegrid.css">
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==" crossorigin="" />
        <style type="text/css">
            #map{ /* la carte DOIT avoir une hauteur sinon elle n'apparaît pas */
                height:400px;
            }
        </style>
        <style>
            img.accident { filter: hue-rotate(210deg); }
            img.glissement { filter: hue-rotate(120deg); }
        </style>
    <script src="js/angular.min.js"></script> 
    <script src="js/controlleurRegion.js"></script>
    <script src="js/angular-route.js"></script>
    <title>Loruki | Cloud Hosting For Everyone</title>

</head>

<%String token = (String)request.getSession().getAttribute("token_region");
Region r = (Region)request.getSession().getAttribute("region");
String id = request.getParameter("id");
 %>
<base href="/" />
<body ng-app="myapp" ng-controller="regionControl" data-ng-init="getSignalement('<%=token%>')">
    <!-- Navbar -->
    <div class="navbars">
        <div class="container flex">
            <h1 class="logo"><%=r.getDesignation()%></h1>
            <nav>
                <ul>
                    <li><a href="region/indexRegion" target="_self">Accueil</a></li>
                    <li><a href="region/listeSignalementRegion?enCours=1" target="_self">Signalements en cours</a></li>
                    <li><a href="region/listeSignalementRegion?termine=1" target="_self">Signalements terminés</a></li>
                    <li><a href="region/recherche" target="_self">Recherche</a></li>
                    <li><a href="/region/logout" target="_self">Se deconnecter</a></li>
                </ul>
            </nav>
        </div>
    </div>

   

    <!-- Docs main -->
    <section class="docs-main my-4">
        <div class="container grid">
            <div class="card bg-light p-3">
                <div id="map">
                    <!-- Ici s'affichera la carte -->
                </div>
                <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>

            </div>

            <div class="card">
                <h2>A propos du signalement</h2>
                <h3>Date : </h3>
                <p>{{signalement.dateSignalement}}</p>
                <h3>Description</h3>
                <p>{{signalement.description}}</p>
                <h3>Coordonnes</h3>
                <p>{{signalement.latitude}}, {{signalement.longitude}}</p>
                <h3>Utilisateur</h3>
                <p>{{signalement.utilisateur.username}}</p>
                <button ng-click="update('<%=token%>',{{signalement.id}})"type="button" class="btn btn-success">Marquer comme resolu</button>

            </div>
        </div>
    </section>
    
    <!-- Footer -->
    <footer class="footer bg-dark py-5">
        <div class="container grid grid-3">
            <div>
                <h1><%=r.getDesignation()%>
                </h1>
                <p>Copyright &copy; 2022</p>
            </div>
            <nav>
                <ul>
                    <li><a href="indexRegion" target="_self">Accueil</a></li>
                    <li><a href="region/listeSignalementRegion?enCours=1" target="_self">Signalements en cours</a></li>
                    <li><a href="region/listeSignalementRegion?termine=1" target="_self">Signalements terminés</a></li>
                    <li><a href="region/recherche" target="_self">Recherche</a></li>
                </ul>
            </nav>
            
        </div>
    </footer>
</body>
</html>