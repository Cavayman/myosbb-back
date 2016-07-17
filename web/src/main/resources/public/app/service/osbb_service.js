/**
 * Created by Roman on 17.07.16.
 */

App.factory('OsbbService', ['$http', '$q', function ($http, $q) {

    return {

        getAllOsbb: function () {
            return $http.get('/restful/osbb/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching allOsbb');
                        return $q.reject(errResponse);
                    }
                );
        },

        createOsbb: function (osbb) {
            return $http.post('/restful/osbb/', osbb)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating osbb');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateOsbb: function (osbb) {
            return $http.put('/restful/osbb/', osbb)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating osbb');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteOsbb: function (osbbId) {
            return $http.delete('/restful/osbb/id/' + osbbId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting osbb');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteAllOsbb: function () {
            return $http.delete('/restful/osbb/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting allOsbb');
                        return $q.reject(errResponse);
                    }
                );
        }
    };
}]);
