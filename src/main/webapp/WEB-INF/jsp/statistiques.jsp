<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
   
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords"
        content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">
    <meta name="description"
        content="Ample Admin Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework">
    <meta name="robots" content="noindex,nofollow">
    <title>Back Office</title>
    <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />
  
    <link rel="icon" type="image/png" sizes="16x16" href="plugins/images/favicon.png">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="js/angular-route.js"></script>
    <script type="text/javascript">
        var appname = angular.module('myApp', []);

        appname.controller('statControl', ['$scope','$http',
        function($scope,$http) {

          $scope.afficherRegion=function(){
            $http.get('/region/getRegions').then(function (response) {
              $scope.lregions=response.data;
            });
          }

            $scope.afficherRegion();

          $scope.loadData=function(){
                $http.get('/signalement/statEtat1').then(function (response) {
                $scope.nombre1=response.data;
                $scope.problemes1="Route abime";
             });
          }
            $scope.loadData();

           $scope.loadData2=function(){
                  $http.get('/signalement/statEtat2').then(function (response) {
                  $scope.nombre2=response.data;
                 $scope.problemes2="Accident de la route";
             });
           }
            $scope.loadData2();
           $scope.loadData3=function(){
                $http.get('/signalement/statEtat3').then(function (response) {
                $scope.nombre3=response.data;
                $scope.problemes3="Ordures";
             });
           }
            $scope.loadData3();
           $scope.loadDat=function(){
                           $http.get('/signalement/statEtat1N').then(function (response) {
                           $scope.nouveau1=response.data;

                        });
                     }
        $scope.loadDat();
                      $scope.loadDat2=function(){
                             $http.get('/signalement/statEtat2N').then(function (response) {
                             $scope.nouveau2=response.data;

                        });
                      }
        $scope.loadDat2()
                      $scope.loadDat3=function(){
                           $http.get('/signalement/statEtat3N').then(function (response) {
                           $scope.nouveau3=response.data;

                        });
                      }
        $scope.loadDat3();
                    $scope.loadDt=function(){
                           $http.get('/signalement/statEtat1E').then(function (response) {
                           $scope.enCours1=response.data;

                        });
                     }
        $scope.loadDt();
                      $scope.loadDt2=function(){
                             $http.get('/signalement/statEtat2E').then(function (response) {
                             $scope.enCours2=response.data;

                        });
                      }
        $scope.loadDt2();
                      $scope.loadDt3=function(){
                           $http.get('/signalement/statEtat3E').then(function (response) {
                           $scope.enCours3=response.data;

                        });
                      }
        $scope.loadDt3();
                      $scope.getStatistics=function(){
                             $http.get('/signalementregion/stat?id='+$scope.idregion+'').then(function (response) {
                             $scope.nbr=response.data;

                             });
                      }


$scope.getGraph=function(val1,val2,val3){
                const ctx = document.getElementById('graph1').getContext('2d');
                var data = {
                    labels: ['Route abimé','Accident de la route','Ordures'],
                    datasets: [
                        {
                        label: 'Statistiques des cas de problemes',
                        backgroundColor: '#000000',
                        data: [val1,val2,val3]
                        }
                    ]
                }
                var options = {
                    responsive: true,
                }


                var config = {
                    type: 'line',
                    data: data,
                    options: options
                }
                var graph1 = new Chart(ctx, config)
}

        $scope.getGraph($scope.number1,$scope.number2,$scope.number3);


        }


        ]);

    </script>
   <link href="css/style.min.css" rel="stylesheet">
   <base href="ESSAi.html" />
</head>

<body ng-app="myApp" ng-controller="statControl">
   
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
                        <h4 class="page-title"> Nouveaux Signalements</h4>
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
              
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="white-box">
                                        
                                        <h3 class="box-title">Statistique global  :</h3>
                                                <p><canvas id="graph1"></canvas></p>
                                                <p class="text-muted"> Dans les {{problemes1}} il y a {{nombre1}} problemes dont {{enCours1}} qui sont En cours de traitement et {{nouveau1}} nouveaux signalements</p>
                                                <p class="text-muted"> Dans les {{problemes2}} il y a {{nombre2}} problemes dont {{enCours2}} qui sont En cours de traitement et {{nouveau2}} nouveaux signalements</p>
                                                <p class="text-muted"> Dans les {{problemes3}} il y a {{nombre3}} problemes dont {{enCours3}} qui sont En cours de traitement et {{nouveau3}} nouveaux signalements</p>
                                        <div class="table-responsive">
                                            <table class="table text-nowrap">
                                                <thead>
                                                    <tr>
                                                        <th class="border-top-0">Problemes</th>
                                                        <th class="border-top-0">Nombre de signalement</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>{{problemes1}}</td>
                                                        <td>{{nombre1}}</td>
                                                    </tr>
                                                   <tr>
                                                    <td>{{problemes2}}</td>
                                                    <td>{{nombre2}}</td>
                                                   </tr>
                                                    <tr>
                                                     <td>{{problemes3}}</td>
                                                       <td>{{nombre3}}</td>
                                                      </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="form-group mb-4">
                                                  <label class="col-sm-12">Statistique specifique sur une region  :</label>
                                                     <div class="col-sm-12 border-bottom">
                                                         <select class="form-select shadow-none p-0 border-0 form-control-line" ng-model="idregion">
                                                               <option ng-repeat="region in lregions" value="{{region.id}}">{{region.description}}</option>
                                                        </select>
                                                     </div>
                                                </div>
                                                <div class="form-group mb-4">
                                                   <div class="col-sm-12">
                                                      <button class="btn btn-success" ng-click="getStatistics()">valider</button>
                                                   </div>
                                                </div>

                                                <p>Dans ce region il y a {{ nbr }} signalement effectuée.</p>
                                    </div>
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
<script>

</script>