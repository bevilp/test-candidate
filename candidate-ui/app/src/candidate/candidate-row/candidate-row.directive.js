(function () {
    'use strict';

    angular.module('app.candidate').directive('candidateRow', [
        CandidateRow
    ]);

    CandidateRowController.$inject = ['CandidateService', '$log'];

    //directive
    function CandidateRow() {
        return {
            bindToController: true,
            controller: CandidateRowController,
            controllerAs: 'ctrl',
            restrict: 'A',
            scope: {candidate: '='},
            templateUrl: './src/candidate/candidate-row/candidate-row.html'
        };
    }

    function CandidateRowController(CandidateService, $log) {
        var self = this;

        self.enabledCheckbox = self.candidate.enabled;
        self.toggle = toggle;

        function toggle() {
            // save previous state in case update fails
            var previousValue = self.candidate.enabled;
            self.candidate.enabled = self.enabledCheckbox;

            CandidateService.updateCandidate(self.candidate).then(function (response) {
                // success
                $log.debug('update succeeded');
            }, function () {
                // update failed. Restore previous state
                $log.error('update failed');
                self.candidate.enabled = previousValue;
                self.enabledCheckbox = previousValue;
            });
        }
    }
})();
