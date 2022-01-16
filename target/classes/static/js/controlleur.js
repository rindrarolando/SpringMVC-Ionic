var appname = angular.module('myapp', ['ngRoute']);

appname.config(['$locationProvider',function($locationProvider) {
  $locationProvider.html5Mode(true);
}]);

appname.controller('listeSignControl', ['$scope','$http','$location',
function($scope,$http,$location) {
 
  $scope.getSignalements=function(){ 
    $http.get('https://spring-ion.herokuapp.com/signalement/getSignalements').then(function (response) {
      $scope.data=response.data; 
      
    });
  }

  $scope.getSignalements();
  
  $scope.getSignalement=function($id){ 
    $http.get('https://spring-ion.herokuapp.com/signalement/getSignalement?id='+$id+'').then(function (response) {
      $scope.signalement=response.data; 
    });
  }
}

]);

appname.controller('testControl', ['$scope','$http','$location',
function($scope,$http,$location) {
  
  console.log($location.search().id);
  
}]);








