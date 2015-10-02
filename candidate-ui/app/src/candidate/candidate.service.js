(function () {
    'use strict';

    angular
        .module('app.candidate')
        .factory('CandidateService', CandidateService);

    CandidateService.$inject = ['$http', '$log', '$q', 'appConfig'];

    function CandidateService($http, $log, $q, appConfig) {
        var _candidateUrl = appConfig.backend + '/candidate';
        var service = {
            getCandidates: getCandidates,
            updateCandidate: updateCandidate,
            addCandidate: addCandidate,
            deleteCandidates: deleteCandidates
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
                $log.error('Failed to retrieve candidates due to ' + error.data);
            }
        }

        function updateCandidate(candidate) {
            return $http.put(_candidateUrl + '/' + candidate.id, candidate)
                .then(updateCandidateComplete)
                .catch(updateCandidateFailed);
            function updateCandidateComplete(response) {
                $log.debug('candidate updated ', response);
                return response.data;
            }

            function updateCandidateFailed(error) {
                $log.error('Failed to update candidate due to ', error);
                return $q.reject(error);
            }
        }

        function addCandidate(candidate) {
            return $http.post(_candidateUrl,
                candidate)
                .then(addCandidateComplete)
                .catch(addCandidateCompleteFailed);

            function addCandidateComplete(response) {
                $log.debug('Candidate Added ', response.data);
                return response.data;
            }

            function addCandidateCompleteFailed(error) {
                $log.error('Failed to add candidate due to ', error);
                return $q.reject(error);
            }
        }

        function deleteCandidates(candidateIds) {
            return $http.post(_candidateUrl + '/delete', candidateIds)
                .then(deleteCandidatesSuccess)
                .catch(deleteCandidatesFailed);

            function deleteCandidatesSuccess(response) {
                $log.debug('Candidates deleted ', response.data);
                return response.data;
            }

            function deleteCandidatesFailed(error) {
                $log.error('Failed to delete candidates due to ', error);
                return $q.reject(error);
            }
        }
    }

})();