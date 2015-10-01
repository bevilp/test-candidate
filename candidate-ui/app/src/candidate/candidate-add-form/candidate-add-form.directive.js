(function () {
    'use strict';

    angular.module('app.candidate').directive('candidateAddForm', [
        CandidateAddForm
    ]);

    function CandidateAddForm() {
        return {
            bindToController: true,
            controller: CandidateAddFormController,
            controllerAs: 'ctrl',
            restrict: 'E',
            scope: {candidates: '='},
            templateUrl: './src/candidate/candidate-add-form/candidate-add-form.html'
        };
    }

    CandidateAddFormController.$inject = ['CandidateService', '$log'];

    function CandidateAddFormController(CandidateService, $log) {
        var self = this;

        self.candidateName = "";
        self.formSubmitted = false;
        self.addCandidate = addCandidate;

        function addCandidate(name) {
            $log.debug("trying to add candidate with name[" + name + "]");
            self.formSubmitted = true;

            CandidateService.addCandidate({name: name, enabled: true}).then(function (result) {
                $log.debug("Successfully added candidate ", result);
                self.candidates.push(result);
            }, function (error) {
                $log.error("Could not add candidate du to ", error);
            })
        }
    }

})();