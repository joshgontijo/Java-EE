'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
    'ui.bootstrap',
    'ngRoute',
    'myApp.filters',
    'myApp.services',
    'myApp.directives',
    'myApp.controllers'
]).
        config(['$routeProvider', function($routeProvider) {
                $routeProvider.when('/main', {templateUrl: 'partials/main.html'})
                        .when('/charts', {templateUrl: 'partials/charts.html'})
                        .when('/tables', {templateUrl: 'partials/gears.html', controller: 'gearsController'})
                        .when('/forms', {templateUrl: 'partials/forms.html'})
                        .when('/withdrawal', {templateUrl: 'partials/withdrawal.html', controller: 'withdrawalController'})
                        .when('/users', {templateUrl: 'partials/users.html', controller: 'usersController'})
                        .otherwise({redirectTo: '/main'});
            }]);
