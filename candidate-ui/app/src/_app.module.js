(function () {
    'use strict';

    angular
        .module('app', ['ngMockE2E', 'ui.router', 'ui.bootstrap', 'app.home', 'app.step', 'app.candidate', 'ngMessages'])
        .config(AppConfig);

    AppConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function AppConfig($stateProvider, $urlRouterProvider) {

        // Route
        $urlRouterProvider.otherwise('/home');

        $stateProvider.state('app', {
            abstract: true,
            templateUrl: 'src/main.template.html'
        });
    }

})();
