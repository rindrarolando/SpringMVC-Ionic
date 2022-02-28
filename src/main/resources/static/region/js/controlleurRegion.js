var appname = angular.module('myapp', ['ngRoute']);

appname.config(['$locationProvider',function($locationProvider) {
  $locationProvider.html5Mode(true);
}]);



appname.controller('regionControl', ['$scope','$http','$location','$window','$filter',
function($scope,$http,$location,$window,$filter) {
    var lat = -20.473666806709673;
    var lon = 46.54870880857691;
  $scope.getSignalements=function($args,$id){

    $http.get('https://spring-ion.herokuapp.com/signalementregion/getSignalementByRegion?id='+$id+'', {
        headers : {'token':$args}
    }).then(function (response) {

      $scope.signalement=response.data;

      for(ville in $scope.signalement){

        var marker = L.marker([$scope.signalement[ville].signalement.latitude, $scope.signalement[ville].signalement.longitude]).addTo(macarte);
        if($scope.signalement[ville].signalement.type.id==1)marker._icon.classList.add("route");
        else if($scope.signalement[ville].signalement.type.id==2)marker._icon.classList.add("accident");
        else if($scope.signalement[ville].signalement.type.id==3)marker._icon.classList.add("ordures");
        else if($scope.signalement[ville].signalement.type.id==4)marker._icon.classList.add("glissement");
        else if($scope.signalement[ville].signalement.type.id==5)marker._icon.classList.add("inondation");
        else if($scope.signalement[ville].signalement.type.id==6)marker._icon.classList.add("coupure");
        marker.bindPopup('<a href="region/signalement?id='+$scope.signalement[ville].signalement.id+'" target="_self">'+$scope.signalement[ville].signalement.description+'</a>');
      }
    });

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

  }

    $scope.getSignalementsEnCours=function($args,$id){

          $http.get('https://spring-ion.herokuapp.com/region/getSignalementEnCours?id='+$id+'', {
              headers : {'token':$args}
          }).then(function (response) {


            $scope.signalement=response.data;
            console.log($scope.signalement)
            for(ville in $scope.signalement){

              var marker = L.marker([$scope.signalement[ville].signalement.latitude, $scope.signalement[ville].signalement.longitude]).addTo(macarte);
              if($scope.signalement[ville].signalement.type.id==1)marker._icon.classList.add("route");
              else if($scope.signalement[ville].signalement.type.id==2)marker._icon.classList.add("accident");
              else if($scope.signalement[ville].signalement.type.id==3)marker._icon.classList.add("ordures");
              else if($scope.signalement[ville].signalement.type.id==4)marker._icon.classList.add("glissement");
              else if($scope.signalement[ville].signalement.type.id==5)marker._icon.classList.add("inondation");
              else if($scope.signalement[ville].signalement.type.id==6)marker._icon.classList.add("coupure");
              marker.bindPopup('<a href="region/signalement?id='+$scope.signalement[ville].signalement.id+'" target="_self">'+$scope.signalement[ville].signalement.description+'</a>');
            }
          });

        }

        $scope.getSignalementsTermines=function($args,$id){

                  $http.get('https://spring-ion.herokuapp.com/region/getSignalementsTermines?id='+$id+'', {
                      headers : {'token':$args}
                  }).then(function (response) {


                    $scope.signalementTermine=response.data;
                    console.log($scope.signalementTermine)
                    for(ville in $scope.signalementTermine){

                      var marker = L.marker([$scope.signalementTermine[ville].signalement.latitude, $scope.signalementTermine[ville].signalement.longitude]).addTo(macarte2);
                      if($scope.signalementTermine[ville].signalement.type.id==1)marker._icon.classList.add("route");
                      else if($scope.signalementTermine[ville].signalement.type.id==2)marker._icon.classList.add("accident");
                      else if($scope.signalementTermine[ville].signalement.type.id==3)marker._icon.classList.add("ordures");
                      else if($scope.signalementTermine[ville].signalement.type.id==4)marker._icon.classList.add("glissement");
                      else if($scope.signalementTermine[ville].signalement.type.id==5)marker._icon.classList.add("inondation");
                      else if($scope.signalementTermine[ville].signalement.type.id==6)marker._icon.classList.add("coupure");
                      marker.bindPopup('<a href="region/signalement?id='+$scope.signalementTermine[ville].signalement.id+'" target="_self">'+$scope.signalementTermine[ville].signalement.description+'</a>');
                    }
                  });

                }

        $scope.getEtatSignalements=function($args,$id){
            $scope.getSignalementsEnCours($args,$id);
            $scope.getSignalementsTermines($args,$id);
        }

  $scope.attribuerRegion=function(){
  console.log("id signalement ="+$location.search().id);
  console.log("id region ="+$scope.idregion);
  $http.get('https://spring-ion.herokuapp.com/signalement/attribuer?idSignalement='+$location.search().id+"&idRegion="+$scope.idregion).then(function (response) {
      });
      $window.location.href = '/NouveauxSignalements';
  }

    $scope.test=function($args,$id){
        $scope.formattedDate =   $filter('date')($scope.date, "dd-MM-yyyy");

        $date = $scope.formattedDate;
        $etat = $scope.etat;
        $type = $scope.type;
        console.log($date);
        console.log($etat);
        $url = 'https://spring-ion.herokuapp.com/region/recherche?id='+$id+'&date='+$date+'&type='+$type+'&etat='+$etat+'';
        console.log($url);
        $http.get('https://spring-ion.herokuapp.com/region/rechercheSignalement?id='+$id+'&date='+$date+'&type='+$type+'&etat='+$etat+'', {
                              headers : {'token':$args}
                          }).then(function (response) {
                            $scope.signalementTermine=response.data;

                            for(ville in $scope.signalementTermine){

                              var marker = L.marker([$scope.signalementTermine[ville].signalement.latitude, $scope.signalementTermine[ville].signalement.longitude]).addTo(macarte);
                              if($scope.signalementTermine[ville].signalement.type.id==1)marker._icon.classList.add("route");
                              else if($scope.signalementTermine[ville].signalement.type.id==2)marker._icon.classList.add("accident");
                              else if($scope.signalementTermine[ville].signalement.type.id==3)marker._icon.classList.add("ordures");
                              else if($scope.signalementTermine[ville].signalement.type.id==4)marker._icon.classList.add("glissement");
                              else if($scope.signalementTermine[ville].signalement.type.id==5)marker._icon.classList.add("inondation");
                              else if($scope.signalementTermine[ville].signalement.type.id==6)marker._icon.classList.add("coupure");
                              marker.bindPopup('<a href="region/signalement?id='+$scope.signalementTermine[ville].signalement.id+'" target="_self">'+$scope.signalementTermine[ville].signalement.description+'</a>');
                            }
                          });
                        }
        $scope.insertNotification = function($args){

            $http.get('https://spring-ion.herokuapp.com/region/getSignalement?id='+$location.search().id+'',{
                headers : {'token':$args}
            }).then(function (response) {
                $id = response.data.id;
                $dat = new Date();
                $date =   $filter('date')($dat, "dd-MM-yyyy");
                $description = response.data.description;
                $idUser = response.data.utilisateur.id;

                notification = {
                    "id":$id,
                    "iduser":$idUser,
                    "description":$description,
                    "date":$date
                }

                $http({
                    method: 'POST',
                    url: 'https://spring-ion.herokuapp.com/notification/insert',
                    data: JSON.stringify(notification),
                    headers : {'token':$args}
                })
                    .then(function (success) {
                        $window.location.href = 'region/indexRegion';
                    }, function (error) {
                        console.log(" pas ok");
                    });
            });
        }




  $scope.getSignalement=function($args){

    $http.get('https://spring-ion.herokuapp.com/region/getSignalement?id='+$location.search().id+'',{
            headers : {'token':$args}
        }).then(function (response) {
        $scope.signalement = response.data;
        var lat = $scope.signalement.latitude;
        var lon = $scope.signalement.longitude;
                            var macarte = null;
                function initMap() {
                macarte = L.map('map').setView([lat, lon], 10);
                                    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {

                                    attribution: 'données © OpenStreetMap/ODbL - rendu OSM France',
                                    minZoom: 5,
                                    maxZoom: 20
                                    }).addTo(macarte);


                                     }
                                     initMap();

                var marker = L.marker([$scope.signalement.latitude,$scope.signalement.longitude]).addTo(macarte);
        });
  }
  scope.update=function($args,$id){

          $http({
              method: 'POST',
              url: 'https://spring-ion.herokuapp.com/region/update?id='+$id,
              headers : {'token':$args}
          })
          .then(function (response) {
              //$scope.insertNotification($args);
          });
      }

}]);






