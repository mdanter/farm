'use strict';

controllers.controller('ListAnimalController', [
		'$log',
		'$timeout',
		'$modal',
		'$location',
		'$routeParams',
		'animalFactory',
		function($log, $timeout, $modal, $location, $routeParams,
				animalFactory) {

			var vm = this;
			
			vm.userFeedback = '';

			vm.init = function() {
				
				animalFactory.getAll().then(function(response) {

					$log.info(response.data);

					vm.animals = response.data;

					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				}, function(response) {

					$log.error(response.data);

					vm.userFeedback = response.data;

					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				});
				
				
			}

			vm.init();

			vm.view = function(animal) {

				// open modal dialog and save the reference so we can close it
				vm.viewModal = vm.openViewModal(animal)
				
				
				vm.viewModal.result.then(function(animal) {
					$log.info(animal);
					vm.init();
					
				}, function() {
					$log.info('View dismissed at: ' + new Date());
				});

			}
			
			vm.feed = function(animal) {

				// open modal dialog and save the reference so we can close it
				vm.busyModal = vm.openBusyModal();

				animalFactory.feed(animal).then(function(response) {

					$log.info(response.data);

					vm.userFeedback = response.data;

					// close modal dialog
					vm.busyModal.close();

					vm.init();
					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				}, function(response) {

					$log.error(response.data);

					vm.userFeedback = response.data;

					// close modal dialog
					vm.busyModal.close();

					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				});

			}
			
			vm.sell = function(animal) {

				// open modal dialog and save the reference so we can close it
				vm.busyModal = vm.openBusyModal();

				animalFactory.sell(animal).then(function(response) {

					$log.info(response.data);

					vm.userFeedback = response.data;
					
					animalFactory.feed(animal).then(function(response) {
						$log.info(response.data);

					}, function(response) {
						$log.info(response.data);
					});

					// close modal dialog
					vm.busyModal.close();

					vm.init();
					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				}, function(response) {

					$log.error(response.data);

					vm.userFeedback = response.data;

					// close modal dialog
					vm.busyModal.close();

					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				});

			}
			
			vm.nono = function(){
				animalFactory.nono().then(function(response) {

					$log.info(response.data);

				}, function(response) {

				});

			}
			
			vm.remove = function(animal) {

				// open modal dialog and save the reference so we can close it
				vm.busyModal = vm.openBusyModal();

				animalFactory.remove(animal).then(function(response) {

					$log.info(response.data);

					vm.userFeedback = response.data;

					// close modal dialog
					vm.busyModal.close();

					vm.init();
					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				}, function(response) {

					$log.error(response.data);

					vm.userFeedback = response.data;

					// close modal dialog
					vm.busyModal.close();

					$timeout(function() {
						vm.userFeedback = ''
					}, 5000);

				});

			}
			
			vm.openBusyModal = function() {

				return $modal.open({
					templateUrl : 'app/Shared/_busyModal.html',
					controller : 'BusyModalController',
					backdrop : 'static',
					keyboard : false
				});

			};
			
			vm.openViewModal = function(animal) {

				return $modal.open({
					templateUrl : 'app/Animal/_viewAnimalModal.html',
					controller : 'ViewAnimalModalController',
					backdrop : 'static',
					keyboard : false,
					resolve : {
						animal : function() {
							return animal;
						}
					}
				});

			};

		} ]);