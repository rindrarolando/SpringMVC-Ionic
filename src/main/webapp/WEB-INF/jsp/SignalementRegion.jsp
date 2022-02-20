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
    <script src="js/controlleur.js"></script> 
    <script src="js/angular-route.js"></script>
    <title>Loruki | Cloud Hosting For Everyone</title>
</head>
<body>
    <!-- Navbar -->
    <div class="navbars">
        <div class="container flex">
            <h1 class="logo">Region</h1>
            <nav>
                <ul>
                    <li><a href="index.html">Accueil</a></li>
                    <li><a href="features.html">Signalements en cours</a></li>
                    <li><a href="docs.html">Signalements terminés</a></li>
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
                <script type="text/javascript"> 
                    var lat = -20.473666806709673;
                    var lon = 46.54870880857691;
                    var macarte = null;
                    var villes = {
                    '<a href="lol.php">ok</a>': { "lat": -20.1, "lon": 46.4 }
                    };
    
                    function initMap() {
                    macarte = L.map('map').setView([lat, lon], 8);
                    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            
                    attribution: 'données © OpenStreetMap/ODbL - rendu OSM France',
                    minZoom: 5,
                    maxZoom: 20
                    }).addTo(macarte);
                    for (ville in villes) {
                            var marker = L.marker([villes[ville].lat, villes[ville].lon]).addTo(macarte);
                            marker._icon.classList.add("accident");
                            marker.bindPopup(ville);
                        }       
                                
                    }
                    window.onload = function(){
                    initMap(); 
                    };
        </script>
            </div>

            <div class="card">
                <h2>A propos du signalement</h2>
                


                <h3>Date</h3>
                

                <h3>Description</h3>
                

                <h3>Coordonnes</h3>
                
                <h3>Utilisateur</h3>

                <h3>Region</h3>

                <button type="button" class="btn btn-success">Marquer comme resolu</button>

            </div>
        </div>
    </section>
    
    <!-- Footer -->
    <footer class="footer bg-dark py-5">
        <div class="container grid grid-3">
            <div>
                <h1>Region
                </h1>
                <p>Copyright &copy; 2022</p>
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