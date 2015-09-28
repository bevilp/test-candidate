(function () {
    'use strict';

    angular
        .module('app.candidate', ['ngSanitize'])
        .config([
            '$stateProvider',
            DashboardConfig
        ]);

    function DashboardConfig($stateProvider) {
        $stateProvider.state('app.candidate', {
            url: '/candidate',
            views: {
                'main@app': {
                    templateUrl: 'src/candidate/candidate.html',
                    controller: 'CandidateController as candidateCtrl',
                }
            },
            data: {}
        });
    }
})();