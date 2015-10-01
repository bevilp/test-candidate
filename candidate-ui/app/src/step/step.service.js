(function () {
    'use strict';

    angular
        .module('app.step')
        .service('StepService', StepService);

    StepService.$inject = ['$http'];

    function StepService($http) {
        var self = this;

        self.get = get;

        function get() {
            return $http.get('/step');
        }
    }

})();

