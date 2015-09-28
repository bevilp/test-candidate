(function () {
    'use strict';

    angular
        .module('app.candidate')
        .factory('CandidateService', CandidateService);

    CandidateService.$inject = ['$http', '$log'];

    function CandidateService($http, $log) {
        return {
            getAllCandidates: getAllCandidates
        };

        function getAllCandidates() {
            return $http.get('/candidate')
                .then(getCandidatesComplete)
                .catch(getCandidatesFailed);

            function getCandidatesComplete(response) {
                return response.data;
            }

            function getCandidatesFailed(error) {
                $log.error(error);
            }
        }
    };
})();