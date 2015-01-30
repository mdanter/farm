'use strict';

controllers.controller('ViewAnimalModalController', ['$scope','$log','$modalInstance','animal','animalFactory',function($scope,
		$log, $modalInstance, animal, animalFactory) {


	$scope.animal = animal;
	
	$scope.feedAndClose = function(animal) {
		
		animalFactory.feed(animal).then(function(response) {
			$log.info(response.data);
			$modalInstance.close($scope.animal);
		}, function(response) {
			$log.error(response.data);
		});
		
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('close');
	};

}]);