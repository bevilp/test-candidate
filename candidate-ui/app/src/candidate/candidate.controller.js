(function () {
    'use strict';

    angular
        .module('app.candidate')
        .controller('CandidateController', CandidateController);

    CandidateController.$inject = ['CandidateService', '$log'];

    function CandidateController(CandidateService, $log) {
        var self = this;

        self.getAllCandidates = getAllCandidates;

        self.candidates = [];

        populate();

        function populate() {
            return getAllCandidates().then(function () {
                $log.info('retrieved all candidates');
            });
        }

        function getAllCandidates() {
            return CandidateService.getAllCandidates()
                .then(function (data) {
                    $log.info(data);
                    self.candidates = data;
                    return self.candidates;
                });
        }
    }
})();
