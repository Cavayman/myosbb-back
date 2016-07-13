/**
 * Created by nataliia on 12.07.16.
 */

App.factory('EventService', ['$http', '$q', function ($http, $q) {

    return {

        getAllEvents: function () {
            return $http.get('/restful/event/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching events');
                        return $q.reject(errResponse);
                    }
                );
        },

        createEvent: function (event) {
            return $http.post('/restful/event/', event)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating event');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateEvent: function (event, eventId) {
            return $http.put('/restful/event/' + eventId, event)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating event');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteEvent: function (eventId) {
            return $http.delete('/restful/event/' + eventId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting event');
                        return $q.reject(errResponse);
                    }
                );
        }
    };
}]);
