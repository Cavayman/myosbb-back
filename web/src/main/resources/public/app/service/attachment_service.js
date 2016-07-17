/**
 * Created by nataliia on 15.07.16.
 */

App.factory('AttachmentService', ['$http', '$q', function ($http, $q) {

    return {

        getAllAttachments: function () {
            return $http.get('/restful/attachment/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while fetching attachments');
                        return $q.reject(errResponse);
                    }
                );
        },

        createAttachment: function (attachment) {
            return $http.post('/restful/attachment/', attachment)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while creating attachment');
                        return $q.reject(errResponse);
                    }
                );
        },

        updateAttachment: function (attachment, attachmentId) {
            return $http.put('/restful/attachment/' + attachmentId, attachment)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while updating attachment');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteAttachment: function (attachmentId) {
            return $http.delete('/restful/attachment/' + attachmentId)
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting attachment');
                        return $q.reject(errResponse);
                    }
                );
        },

        deleteAllAttachments: function () {
            return $http.delete('/restful/attachment/')
                .then(
                    function (response) {
                        return response.data;
                    },
                    function (errResponse) {
                        console.error('Error while deleting attachments');
                        return $q.reject(errResponse);
                    }
                );
        }
    };
}]);
