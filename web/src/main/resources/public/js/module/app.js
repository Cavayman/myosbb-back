'use strict'
var App = angular.module('osbbApp', []);


App.directive('showErrors', function($timeout) {
    return {
        restrict: 'A',
        require: '^form',
        link: function (scope, el, attrs, formCtrl) {
            // find the text box element, which has the 'rname' attribute
            var inputEl   = el[0].querySelector("[rname]");
            // convert the native text box element to an angular element
            var inputNgEl = angular.element(inputEl);
            // get the name on the text box
            var inputName = inputNgEl.attr('rname');

            // only apply the has-error class after the user leaves the text box
            var blurred = false;
            inputNgEl.bind('blur', function() {
                blurred = true;
                el.toggleClass('has-error', formCtrl[inputName].$invalid);
            });

            scope.$watch(function() {
                return formCtrl[inputName].$invalid
            }, function(invalid) {
                // we only want to toggle the has-error class after the blur
                // event or if the control becomes valid                if (!blurred && invalid) { return }
                el.toggleClass('has-error', invalid);
            });

            scope.$on('show-errors-check-validity', function() {
                el.toggleClass('has-error', formCtrl[inputName].$invalid);
            });

            scope.$on('show-errors-reset', function() {
                $timeout(function() {
                    el.removeClass('has-error');
                }, 0, false);
            });
        }
    }
});

app.controller('DemoCtrl', function($scope, $log, $http, $timeout) {

    $scope.someFunction = function (email){
        $scope.counter++;
        $log.info("seleccionaste a:" + email);

    };

    $scope.generos = [
        { clave: "01", nombre: "MALE" },
        { clave: "02", nombre: "FEMALE" }
    ];

    $scope.save=function () {
        $scope.$broadcast('show-errors-check-validity');
        if($scope.datosDeUbicacion.$valid)
            alert('SUCCESS');
    }

});