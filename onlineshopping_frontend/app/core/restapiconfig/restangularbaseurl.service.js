'use strict';

(function() {
	angular.module('restangular.baseurl').config(function(RestangularProvider) {
        //set the base url for api calls on our RESTful services
		var newBaseUrl = "";
		if (window.location.hostname == "localhost") {
			newBaseUrl = "http://localhost:8090/api/v1";
		} else {
			var deployedAt = window.location.href.substring(0, window.location.href);
			newBaseUrl = deployedAt + "/api/v1";
		}
		RestangularProvider.setBaseUrl(newBaseUrl);
	});
}());