'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope) {
            $http({
                method: 'GET',
                url: 'api/demouser'
            }).then(function successCallback(res) {
                $scope.data = res.data.message;
            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });

            $scope.getCompany = function ($http, $scope) {
                var turl = 'http://cvrapi.dk/api?';
                var ourl = $scope.options + "=" + $scope.searchText + "&country=" + $scope.country;
                var finalurl = turl + ourl;
                $http.get(finalurl).then(function successCallback(res) {
                    $scope.data = res.data.message;
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });
            };

        });