(function () {
    'use strict';

    angular
        .module('app.step')
        .controller('StepsController', [
            StepsController
        ]);

    StepsController.$inject = ['StepService'];

    function StepsController(StepService) {
        var self = this;

        self.steps = [];
        StepService.get().then(function (response) {
            self.steps = response.data;
        });
    }

})();
