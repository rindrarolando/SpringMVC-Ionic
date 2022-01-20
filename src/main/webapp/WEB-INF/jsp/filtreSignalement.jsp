<html>
  <head>
   <title>Signalement</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script type="text/javascript">

        var appname = angular.module('SignalementApp', []);

        appname.controller('SignalementController', ['$scope','$http',
        function($scope,$http) {

//Afficher la liste des signalement
          //$scope.afficheListeSignalement=function(){
            //$http.get('/signalement/getSignalements').then(function (response) {
              //$scope.signalement = response.data;
            //});
          //}

          //$scope.afficheListeSignalement();

//Modifier un signalement par son ID
          //$scope.editSignalement=function(sign){
            //$http.get('/signalement/deleteSignalement').then(function (response) {
              //$scope.signalement = response.data;
            //});
          //}

//Effacer un signalement par son ID
         // $scope.editSignalement=function(sign){
           // $http.get('/signalement/deleteSignalement?id='+sign.id+'').then(function (response) {
             // $scope.deleteComplete = response.data;
            //});
          //}

//Get les signalement faites par un utilisateurs specifique
           //$scope.getSignalementsByUtil=function(){
                       //$http.get('/signalement/SignalementByUtil?username='+$scope.username+'').then(function (response) {
                         //$scope.signalement = response.data;
                       //});
                     //}


//Get les signalement faites par region , types , etat , entre deux dates specifique
           $scope.getSignalementByFilter=function(){
                       $http.get('/signalementregion/SignalementByFilter?idRegion='+$scope.idRegion+'?idType='+$scope.idType+'?etat='+$scope.etat+'?debut='+$scope.debut+'?fin='+$scope.fin).then(function (response) {
                         $scope.signalement = response.data;
                       });
                     }


//Get statistics signalement en Global
           $scope.getSignalementByFilter=function(){
                       $http.get('/signalementregion/SignalementByFilter?idRegion='+$scope.idRegion+'?idType='+$scope.idType+'?etat='+$scope.etat+'?debut='+$scope.debut+'?fin='+$scope.fin).then(function (response) {
                         $scope.signalement = response.data;
                       });
                     }

//Get statistics signalement en precisant une region specifique
           $scope.getSignalementByFilter=function(){
                       $http.get('/signalementregion/SignalementByFilter?idRegion='+$scope.idRegion+'?idType='+$scope.idType+'?etat='+$scope.etat+'?debut='+$scope.debut+'?fin='+$scope.fin).then(function (response) {
                         $scope.signalement = response.data;
                       });
                     }



        }


        ]);
   </script>

  <head>
    <body ng-app="SignalementApp" ng-controller="SignalementController">

         <h1>
           Liste des signalement par tout les utilisateurs :
         </h1>

         <p>utilisateurs : <input type="text" ng-model="id"></p>
         <button ng-click="getSignalementByFilter()"> search </button>


        <p> {{ deleteComplete }} </p>
        <table>

               <tr>
                  <th>etat</th>
                  <th>description</th>
                  <th>Date</th>
                  <th>latitude</th>
                  <th>longitude</th>
                  <th>Operations</th>
               </tr>
               <tr ng-repeat="sign in signalement">
                   <td> {{ sign.etat }} </td>
                   <td> {{ sign.description }} </td>
                   <td> {{ sign.dateSignalement }} </td>
                   <td> {{ sign.latitude }} </td>
                   <td> {{ sign.longitude }} </td>
                   <td><a class="blue-button">Edit</a> | <a class="red-button">Delete</a></td>
               </tr>

        </table>

  </body>
</html>