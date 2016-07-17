/**
 * Created by Roman on 17.07.16.
 */

App.controller('OsbbController', ['$scope', 'OsbbService',

    function ($scope, OsbbService) {
        var osbbController = this;

        osbbController.osbb = {osbbId: null, name: '', description: ''};
        osbbController.allOsbb = [];

        osbbController.getAllOsbb = function () {
            OsbbService.getAllOsbb()
                .then(
                    function (data) {
                        osbbController.allOsbb = data;
                    },
                    function (errResponse) {
                        console.error('error while fetching allOsbb');
                    }
                );
        };

        osbbController.createOsbb = function (osbb) {
            if (osbb.date == null || osbb.date.length == 0) {
                console.log('specifying date');
                osbb.date = new Date().toJSON().slice(0, 10);
                console.log(osbb.date);
            }
            OsbbService.createOsbb(osbb)
                .then(
                    osbbController.getAllOsbb,
                    function (errResponse) {
                        console.error('error while creating allOsbb');
                    }
                );
        };

        osbbController.updateOsbb = function (osbb) {
            OsbbService.updateOsbb(osbb)
                .then(
                    osbbController.getAllOsbb,
                    function (errResponse) {
                        console.error('error while updating allOsbb');
                    }
                );
        };

        osbbController.deleteOsbb = function (osbbId) {
            OsbbService.deleteOsbb(osbbId)
                .then(
                    osbbController.getAllOsbb,
                    function (errResponse) {
                        console.error('error while deleting allOsbb');
                    }
                );
        };

        osbbController.deleteAllOsbb = function () {
            OsbbService.deleteAllOsbb()
                .then(
                    osbbController.getAllOsbb,
                    function (errResponse) {
                        console.error('error while deleting allOsbb');
                    }
                );
        };

        osbbController.getAllOsbb();

        osbbController.submit = function () {
            if (osbbController.osbb.osbbId === null) {
                console.log('saving new osbb', osbbController.osbb);
                osbbController.createOsbb(osbbController.osbb);
            } else {
                var osbbId = parseInt(osbbController.osbb.osbbId);
                console.log('updating osbb', osbbController.osbb);
                osbbController.updateOsbb(osbbController.osbb, osbbId);
            }
            osbbController.reset();
        };

        osbbController.edit = function (osbbId) {
            console.log('id to be edited: ' + osbbId);
            for (var i = 0; i < osbbController.allOsbb.length; i++) {
                if (osbbController.allOsbb[i].osbbId === osbbId) {
                    osbbController.osbb = angular.copy(osbbController.allOsbb[i]);
                    break;
                }
            }
        };

        osbbController.remove = function (osbbId) {
            if (osbbController.osbb.osbbId === osbbId) {
                osbbController.reset();
            }
            osbbController.deleteOsbb(osbbId);
        };

        osbbController.reset = function () {
            osbbController.osbb = {osbbId: null, name: '', description: ''};
            $scope.osbbForm.$setPristine();
        }
    }
]);