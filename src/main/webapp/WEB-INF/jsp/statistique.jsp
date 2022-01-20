<html>
  <head>
   <title>Signalement</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script type="text/javascript">

        var appname = angular.module('SignalementApp', []);

        appname.controller('SignalementController', ['$scope','$http',
        function($scope,$http) {

//Afficher la liste des signalement
          $scope.afficheListeSignalement=function(){
            $http.get('/signalement/getSignalements').then(function (response) {
              $scope.signalement = response.data;
            });
          }

          $scope.afficheListeSignalement();




        }


        ]);
   </script>

  <head>
    <body ng-app="SignalementApp" ng-controller="SignalementController">

         <h1>
           Liste des signalement par tout les utilisateurs :
         </h1>




        <p> {{ deleteComplete }} </p>
        <table>

               <tr>
                  <th>Type</th>
                  <th>nombre de report</th>

               </tr>
               <tr ng-repeat="sign in signalement">
                   <td> {{ sign.etat }} </td>
                   <td> {{ sign.idtype }} </td>

                   <td><a class="blue-button">Edit</a> | <a class="red-button">Delete</a></td>
               </tr>

        </table>

  </body>
</html>