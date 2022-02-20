var appname = angular.module('myapp', ['ngRoute']);

appname.config(['$locationProvider',function($locationProvider) {
  $locationProvider.html5Mode(true);
}]);



appname.controller('regionControl', ['$scope','$http','$location','$window',
function($scope,$http,$location,$window) {
  $scope.getSignalements=function($args,$id){

    $http.get('http://localhost:8080/signalementregion/getSignalementByRegion?id='+$id+'', {
        headers : {'token':$args}
    }).then(function (response) {

    var villes = {
        '<a href="lol.php">ok</a>': { "lat": -20.1, "lon": 46.4 }
     };
    for (ville in villes) {
        var marker = L.marker([villes[ville].lat, villes[ville].lon]).addTo(macarte);
        marker._icon.classList.add("glissement");
        marker.bindPopup(ville);
    }
        console.log('ok');
      $scope.signalement=response.data;
      for(ville in $scope.signalement){
        console.log($scope.signalement[ville].signalement.latitude);
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
  $scope.attribuerRegion=function(){
  console.log("id signalement ="+$location.search().id);
  console.log("id region ="+$scope.idregion);
  $http.get('http://localhost:8080/signalement/attribuer?idSignalement='+$location.search().id+"&idRegion="+$scope.idregion).then(function (response) {
      });
      $window.location.href = '/NouveauxSignalements';
  }

  $scope.getSignalement=function($args){

    $http.get('http://localhost:8080/region/getSignalement?id='+$location.search().id+'',{
            headers : {'token':$args}
        }).then(function (response) {
        $scope.signalement = response.data;
        var lat = $scope.signalement.latitude;
        var lon = $scope.signalement.longitude;
                            var macarte = null;
                function initMap() {
                macarte = L.map('map').setView([lat, lon], 8);
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
}]);






