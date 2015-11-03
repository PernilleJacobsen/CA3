'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])
        .controller('View6Ctrl', function ($scope, $http) {
            $scope.message = "hej";
            $scope.saveUser = function () {
                alert('aloha');
                $http.post('api/saveUser', $scope.user).
                        success(function () {
                            alert('hej');
                        });
            };
        });


