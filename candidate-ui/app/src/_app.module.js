(function () {
    'use strict';

    angular
        .module('app', ['ngMockE2E', 'ui.router', 'ui.bootstrap', 'app.home', 'app.step', 'app.candidate'])
        .config([
            '$stateProvider', '$urlRouterProvider',
            AppConfig
        ]);

    function AppConfig($stateProvider, $urlRouterProvider) {

        // Route
        $urlRouterProvider.otherwise('/home');

        $stateProvider.state('app', {
            abstract: true,
            templateUrl: 'src/main.template.html'
        });
    }

})();
