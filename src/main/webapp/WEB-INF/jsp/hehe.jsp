<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>statistique</title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

        <script type="text/javascript">
        var appname = angular.module('Myapp', []);

        appname.controller('hehe', ['$scope','$http',
        function($scope,$http) {

        $scope.getNouveau=function(){        //GRAPHIQUE TYPE_SIGNALEMENT
                              $http.get('/signalement/statistique/region/nouveau').then(function (response) {
                              json = response.data;
                                var chartjsData = [];
                                      for (var i = 0; i < json.length; i++) {
                                         chartjsData.push(json[i].total);
                                      }
                                return chartjsData;

                              });
                            }

        $scope.getTermine=function(){        //GRAPHIQUE TYPE_SIGNALEMENT
                                      $http.get('/signalement/statistique/region/termine').then(function (response) {
                                      json = response.data;
                                        var chartjsData = [];
                                              for (var i = 0; i < json.length; i++) {
                                                 chartjsData.push(json[i].total);
                                              }
                                        return chartjsData;

                                      });
                                    }


          $scope.getChartReg=function(){        //GRAPHIQUE TYPE_SIGNALEMENT
            $http.get('/signalement/statistique/region/encours').then(function (response) {
            json = response.data;
            var chartjsData2 = $scope.getNouveau();
            var chartjsData3 = $scope.getTermine;
              var chartjsData = [];
                    for (var i = 0; i < json.length; i++) {
                       chartjsData.push(json[i].total);
                    }

                    var chartjsLabels = [];
                    for (var i = 0; i < json.length; i++) {
                       chartjsLabels.push(json[i].type);
                    }

              const ctx = document.getElementById('graphRegion').getContext('2d');
                              var data = {
                                  labels: chartjsLabels,
                                  datasets: [
                                      {
                                      label: 'En cours',
                                      borderColor: '#d8f4ff',
                                      data: chartjsData,
                                      fill: true,
                                      tension: 0.1
                                      },
                                      {
                                       label: 'Nouveau',
                                       borderColor: "#110f48",
                                       data: chartjsData2,
                                       fill: true,
                                       tension: 0.1
                                      },
                                      {
                                       label: 'Termine',
                                       borderColor: "#f8f8fa",
                                       data: chartjsData3,
                                       fill: true,
                                       tension: 0.1
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
                              var graphRegion = new Chart(ctx, config)

            });
          }
          $scope.getChartReg();






          //Chart graphRegion en line
          $scope.getChart=function(){
                      $http.get('/signalement/statistique/type').then(function (response) {

                      json = response.data;


                        var chartjsData = [];
                              for (var i = 0; i < json.length; i++) {
                                 chartjsData.push(json[i].total);
                              }



                        var chartjsLabels = [];
                            for (var i = 0; i < json.length; i++) {
                                 chartjsLabels.push(json[i].region);
                              }

                        const ctx = document.getElementById('graph1').getContext('2d');
                                        var data = {
                                            labels: chartjsLabels,
                                            datasets: [
                                                {
                                                label: 'Statistiques des Problemes a Madagascar',
                                                backgroundColor: '#d8f4ff',
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
                    $scope.getChart();

                    //GRAPHIQUE Utilisateur
                    $scope.getGraphUtil=function(){
                                $http.get('/signalement/statistique/utilisateur').then(function (response) {
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
                              $scope.getGraphUtil();


        }

        ]);

        </script>
    </head>

    <body ng-app="Myapp" ng-controller="hehe">
                <p>Statistiques par region et problemes: </p>
                <p><canvas id="graphRegion" width="10" height="5"></canvas></p>

                <p>Statistiques des problemes et type a Madagascar: </p>
                <p><canvas id="graph1" width="10" height="5"></canvas></p>

                <p>Statistiques the top 10 best user: </p>
                <p><canvas id="graphUser" width="10" height="5"></canvas></p>
    </body>
</html>
<script type="text/javascript">



</script>