'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'view4/view4.html',
                    controller: 'View4Ctrl'
                });
            }]).controller('View4Ctrl', function ($http, $scope) {
    $http({
        method: 'GET',
        url: 'api/currency/dailyrates'
    }).then(function successCallback(res) {
        $scope.data = res.data;
    }, function errorCallback(res) {
        $scope.error = res.status + ": " + res.data.statusText;
    });
    
    $scope.convert = function(){
        var baseUrl = 'api/currency/calculator/';
        var attributes = $scope.amount +"/" + $scope.fromcurrency + "/" + $scope.tocurrency;
        var url = baseUrl + attributes;
        $http.get(url).then(function successCallBack(res){
            $scope.calculated = res.data;
        }, function errorCallBack(res){
            alert("noget gik galt");
        });
    };
});