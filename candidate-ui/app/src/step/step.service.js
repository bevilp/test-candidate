(function () {
    'use strict';

    angular
        .module('app.step')
        .service('StepService', [
            '$http',
            StepService
        ]);

    StepService.$inect = ['$http'];

    function StepService($http) {
        var self = this;

        this.get = get;

        function get() {
            return $http.get('/step');
        }

    }

})();
