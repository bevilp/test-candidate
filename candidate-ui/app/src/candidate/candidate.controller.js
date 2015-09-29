(function () {
    'use strict';

    angular
        .module('app.candidate')
        .controller('CandidateController', CandidateController);

    CandidateController.$inject = ['CandidateService', '$log'];

    function CandidateController(CandidateService, $log) {
        var self = this;

        self.getCandidates = getCandidates;
        self.sortBy = sortBy;
        self.sortReverse = sortReverse;

        self.candidates = [];

        // sorting variables
        self.sortColumn = 'name'; // default sort by name
        self.sortGrowing = true;  // default sort order

        populate();

        function populate() {
            return getCandidates().then(function () {
                $log.info('Got candidates');
            });
        };

        function getCandidates() {
            return CandidateService.getCandidates()
                .then(function (data) {
                    self.candidates = data;
                    return self.candidates;
                });
        }

        /**
         * Sets the controllers sortBy variable
         *
         * @param sortColumn what we wish to sort by
         */
        function sortBy(sortColumn) {
            $log.debug('sorting by: ' + sortColumn);
            self.sortColumn = sortColumn;
        }

        /**
         * Inverts sortReverse
         */
        function sortReverse() {
            self.sortGrowing = !self.sortGrowing;
        }
    }

})();
