var flightResApp = angular.module("flightReservation", []);

angular.module('flightReservation').directive('equals', function () {
    return {
        restrict: 'A',
        require: '?ngModel',
        link: function (scope, elem, attrs, ngModel) {
            if (!ngModel) return;

            scope.$watch(attrs.ngModel, function () {
                validate();
            });

            attrs.$observe('equals', function (val) {
                validate();
            });

            var validate = function () {
                // values
                var val1 = ngModel.$viewValue;
                var val2 = attrs.equals;

                // set validity
                ngModel.$setValidity('equals', !val1 || !val2 || val1 === val2);
            };
        }
    }
});

flightResApp.controller("loginController", ['$scope', function ($scope) {
    $scope.heading = "User Login";

}])

flightResApp.controller("registrationController", ['$scope', function ($scope) {
    $scope.heading = "User Registration";

}])