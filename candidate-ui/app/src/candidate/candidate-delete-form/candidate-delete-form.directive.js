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
            scope: {candidateIdList: '='},
            templateUrl: './src/candidate/candidate-delete-form/candidate-delete-form.html'
        };
    }

    CandidateDeleteFormController.$inject = ['CandidateService', '$log'];

    function CandidateDeleteFormController(CandidateService, $log) {
        var self = this;
        self.deleteCandidates = deleteCandidates;

        function deleteCandidates() {
            $log.debug(self.candidateIdList);
            CandidateService.deleteCandidates({ids: self.candidateIdList});
        }
    }

})();