/**
 * Created by Kris on 13.07.2016.
 */

App.factory('MessageService', ['$http', '$q', function ($http, $q) {

    return {


        getAllMessages: function () {

            return $http.get('http://localhost:52430/message/')
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


            return $http.post('http://localhost:52430/message/', message)
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

            return $http.put('http://localhost:52430/message/' + messageId, message)
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

            return $http.delete('http://localhost:52430/event/' + messageId)
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


    };


}

])