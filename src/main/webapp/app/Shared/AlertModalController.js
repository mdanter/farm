'use strict';

controllers.controller('AlertModalController', function($scope,
		$modalInstance, action, message) {
	
	$scope.action = action;
	$scope.message = message;
	
	$scope.ok = function() {
		$modalInstance.close();
	};

});