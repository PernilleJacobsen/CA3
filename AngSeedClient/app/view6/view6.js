'use strict';

angular.module('myApp.view6', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view6', {
                    templateUrl: 'view6/view6.html',
                    controller: 'View6Ctrl'
                });
            }])
        .controller('View6Ctrl', function ($scope, $http) {
            $scope.saveUser = function () {
                $http.post('api/saveUser', $scope.user).
                        succes(function () {
                            
                            $scope.myVar = false;
                            $scope.message = "User created";
                        })
                        .error(function () {
                                $scope.myVar = true;
                                $scope.message = "User not created!";
                            });
        

            };
        });
