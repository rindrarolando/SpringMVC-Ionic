var appname = angular.module('myapp', ['ngRoute']);

appname.config(['$locationProvider',function($locationProvider) {
  $locationProvider.html5Mode(true);
}]);

appname.controller('listeSignControl', ['$scope','$http',
function($scope,$http) {
  $scope.getSignalements=function(){ 
    $http.get('https://spring-ion.herokuapp.com/signalement/getSignalements').then(function (response) {
      $scope.data=response.data; 
    });
  }
  $scope.getSignalements();
}

]);

appname.controller('testControl', ['$scope','$http','$location',
function($scope,$http,$location) {
  $scope.getSignalement=function(){
  console.log("oh fuk");
    $http.get('https://spring-ion.herokuapp.com/signalement/getSignalement?id='+$location.search().id+'').then(function (response) {
      $scope.signalement=response.data;
      $http.get('https://nominatim.openstreetmap.org/reverse?format=json&lat='+$scope.signalement.latitude+'&lon='+$scope.signalement.longitude+'&zoom=18&addressdetails=1&fbclid=IwAR30BvNdX9B3P-mKUnh6H2bOv2KjXDngOnXosNHbvyX_8Y5Ddd-Vlih0zqc').then(function (response) {
            $scope.region=response.data.address.state;

            console.log($scope.region);
          });
    });
    $http.get('https://spring-ion.herokuapp.com/region/getRegions').then(function (response) {
            $scope.lregions=response.data;
    });
  }
  $scope.attribuerRegion=function(){
  console.log("id signalement ="+$location.search().id);
  console.log("id region ="+$scope.idregion);
  }
}]);

appname.controller('tablesControl', ['$scope','$http',
function($scope,$http) {
  $scope.getRegions=function(){
    $http.get('localhost:8080/region/getRegions').then(function (response) {
      $scope.regions=response.data;
    });
  }
  $scope.getUtilisateurs=function(){
      $http.get('https://spring-ion.herokuapp.com/utilisateurs/getUtilisateurs').then(function (response) {
        $scope.utilisateurs=response.data;
      });
    }
    $scope.getTypeSignalements=function(){
        $http.get('https://spring-ion.herokuapp.com/typesignalement/getTypeSignalements').then(function (response) {
          $scope.typeSignalements=response.data;
        });
      }
  $scope.getData=function(){
    $scope.getRegions();
    $scope.getUtilisateurs();
    $scope.getTypeSignalements();
  }
  console.log($scope.typeSignalements);

}

]);







