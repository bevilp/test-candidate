(function () {
    'use strict';

    angular
        .module('app.candidate')
        .factory('CandidateService', CandidateService);

    CandidateService.$inject = ['$http', '$log'];

    function CandidateService($http, $log) {
        var service = {
            getCandidates: getCandidates
        };
        return service;

        function getCandidates() {
            return $http.get('/candidate')
                .then(getCandidatesComplete)
                .catch(getCandidatesFailed);

            function getCandidatesComplete(response) {
                return response.data;
            }

            function getCandidatesFailed(error) {
                logger.error('XHR Failed for getCandidates.' + error.data);
            }
        }
    }

})();