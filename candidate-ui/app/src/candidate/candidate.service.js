(function () {
    'use strict';

    angular
        .module('app.candidate')
        .factory('CandidateService', CandidateService);

    CandidateService.$inject = ['$http', '$log', '$q'];

    function CandidateService($http, $log, $q) {
        var _candidateUrl = '/candidate';
        var service = {
            getCandidates: getCandidates,
            updateCandidate: updateCandidate,
            addCandidate: addCandidate
        };
        return service;

        function getCandidates() {
            return $http.get(_candidateUrl)
                .then(getCandidatesComplete)
                .catch(getCandidatesFailed);

            function getCandidatesComplete(response) {
                return response.data;
            }

            function getCandidatesFailed(error) {
                $log.error('XHR Failed for getCandidates.' + error.data);
            }
        }

        function updateCandidate(candidate) {
            return $http.put(_candidateUrl + '/' + candidate.id, candidate)
                .then(updateCandidateComplete)
                .catch(updateCandidateFailed);
            function updateCandidateComplete(response) {
                $log.debug('candidate updated');
                $log.debug(response);
                return response.data;
            }

            function updateCandidateFailed(error) {
                $log.error('XHR Failed for updateCandidate.');
                $log.error(error)
                return $q.reject(error);
            }
        }

        function addCandidate(candidate) {
            return $http.post(_candidateUrl,
                candidate)
                .then(addCandidateComplete)
                .catch(addCandidateCompleteFailed);

            function addCandidateComplete(response) {
                $log.debug('Candidate Added ' + response.data);
                return response.data;
            }

            function addCandidateCompleteFailed(error) {
                $log.error('XHR Failed for updateCandidate.');
                $log.error(error)
                return $q.reject(error);
            }
        }
    }

})();