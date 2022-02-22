<%@page import="java.util.List"%>
<%@page import="com.projet.cloudmobile.models.*"%>
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
            #map{
                height:400px;
            }
        </style>

        <link rel="stylesheet" href="css/markcolors.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="js/angular.min.js"></script> 
    <script src="js/controlleurRegion.js"></script>
    <script src="js/angular-route.js"></script>


    <title>Region</title>
</head>

<%String token = (String)request.getSession().getAttribute("token_region");
Region r = (Region)request.getSession().getAttribute("region");
String id = request.getParameter("id");
%>
<base href="/" />
<body ng-app="myapp" ng-controller="regionControl" data-ng-init="getEtatSignalements('<%=token%>','<%=r.getId()%>')">
    <!-- Navbar -->
    <div class="navbars">
        <div class="container flex">
            <h1 class="logo"><%=r.getDesignation()%></h1>
            <nav>
                <ul>
                    <li><a href="region/indexRegion" target="_self">Accueil</a></li>
                    <li><a href="region/listeSignalementRegion?enCours=1" target="_self">Signalements en cours</a></li>
                    <li><a href="region/listeSignalementRegion?termine=1" target="_self">Signalements termines</a></li>
                    <li><a href="region/recherche" target="_self">Recherche</a></li>
                    <li><a href="region/logout" target="_self">Deconnexion</a></li>
                </ul>
            </nav>
        </div>
    </div>

    

    <!-- Sub head -->
    <section class="features-sub-head bg-light py-3">

        <div class="container grid">

        <%if(request.getParameter("enCours")!=null){%>
            <h3>Liste des signalements en cours</h3> 
            
            <table class="table">
                <thead class="thead-dark">
                  <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Description</th>
                    <th scope="col">Etat</th>
                    <th scope="col">Utilisateur</th>
                  </tr>
                </thead>
                <tbody>
                 <tr ng-repeat="x in signalement">
                     <td>{{x.signalement.id}}</td>
                     <td>{{x.signalement.description}}</td>
                     <td>{{x.signalement.etat}}</td>
                     <td>{{x.utilisateur.username}}</td>
                     <td><form action={{'region/signalement?id='+x.id}} method="post"><button type="submit"class="btn btn-primary">Voir details</button></form></td>
                 </tr>
                </tbody>
              </table>
               </div>
                  </section>

                  <section class="features-main my-2">
                      <div class="container grid grid-3">
                          <div id="map">

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
          <%}%>
          <%if(request.getParameter("termine")!=null){%>
                      <h3>Liste des signalements termines</h3>

                      <table class="table">
                                      <thead class="thead-dark">
                                        <tr>
                                          <th scope="col">Id</th>
                                          <th scope="col">Description</th>
                                          <th scope="col">Etat</th>
                                          <th scope="col">Utilisateur</th>
                                        </tr>
                                      </thead>
                                      <tbody>
                                       <tr ng-repeat="x in signalementTermine">
                                           <td>{{x.signalement.id}}</td>
                                           <td>{{x.signalement.description}}</td>
                                           <td>{{x.signalement.etat}}</td>
                                           <td>{{x.utilisateur.username}}</td>
                                           <td><form action={{'region/signalement?id='+x.id}} method="post"><button type="submit"class="btn btn-primary">Voir details</button></form></td>
                                       </tr>
                                      </tbody>
                                    </table>
                                    </div>
                                                      </section>

                                                      <section class="features-main my-2">
                                                          <div class="container grid grid-3">
                                                              <div id="map">

                                                              </div>
                                                              <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
                                                              <script type="text/javascript">
                                                                  var lat = -20.473666806709673;
                                                                  var lon = 46.54870880857691;
                                                                  var macarte2 = null;


                                                                  function initMap() {
                                                                  macarte2 = L.map('map').setView([lat, lon], 8);
                                                                  L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {

                                                                  attribution: 'données © OpenStreetMap/ODbL - rendu OSM France',
                                                                  minZoom: 5,
                                                                  maxZoom: 20
                                                                  }).addTo(macarte2);


                                                                  }
                                                                  window.onload = function(){
                                                                  initMap();
                                                                  };
                                                      </script>
                                                          </div>
                                                      </section>
                    <%}%>

    
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