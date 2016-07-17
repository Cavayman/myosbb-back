/**
 * Created by Kris on 15.07.2016.
 */

App.controller('MessageController', ['$scope', 'MessageService',

    function ($scope, MessageService) {
        var messageController = this;

        messageController.messag = {messageId: null, description: '', message:'', time: ''};
        messageController.messages = [];

        messageController.getAllMessages = function () {
            MessageService.getAllMessages()
                .then(
                    function (data) {
                        messageController.messages = data;
                    },
                    function (errResponse) {
                        console.error('error while fetching message');
                    }
                );
        };

        messageController.createMessage = function (messag) {
            if (messag.time == null || messag.time.length == 0) {
                console.log('specifying date');
                messag.time = new Date().toJSON().slice(0, 10);
                console.log(messag.time);
            }
            MessageService.createMessage(messag)
                .then(
                    messageController.getAllMessages,
                    function (errResponse) {
                        console.error('error while creating messag');
                    }
                );
        };

        messageController.updateMessage = function (messag, messageId) {
            MessageService.updateMessage(messag, messageId)
                .then(
                    messageController.getAllMessages,
                    function (errResponse) {
                        console.error('error while updating messag');
                    }
                );
        };

        messageController.deleteMessage = function (messageId) {
            MessageService.deleteMessage(messageId)
                .then(
                    messageController.getAllMessages,
                    function (errResponse) {
                        console.error('error while deleting messag');
                    }
                );
        };

        messageController.deleteAllMessages = function () {
            MessageService.deleteAllMessages()
                .then(
                    messageController.getAllMessages,
                    function (errResponse) {
                        console.error('error while deleting messages');
                    }
                );
        };
        messageController.getAllMessages();


        messageController.submit = function () {
            if (messageController.messag.messageId === null) {
                console.log('saving new messag', messageController.messag, messageController.messag.messageId);
                messageController.createMessage(messageController.messag);
            } else {
                var messageId = parseInt(messageController.messag.messageId);
                console.log('updating messag', messageController.messag);
                messageController.updateMessage(messageController.messag, messageId);
            }
            messageController.reset();
        };

        messageController.edit = function (messageId) {
            console.log('id to be edited: ' + messageId);
            for (var i = 0; i < messageController.messages.length; i++) {
                if (messageController.messages[i].messageId === messageId) {
                    messageController.messag = angular.copy(messageController.messages[i]);
                    break;
                }
            }
        };

        messageController.remove = function (messageId) {
            if (messageController.messag.messageId === messageId) {
                messageController.reset();
            }
            messageController.deleteMessage(messageId);
        };

        messageController.reset = function () {
            messageController.messag = {messageId: null, name: '', description: '', time: ''};
            $scope.messageForm.$setPristine();
        }
    }
]);