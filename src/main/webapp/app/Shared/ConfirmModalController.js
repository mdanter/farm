'use strict';

controllers.controller('ConfirmModalController', function($scope,
		$modalInstance, action, message) {
	
	$scope.action = action;
	$scope.message = message;
	
	$scope.ok = function() {
		$modalInstance.close();
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

});