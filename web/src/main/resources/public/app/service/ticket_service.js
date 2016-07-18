/**
 * Created by Kris on 15.07.2016.
 */
App.factory('TicketService', ['$http', '$q', function ($http, $q) {
    return {
        getAllTickets: function () {
            return $http.get('/restful/ticket/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching ticket');
                        return $q.reject(errResponse);
                    }
                );
        },

        createTicket: function (ticket) {
            return $http.post('/restful/ticket/', ticket)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating ticket');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateTicket: function (ticket, ticketId) {
            return $http.put('/restful/ticket/' + ticketId, ticket)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating ticket');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteTicket: function (ticketId) {
            return $http.delete('/restful/ticket/' + ticketId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting ticket');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteAllTickets: function () {
            return $http.delete('/restful/ticket/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting ticket');
                        return $q.reject(errResponse);
                    }
                );
        }
    };
}]);
