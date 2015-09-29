(function () {
    'use strict';

    angular
        .module('app.candidate')
        .directive('candidateRow', [
            CandidateRow
        ]);

    //directive
    function CandidateRow() {
        return {
            restrict: 'A',
            scope: {candidate: '='},
            templateUrl: './src/candidate/candidate-row/candidate-row.html'
        };
    }

})();
