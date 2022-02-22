<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String token = (String)request.getSession().getAttribute("token");
%>
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
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
    <script type="text/javascript">

var appname = angular.module('myApp', []);

appname.controller('statControl', ['$scope','$http',
function($scope,$http) {


$scope.loadData=function($args){
      $http.get('/signalement/statEtat1',{
            headers : {'token':$args}
        }).then(function (response) {
      $scope.nombre1=response.data;
      $scope.problemes1="Route abime";
   });
}


 $scope.loadData2=function($args){
        $http.get('/signalement/statEtat2',{
            headers : {'token':$args}
        }).then(function (response) {
        $scope.nombre2=response.data;
       $scope.problemes2="Accident de la route";
   });
 }

 $scope.loadData3=function($args){
      $http.get('/signalement/statEtat3',{
            headers : {'token':$args}
        }).then(function (response) {
      $scope.nombre3=response.data;
      $scope.problemes3="Ordures";
   });
 }

 $scope.loadDat=function($args){
      $http.get('/signalement/statEtat1N',{
            headers : {'token':$args}
        }).then(function (response) {
      $scope.nouveau1=response.data;

      });
 }

 $scope.loadDat2=function($args){
      $http.get('/signalement/statEtat2N',{
            headers : {'token':$args}
        }).then(function (response) {
      $scope.nouveau2=response.data;

      });
 }

 $scope.loadDat3=function($args){
      $http.get('/signalement/statEtat3N',{
            headers : {'token':$args}
        }).then(function (response) {
      $scope.nouveau3=response.data;

      });
 }

 $scope.loadDt=function($args){
        $http.get('/signalement/statEtat1E',{
            headers : {'token':$args}
        }).then(function (response) {
        $scope.enCours1=response.data;

     });
 }

   $scope.loadDt2=function($args){
          $http.get('/signalement/statEtat2E',{
                headers : {'token':$args}
          }).then(function (response) {
          $scope.enCours2=response.data;

     });
 }

   $scope.loadDt3=function($args){
        $http.get('/signalement/statEtat3E',{
            headers : {'token':$args}
        }).then(function (response) {
        $scope.enCours3=response.data;

     });
 }

//GRAPHIQUE PAR TYPE

$scope.getChart=function($args){
   $http.get('/signalement/statistique/type',{
      headers : {'token':$args}
   }).then(function (response) {
    json = response.data;
    var chartjsData = [];
    for (var i = 0; i < json.length; i++) {
        chartjsData.push(json[i].total);
    }

    var chartjsLabels = [];
    for (var i = 0; i < json.length; i++) {
        chartjsLabels.push(json[i].type);
    }
    const ctx = document.getElementById('graph1').getContext('2d');
        var data = {
                    labels: chartjsLabels,
                    datasets: [
                    {
                      label: 'Statistiques des Problemes a Madagascar',
                      backgroundColor: ['#d8f4ff',"#110f48","#f8f8fa"],
                      data: chartjsData
                    }
                    ]
        }

        var options = {
                       responsive: true,
        }


        var config = {
                       type: 'pie',
                       data: data,
                       options: options
        }
        var graph1 = new Chart(ctx, config)

});

}


//GRAPHIQUE Utilisateur

$scope.getGraphUtil=function($args){
            $http.get('/signalement/statistique/utilisateur',{
                headers : {'token':$args}
            }).then(function (response) {
            json = response.data;
              var chartjsData = [];
                    for (var i = 0; i < json.length; i++) {
                       chartjsData.push(json[i].total);
                    }

                    var chartjsLabels = [];
                    for (var i = 0; i < json.length; i++) {
                       chartjsLabels.push(json[i].name);
                    }

              const ctx = document.getElementById('graphUser').getContext('2d');
                              var data = {
                                  labels: chartjsLabels,
                                  datasets: [
                                      {
                                      label: 'Utilisateur les plus actifs',
                                      backgroundColor: "#f8f8fa",
                                      data: chartjsData
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
                              var graphUser = new Chart(ctx, config)

            });
          }




//GRAPHIQUE Nouveau region


 $scope.regionNouveau=function($args){
             $http.get('/signalement/statistique/region/nouveau',{
                 headers : {'token':$args}
             }).then(function (response) {
             json = response.data;
               var chartjsData = [];
                     for (var i = 0; i < json.length; i++) {
                        chartjsData.push(json[i].total);
                     }

                     var chartjsLabels = [];
                     for (var i = 0; i < json.length; i++) {
                        chartjsLabels.push(json[i].region);
                     }

               const ctx = document.getElementById('graphRegionNouv').getContext('2d');
                               var data = {
                                   labels: chartjsLabels,
                                   datasets: [
                                       {
                                       label: 'Evolution des problemes reporter sans traitement',
                                       backgroundColor: "#110f48",
                                       data: chartjsData
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
                               var graphRegionNouv = new Chart(ctx, config)

             });
           }


 //GRAPHIQUE EN cours region

 $scope.regionEnCours=function($args){
        $http.get('/signalement/statistique/region/encours',{
            headers : {'token':$args}
        }).then(function (response) {
        json = response.data;
          var chartjsData = [];
                for (var i = 0; i < json.length; i++) {
                   chartjsData.push(json[i].total);
                }

                var chartjsLabels = [];
                for (var i = 0; i < json.length; i++) {
                   chartjsLabels.push(json[i].region);
                }

          const ctx = document.getElementById('graphRegionEncours').getContext('2d');
                          var data = {
                              labels: chartjsLabels,
                              datasets: [
                                  {
                                  label: 'Evolution des problemes reporter en cours traitement',
                                  backgroundColor: "#f8f8fa",
                                  data: chartjsData
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
                          var graphRegionEncours = new Chart(ctx, config)

        });
      }


//GRAPHIQUE Termine region

 $scope.regionTermine=function($args){
        $http.get('/signalement/statistique/region/termine',{
            headers : {'token':$args}
        }).then(function (response) {
        json = response.data;
          var chartjsData = [];
                for (var i = 0; i < json.length; i++) {
                   chartjsData.push(json[i].total);
                }

                var chartjsLabels = [];
                for (var i = 0; i < json.length; i++) {
                   chartjsLabels.push(json[i].region);
                }

          const ctx = document.getElementById('graphRegionTermine').getContext('2d');
                          var data = {
                              labels: chartjsLabels,
                              datasets: [
                                  {
                                  label: 'Evolution des problemes reporter qui sont terminés',
                                  backgroundColor: "#d8f4ff",
                                  data: chartjsData
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
                          var graphRegionTermine = new Chart(ctx, config)

        });
      }











    //fin

 }]);

    </script>
   <link href="css/style.min.css" rel="stylesheet">
   <base href="ESSAi.html" />
</head>

<body ng-app="myApp" ng-controller="statControl" data-ng-init="regionTermine('<%=token%>');getChart('<%=token%>');getGraphUtil('<%=token%>');regionNouveau('<%=token%>');regionEnCours('<%=token%>');loadDt3('<%=token%>');loadData('<%=token%>');loadData2('<%=token%>');loadData3('<%=token%>');loadDat('<%=token%>');loadDat2('<%=token%>');loadDat3('<%=token%>');loadDt('<%=token%>');loadDt2('<%=token%>');">
   
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
                                        
                                        <h3 class="box-title">STATISTIQUE GLOBAL EN GRAPHIQUE :</h3>
                                                <p>Statistiques des problemes selon leurs type à Madagascar: </p>

                                                <p><canvas id="graph1" width="10" height="5"></canvas></p>

                                                <p><span style="color: red">Remarques avec le graphique en pie :</span></p>

                                                <p class="text-muted"> Dans les {{problemes1}} il y a {{nombre1}} problemes dont {{enCours1}} qui sont En cours de traitement et {{nouveau1}} nouveaux signalements</p>
                                                <p class="text-muted"> Dans les {{problemes2}} il y a {{nombre2}} problemes dont {{enCours2}} qui sont En cours de traitement et {{nouveau2}} nouveaux signalements</p>
                                                <p class="text-muted"> Dans les {{problemes3}} il y a {{nombre3}} problemes dont {{enCours3}} qui sont En cours de traitement et {{nouveau3}} nouveaux signalements</p>

                                                <p>Statistiques par region et des problemes Nouveaux: </p>
                                                <p><canvas id="graphRegionNouv" width="10" height="5"></canvas></p>

                                                <p>Statistiques par region et des problemes En cours: </p>
                                                <p><canvas id="graphRegionEncours" width="10" height="5"></canvas></p>

                                                <p>Statistiques par region et des problemes termine: </p>
                                                <p><canvas id="graphRegionTermine" width="10" height="5"></canvas></p>

                                                <p>Statistiques the top 10 best user: </p>
                                                <p><canvas id="graphUser" width="10" height="5"></canvas></p>



                                        <div class="form-group mb-4">

                                        </div>


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