(function () {
    'use strict';

    angular
        .module('app.candidate', [])
        .config(DashboardConfig);

    DashboardConfig.$inject = ['$stateProvider'];

    function DashboardConfig($stateProvider) {

        $stateProvider.state('app.candidate', {
            url: '/candidate',
            views: {
                'main@app': {
                    templateUrl: 'src/candidate/candidate.html',
                    controller: 'CandidateController as ctrl'
                }
            },
            data: {}
        });
    }

})();