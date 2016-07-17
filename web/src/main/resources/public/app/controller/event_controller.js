/**
 * Created by nataliia on 12.07.16.
 */

App.controller('EventController', ['$scope', 'EventService',

    function ($scope, EventService) {
        var eventController = this;

        eventController.event = {eventId: null, name: '', author: '', description: '', date: '', repeat: ''};
        eventController.events = [];

        eventController.getAllEvents = function () {
            EventService.getAllEvents()
                .then(
                    function (data) {
                        eventController.events = data;
                    },
                    function (errResponse) {
                        console.error('error while fetching events');
                    }
                );
        };

        eventController.createEvent = function (event) {
            if (event.date == null || event.date.length == 0) {
                console.log('specifying date');
                event.date = new Date().toJSON().slice(0, 10);
                console.log(event.date);
            }
            EventService.createEvent(event)
                .then(
                    eventController.getAllEvents,
                    function (errResponse) {
                        console.error('error while creating event');
                    }
                );
        };

        eventController.updateEvent = function (event, eventId) {
            EventService.updateEvent(event, eventId)
                .then(
                    eventController.getAllEvents,
                    function (errResponse) {
                        console.error('error while updating event');
                    }
                );
        };

        eventController.deleteEvent = function (eventId) {
            EventService.deleteEvent(eventId)
                .then(
                    eventController.getAllEvents,
                    function (errResponse) {
                        console.error('error while deleting event');
                    }
                );
        };

        eventController.deleteAllEvents = function () {
            EventService.deleteAllEvents()
                .then(
                    eventController.getAllEvents,
                    function (errResponse) {
                        console.error('error while deleting events');
                    }
                );
        };

        eventController.getAllEvents();

        eventController.submit = function () {
            if (eventController.event.eventId === null) {
                console.log('saving new event', eventController.event);
                eventController.createEvent(eventController.event);
            } else {
                var eventId = parseInt(eventController.event.eventId);
                console.log('updating event', eventController.event);
                eventController.updateEvent(eventController.event, eventId);
            }
            eventController.reset();
        };

        eventController.edit = function (eventId) {
            console.log('id to be edited: ' + eventId);
            for (var i = 0; i < eventController.events.length; i++) {
                if (eventController.events[i].eventId === eventId) {
                    eventController.event = angular.copy(eventController.events[i]);
                    break;
                }
            }
        };

        eventController.remove = function (eventId) {
            if (eventController.event.eventId === eventId) {
                eventController.reset();
            }
            eventController.deleteEvent(eventId);
        };

        eventController.reset = function () {
            eventController.event = {eventId: null, name: '',author:'', description: '', date: '', repeat: ''};
            $scope.eventForm.$setPristine();
        }
    }
]);