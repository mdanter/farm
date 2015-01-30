'use strict';

factories.factory('animalFactory', ['$http', '$log','API_ENDPOINT', function($http, $log, API_ENDPOINT) {
    return {
        getAll: function() {
            return $http.get(API_ENDPOINT);
        },
        nonono: function() {
            return $http.get(API_ENDPOINT+'/nonono');
        },
		getbyId : function(id) {
			return $http.get(API_ENDPOINT + '/' + id);
		},
		create : function(animal) {
			
			var API = API_ENDPOINT;
			var headers={ 'Content-Type' : 'application/json' };
			
			$log.info('Creating animal: ');
			$log.info(animal);
			return $http.post(API, animal, headers);
			 
		},
		remove : function(animal) {
			
			var API = API_ENDPOINT+'/'+animal.id;
			var headers={ 'Content-Type' : 'application/json' };
			
			$log.info('Deleting animal: ');
			$log.info(animal);
			return $http.delete(API, headers);
			 
		},
		update : function(animal) {
			
			var API = API_ENDPOINT+'/'+animal.id;
			var headers={ 'Content-Type' : 'application/json' };
			
			$log.info('Updating animal: ');
			$log.info(animal);
			return $http.put(API,animal, headers);
			 
		},
		feed : function(animal) {
			
			var API = API_ENDPOINT+'/feed/'+animal.id;
			var headers={ 'Content-Type' : 'application/json' };
			
			$log.info('Feeding animal: ');
			$log.info(animal);
			return $http.put(API, animal, headers);
			 
		},
		sell : function(animal) {
			
			var API = API_ENDPOINT+'/sell/'+animal.id;
			var headers={ 'Content-Type' : 'application/json' };
			
			$log.info('Selling animal: ');
			$log.info(animal);
			return $http.put(API, animal, headers);
			 
		}
    };  
}]);