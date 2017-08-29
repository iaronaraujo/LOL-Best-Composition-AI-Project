var start = document.getElementById("start");
var block = document.getElementById("startAlgorithm");
var resblock = document.getElementById("response");

var app = angular.module("TeamsManagement", []);

app.controller("GeneticAlgorithmController", function($scope, $http) {
    $scope.info = [];
    $scope.teams = [];

    $scope.dataObj = {
        init_pop : $scope.init_pop,
        iterations : $scope.iterations
    };

    $scope.sendInfoJSON = function(){          

        var method = "POST";
        var url = "/ga/run";

        $http({
        method : method,
        url : url,
        data : angular.toJson($scope.dataObj),
        headers : {
        'Content-Type' : 'application/json'
        }
        }).then( _success, _error );

        function _success(response) {
            $scope.teams.push(data);
            block.style.display = "none";
            resblock.style.display = "table";
        }

        function _error(response) {
            console.log(response.statusText);
        }

    };


});