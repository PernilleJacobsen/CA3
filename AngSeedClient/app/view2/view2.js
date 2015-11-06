'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope) {
                        $http.get('api/getCompany').then(function (response) {
                $scope.data = response.data;
            });
            $scope.getCompany = function () {
                var url = 'api/getCompany/'+$scope.options + "/" + $scope.searchText + "/" + $scope.country;
                $http.get(url).then(function successCallback(res) {
                    $scope.data = res.data;
                }, function errorCallback(response) {
                    var test = angular.toJson(response.data, true);
                    $scope.error = test;
            });
            };

        });