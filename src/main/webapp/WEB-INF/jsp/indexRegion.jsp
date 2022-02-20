<%@page import="java.util.List"%>
<%@page import="com.projet.cloudmobile.models.Region"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <link rel="stylesheet" href="css/utilities.css">
    <link rel="stylesheet" href="css/stylegrid.css">
    <link rel="stylesheet" href="css/markcolors.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==" crossorigin="" />
        <style type="text/css">
            #map{
                height:400px;
            }
        </style>
    <script src="js/angular.min.js"></script> 
    <script src="js/controlleurRegion.js"></script>
    <script src="js/angular-route.js"></script>
    
     <title>Region</title>
</head>
<%String token = (String)request.getSession().getAttribute("token_region");
Region r = (Region)request.getSession().getAttribute("region");
String id = request.getParameter("id"); out.println(id);
 %>
<base href="/" />
<body ng-app="myapp" ng-controller="regionControl" data-ng-init="getSignalements('<%=token%>','<%=r.getId()%>')">
    <div class="navbars">
        <div class="container flex">
            <h1 class="logo"><%=r.getDesignation()%></h1>
            <nav>
                <ul>
                    <li><a href="index.html" target="_self">Accueil</a></li>
                    <li><a href="features.html" target="_self">Signalements en cours</a></li>
                    <li><a href="docs.html" target="_self">Signalements terminés</a></li>
                    <li><a href="region/logout" target="_self">Se deconnecter</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <section class="showcase">
        <div class="container grid">
            

            <div class="showcase-form card">
                <h2>Recherche filtrée</h2>
                 <form>
                    <div class="form-group">
                        <input type="date" class="form-control">
                      </div>
                      <select class="form-control">
                        <option>Default select</option>
                        <option>Default select</option>
                        <option>Default select</option>
                      </select>
                      <select class="form-control">
                        <option>Default select</option>
                      </select>
                    
                    <input type="submit" value="Rechercher" class="btn btn-primary">
                </form>
            </div>
        </div>
    </section>

    <!-- Stats -->
    <section class="stats">
        <div class="container">
            <div id="map">
                <!-- Ici s'affichera la carte -->
            </div>
            <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
            <script type="text/javascript"> 
                var lat = -20.473666806709673;
                var lon = 46.54870880857691;
                var macarte = null;

                function initMap() {
                macarte = L.map('map').setView([lat, lon], 8);
                L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        
                attribution: 'données © OpenStreetMap/ODbL - rendu OSM France',
                minZoom: 5,
                maxZoom: 20
                }).addTo(macarte);


                }
                window.onload = function(){
                initMap(); 
                };
    </script>
        </div>
    </section>

   

    

    <!-- Footer -->
    <footer class="footer bg-dark py-5">
        <div class="container grid grid-3">
            <div>
                <h1>Region
                </h1>
                <p>Copyright &copy; 2020</p>
            </div>
            <nav>
                <ul>
                    <li><a href="index.html">Home</a></li>
                    <li><a href="features.html">Features</a></li>
                    <li><a href="docs.html">Docs</a></li>
                </ul>
            </nav>

        </div>
    </footer>
</body>
</html>
