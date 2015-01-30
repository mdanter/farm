'use strict';

controllers.controller('CreateAnimalController', [
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
				
				return {
						id : '',
						name : '',
						species : '',
						imageSrc : '',
						timesFed : 0,
						sold : false
				};
			}

			vm.animal = vm.init();

			// bound to create button
			vm.create = function() {

				// open modal dialog and save the reference so we can close it
				vm.busyModal = vm.openBusyModal();

				animalFactory.create(vm.animal).then(function(response) {

					$log.info(response.data);

					vm.userFeedback = response.data;

					// close modal dialog
					vm.busyModal.close();

					vm.animal = vm.init();
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

		} ]);