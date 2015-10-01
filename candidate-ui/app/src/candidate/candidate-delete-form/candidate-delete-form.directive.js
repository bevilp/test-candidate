(function () {
    'use strict';

    angular.module('app.candidate').directive('candidateDeleteForm', [
        CandidateDeleteForm
    ]);

    function CandidateDeleteForm() {
        return {
            bindToController: true,
            controller: CandidateDeleteFormController,
            controllerAs: 'ctrl',
            restrict: 'E',
            scope: {candidates: '='},
            templateUrl: './src/candidate/candidate-delete-form/candidate-delete-form.html'
        };
    }

    CandidateDeleteFormController.$inject = ['CandidateService', '$log'];

    function CandidateDeleteFormController(CandidateService, $log) {
        var self = this;
        self.deleteCandidates = deleteCandidates;

        function deleteCandidates() {
            var candidateIds = self.candidates.filter(function (candidate) {
                return candidate.delete === true;
            }).map(function (candidate) {
                return candidate.id;
            });
            $log.debug('will delete: ', candidateIds);
            CandidateService.deleteCandidates({ids: candidateIds})
                .then(function (response) {
                    candidateIds.forEach(function (id) {
                        self.candidates.splice(
                            self.candidates.indexOf(
                                self.candidates.filter(
                                    function (candidate) {
                                        return candidate.id === id
                                    })[0]), 1)
                    });
                    $log.debug('Successfully delete candidates ', candidateIds)
                }, function (response) {
                    $log.error('Could not delete candidates du to ', error);
                });
        }
    }

})
();