'use strict';

// Declare app level module which depends on filters, and services
angular.module('farmApp', ['ngRoute', 'farmApp.factories', 'farmApp.directives', 'farmApp.controllers','ui.bootstrap']).
  config(['$routeProvider','$httpProvider', function($routeProvider, $httpProvider) {
    $routeProvider.when('/animal/list', {templateUrl: 'app/Animal/_list.html'});
    $routeProvider.when('/animal/create', {templateUrl: 'app/Animal/_create.html'});
    
    $routeProvider.when('/401', {templateUrl: '401.html'});
    
    $routeProvider.otherwise({redirectTo: '/animal/list'});
    
    $httpProvider.interceptors.push('sessionInterceptor');
  }]).constant('API_ENDPOINT','/api/rest/v1/animal');
