'use strict';

factories.factory('sessionInterceptor', function($q, $log, $location) {
	return {
		'request' : function(config) {
			$log.debug(config);
			return config;
		},
		'requestError' : function(rejection) {
			$log.warn('Request failed with', rejection.status , 'status');
			return $q.reject(rejection);
		},
		'response' : function(response) {
			$log.debug(response);
			return response;
		},
		'responseError' : function(rejection) {
			$log.warn('Response failed with', rejection.status , 'status');
			if (rejection.status == 401) {
                $location.url('/401');
            }
			
			return $q.reject(rejection);
		}
	};
});