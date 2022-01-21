<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords"
        content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
    <meta name="description"
        content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
    <meta name="robots" content="noindex,nofollow">
    <title>Ample Admin Lite Template by WrapPixel</title>
    <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
    <script src="js/angular.min.js"></script> 
    <script src="js/angular-route.js"></script>
    <script src="js/controlleur.js"></script> 
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">

   <link href="css/style.min.css" rel="stylesheet">
    <base href="tables.jsp" />
</head>

<body ng-app="myapp" ng-controller="tablesControl">
   
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
                                                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="/Tables"
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
                                        </ul>

                </nav>
              
            </div>
           
        </aside>
       
        <div class="page-wrapper" style="min-height: 250px;">
          
            <div class="page-breadcrumb bg-white">
                <div class="row align-items-center">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title"> Tables </h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <div class="d-md-flex">
                            <ol class="breadcrumb ms-auto">
                               
                            </ol>
                            <a href="https://www.wrappixel.com/templates/ampleadmin/" target="_blank"
                                class="btn btn-danger  d-none d-md-block pull-right ms-3 hidden-xs hidden-sm waves-effect waves-light text-white">Se deconnecter</a>
                        </div>
                    </div>
                </div>
              
            </div>
            
            <div class="container-fluid">
              
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-sm-12">
                                <% if(request.getParameter("region")!=null){%>
                                    <div class="white-box">
                                        <h3 class="box-title">Regions :</h3>
                                        <div class="table-responsive">
                                            <table class="table text-nowrap">
                                                <thead>
                                                    <tr>
                                                        <th class="border-top-0">Id</th>
                                                        <th class="border-top-0">Designation</th>
                                                        <th class="border-top-0">Identifiant</th>
                                                        <th class="border-top-0">Mot de passe</th>
                                                        
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr ng-repeat="x in regions">
                                                        <td>{{x.id}}</td>
                                                        <td>{{x.designation}}</td>
                                                        <td>{{x.username}}</td>
                                                        <td>{{x.password}}</td>
                                                        <td><form action={{'/Modifier?region=1&id='+x.id}} method="post"><button type="submit"class="btn btn-primary">Modifier</button></form></td>
                                                        <td><form action={{'Supprimer?id='+x.id}} method="post"><button type="submit"class="btn btn-danger">Supprimer</button></form></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <%}%>
                                    <% if(request.getParameter("utilisateur")!=null){%>
                                    <div class="white-box">
                                        <h3 class="box-title">Utilisateur :</h3>
                                        <div class="table-responsive">
                                            <table class="table text-nowrap">
                                                <thead>
                                                    <tr>
                                                        <th class="border-top-0">Id</th>
                                                        <th class="border-top-0">Utilisateur</th>
                                                        <th class="border-top-0">Mot de passe</th>
                                                        <th class="border-top-0">Email</th>
                                                        <th class="border-top-0">Date de naissance</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr ng-repeat="x in utilisateurs">
                                                        <td>{{x.id}}</td>
                                                        <td>{{x.username}}</td>
                                                        <td>{{x.password}}</td>
                                                        <td>{{x.email}}</td>
                                                        <td>{{x.dtn}}</td>
                                                        <td><form action="ESSAI2.html?id={{x.id}}" method="post"><button type="submit"class="btn btn-primary">Attribuer</button></form></td>
                                                        <td><button type="button" class="btn btn-danger">Supprimer</button></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <%}%>
                                    <% if(request.getParameter("typeSignalement")!=null){%>
                                    <div class="white-box">
                                        <h3 class="box-title">Type de signalement :</h3>
                                        <div class="table-responsive">
                                            <table class="table text-nowrap">
                                                <thead>
                                                    <tr>
                                                        <th class="border-top-0">Id</th>
                                                        <th class="border-top-0">Designation</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr ng-repeat="x in typeSignalement">
                                                        <td>{{x.id}}</td>
                                                        <td>{{x.designation}}</td>
                                                        <td><form action="ESSAI2.html?id={{x.id}}" method="post"><button type="submit"class="btn btn-primary">Attribuer</button></form></td>
                                                        <td><button type="button" class="btn btn-danger">Supprimer</button></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
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