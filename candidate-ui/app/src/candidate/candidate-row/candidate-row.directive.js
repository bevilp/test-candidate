(function () {
    'use strict';

    angular.module('app.candidate')
        .directive('candidateRow', [
            CandidateRow
        ]);

    //directive
    function CandidateRow() {
        return {
            bindToController: true,
            controller: CandidateRowController,
            controllerAs: 'ctrl',
            restrict: 'A',
            scope: {
                candidate: '='
            },
            templateUrl: './src/candidate/candidate-row/candidate-row.html'
        };
    }

    CandidateRowController.$inject = ['CandidateService', '$log'];

    function CandidateRowController(CandidateService, $log) {
        var self = this;

        self.enabledCheckbox = self.candidate.enabled;
        self.deleteCheckbox = false;

        self.toggle = toggle;

        function toggle() {
            // save previous state in case update fails
            var previousValue = self.candidate.enabled;
            self.candidate.enabled = self.enabledCheckbox;

            CandidateService.updateCandidate(self.candidate).then(function (response) {
                // success
                $log.debug('successfully updated candidate ', response);
            }, function (error) {
                $log.error('failed to update candidate due to ', error);
                // update failed. Restore previous state
                self.candidate.enabled = previousValue;
                self.enabledCheckbox = previousValue;
            });
        }
    }
})();
