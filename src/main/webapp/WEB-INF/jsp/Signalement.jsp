<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" >
   
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords"
        content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
    <meta name="description"
        content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
    <meta name="robots" content="noindex,nofollow">
    <title>Ample Admin Lite Template by WrapPixel</title>
    <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
  
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">

   <link href="css/style.min.css" rel="stylesheet">
   <script src="js/angular.min.js"></script> 
   <script src="js/controlleur.js"></script> 
   <script src="js/angular-route.js"></script>
   <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css" integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ==" crossorigin="" />
    <base href="ESSAi.html" />
</head>

<%
String token = (String)request.getSession().getAttribute("token");
%>

<body ng-app="myapp" ng-controller="testControl" ng-init="getSignalement('<%=token%>')">
   
    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>
   
    <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
        data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
     
        <header class="topbar" data-navbarbg="skin5">
            <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                <div class="navbar-header" data-logobg="skin6">
                   
                    <a class="navbar-brand" href="">
                      
                        <b class="logo-icon">
                           
                            <img src="plugins/images/back.png" style="height:25px"alt="homepage" />
                        </b>
                   
                    </a>
                    
                    <a class="nav-toggler waves-effect waves-light text-dark d-block d-md-none"
                        href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                </div>
              
                <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                    <ul class="navbar-nav d-none d-md-block d-lg-none">
                        <li class="nav-item">
                            <a class="nav-toggler nav-link waves-effect waves-light text-white"
                                href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
                        </li>
                    </ul>
                    
                    <ul class="navbar-nav ms-auto d-flex align-items-center">

                        
                        <li class=" in">
                            <form role="search" class="app-search d-none d-md-block me-3">
                                <input type="text" placeholder="Recherche" class="form-control mt-0">
                                <a href="" class="active">
                                    <i class="fa fa-search"></i>
                                </a>
                            </form>
                        </li>
                       
                    </ul>
                </div>
            </nav>
        </header>
       
        <aside class="left-sidebar" data-sidebarbg="skin6">
           
            <div class="scroll-sidebar">
               
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li class="sidebar-item pt-2">

                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/NouveauxSignalements"
                            aria-expanded="false">
                                <i class="fas fa-bug" aria-hidden="true"></i>
                                <span class="hide-menu">Nouveaux Signalements</span>
                            </a>

                            <li class="sidebar-item">
                            <form action="/Tables?region=1" method="post">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/Tables?region=1"
                                    aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Regions</span>
                                </a>
                            </form>
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/Tables?utilisateur=1"
                                    aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Utilisateur</span>
                                </a>
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/Tables?typeSignalement=1"
                                    aria-expanded="false">
                                    <i class="fa fa-table" aria-hidden="true"></i>
                                    <span class="hide-menu">Type de signalement</span>
                                </a>
                            </li>
                        <li class="sidebar-item">
                            <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/stat"
                            aria-expanded="false">
                                <i class="fa fa-table" aria-hidden="true"></i>
                                <span class="hide-menu">Statistiques</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                             <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/listeSignalement"
                             aria-expanded="false">
                                <i class="fa fa-table" aria-hidden="true"></i>
                                <span class="hide-menu">Liste des signalements</span>
                             </a>
                        </li>
                    </ul>

                </nav>
              
            </div>
           
        </aside>
       
        <div class="page-wrapper" style="min-height: 250px;">
          
            <div class="page-breadcrumb bg-white">
                <div class="row align-items-center">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title"> Titre</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <div class="d-md-flex">
                            <ol class="breadcrumb ms-auto">
                               
                            </ol>
                            <a href="/logout"
                                class="btn btn-danger  d-none d-md-block pull-right ms-3 hidden-xs hidden-sm waves-effect waves-light text-white">Se deconnecter</a>
                        </div>
                    </div>
                </div>
              
            </div>
            
            <div class="container-fluid">
                <style type="text/css">
                    #map{ /* la carte DOIT avoir une hauteur sinon elle n'apparaît pas */
                        height:600px;
                    }
                </style>
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-4 col-xlg-3 col-md-12">
                        <div class="white-box">
                            <div id="map">

                            </div>
                        </div>
                    </div>
                    <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
                        <script type="text/javascript">

                            var lat = -18.766947;
                            var lon = 46.869107;
                            var macarte = null;

                            function initMap() {

                                macarte = L.map('map').setView([lat, lon], 6);

                                L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {

                                    
                                    minZoom: 1,
                                    maxZoom: 20
                                }).addTo(macarte);
                            }
                                window.onload = function(){

                            initMap(); 
                                };
                        </script>

                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-8 col-xlg-9 col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <form class="form-horizontal form-material">
                                    <div class="form-group mb-4">
                                        <label class="col-md-12 p-0">Date du signalement</label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <p>{{signalement.dateSignalement}}</p></div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="col-md-12 p-0">Description</label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <p>{{signalement.description}}</p></div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="col-md-12 p-0">Coordonnees</label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <p>{{signalement.longitude}} || {{signalement.latitude}}</p></div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="col-md-12 p-0">Type</label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <p>{{signalement.type.designation}}</p></div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="col-md-12 p-0">Utilisateur</label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <p>{{signalement.utilisateur.username}}</p></div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="col-md-12 p-0">Region trouvee selon les coordonnees :</label>
                                        <div class="col-md-12 border-bottom p-0">
                                            <p>{{region}}</p></div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <label class="col-sm-12">Selectionner la region correspondante :</label>
                                        <div class="col-sm-12 border-bottom">
                                            <select class="form-select shadow-none p-0 border-0 form-control-line" ng-model="idregion">
                                                <option ng-repeat="region in lregions" value="{{region.id}}">{{region.designation}}</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group mb-4">
                                        <div class="col-sm-12">
                                            <button class="btn btn-success" ng-click="attribuerRegion()">Attribuer la region</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                </div>
               
            </div>
            
        </div>
       
    </div>
    
    <script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
    
    <script src="bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/app-style-switcher.js"></script>
    
    <script src="js/waves.js"></script>
   
    <script src="js/sidebarmenu.js"></script>
    
    <script src="js/custom.js"></script>
</body>

</html>