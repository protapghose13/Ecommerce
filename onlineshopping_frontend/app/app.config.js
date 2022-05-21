'use strict';

angular.
  module('phonecatApp').
  config(['$routeProvider',
    function config($routeProvider) {
      $routeProvider.
        when('/phones', {
          template: '<phone-list></phone-list>'
        }).
        when('/phones/:phoneId', {
          template: '<phone-detail></phone-detail>'
        }).
        when('/phonelisttable', {
          template: '<phone-list-table></phone-list-table>'
        }).
        when('/products', {
          template: '<product-list></product-list>'
        }).
        when('/products/edit/:productId', {
          template: '<product-detail></product-detail>'
        }).
        when('/products/create', {
          template: '<product-detail></product-detail>'
        }).
        otherwise('/phones');
    }
  ]);
