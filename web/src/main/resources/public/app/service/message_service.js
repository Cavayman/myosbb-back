/**
 * Created by Kris on 13.07.2016.
 */

App.factory('MessageService', ['$http', '$q', function ($http, $q) {

    return {
        getAllMessages: function () {
            return $http.get('/restful/message/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching messages');
                        return $q.reject(errResponse);
                    }
                );
        },

        createMessage: function (message) {
            return $http.post('/restful/message/', message)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating message');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateMessage: function (message, messageId) {
            return $http.put('/restful/message/' + messageId, message)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating message');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteMessage: function (messageId) {
            return $http.delete('/restful/message/' + messageId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting message');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteAllMessages: function () {
            return $http.delete('/restful/message/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting messeges');
                        return $q.reject(errResponse);
                    }
                );
        }
    };
}
])