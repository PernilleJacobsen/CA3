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
        url: 'api/demouser'
    }).then(function successCallback(res) {
        $scope.data = res.data.message;
    }, function errorCallback(res) {
        $scope.error = res.status + ": " + res.data.statusText;
    });

});