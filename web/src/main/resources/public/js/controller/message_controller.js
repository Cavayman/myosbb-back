/**
 * Created by Kris on 13.07.2016.
 */
App.controller('MessageController', ['$scope', 'MessageService',

    function ($scope, MessageService) {


        var mes = this;

        mes.message = {messageId: null, description: '', messag: '', date: ''};
        mes.messages = [];


        mes.getAllMessages = function () {

            MessageService.getAllMessages()
                .then(
                    function (d) {
                        mes.message = d;
                    },


                    function (errResponse) {
                        console.error('error while fetching messages');
                    }
                );


        };

        mes.createMessage = function (message) {

            if (message.creationDate == null || message.creationDate.length == 0) {
                console.log('specifying date');
                message.creationDate = new Date().toJSON().slice(0, 10);
                console.log(message.creationDate);
            }


            MessageService.createMessage(message)
                .then(
                    mes.getAllMessages,

                    function (errResponse) {
                        console.error('error while creating event');
                    }
                );


        };

        mes.updateMessage = function (message, messageId) {

            MessageService.updateMessage(message, messageId)
                .then(
                    mes.getAllMessage,

                    function (errResponse) {
                        console.error('error while updating message');
                    }
                );


        };


        mes.deleteMessage = function (messageId) {


            MessageService.deleteEvent(messageId)
                .then(
                    mes.getAllMessage,

                    function (errResponse) {
                        console.error('error while deleting message');
                    }
                );

        };


        mes.getAllMessages();


        mes.submit = function () {

            if (mes.message.messageId === null) {

                console.log('saving new event', mes.message);

                mes.createEvent(rs.message);

            } else {

                var eventId = parseInt(mes.message.messageId);

                console.log('updating event', mes.message);

                mes.updateEvent(rs.message, eventId);

            }

            mes.reset();

        };


        mes.edit = function (messageId) {

            console.log('id to be edited: ' + messageId);

            for (var i = 0; i < mes.events.length; i++) {

                if (mes.messages[i].messageId === messageId) {
                    mes.message = angular.copy(mes.messages[i]);
                    break;
                }
            }


        };

        mes.remove = function (messageId) {


            if (mes.message.messageId === messageId) {
                mes.reset();
            }

            mes.deleteMessage(messageId);

        };


        mes.reset = function () {

            mes.event = {eventId: null, name: '', description: '', creationDate: '', filePath: ''};
            $scope.eventForm.$setPristine();

        }


    }


]);