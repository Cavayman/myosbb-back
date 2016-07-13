/**
 * Created by nataliia on 12.07.16.
 */

App.controller('EventController', ['$scope', 'EventService',

    function ($scope, EventService) {
        var eventService = this;

        eventService.event = {eventId: null, name: '', description: '', creationDate: '', filePath: ''};
        eventService.events = [];

        eventService.getAllEvents = function () {
            EventService.getAllEvents()
                .then(
                    function (data) {
                        eventService.events = data;
                    },
                    function (errResponse) {
                        console.error('error while fetching events');
                    }
                );
        };

        eventService.createEvent = function (event) {
            if (event.creationDate == null || event.creationDate.length == 0) {
                console.log('specifying date');
                event.creationDate = new Date().toJSON().slice(0, 10);
                console.log(event.creationDate);
            }
            EventService.createEvent(event)
                .then(
                    eventService.getAllEvents,
                    function (errResponse) {
                        console.error('error while creating event');
                    }
                );
        };

        eventService.updateEvent = function (event, eventId) {
            EventService.updateEvent(event, eventId)
                .then(
                    eventService.getAllEvents,
                    function (errResponse) {
                        console.error('error while updating event');
                    }
                );
        };

        eventService.deleteEvent = function (eventId) {
            EventService.deleteEvent(eventId)
                .then(
                    eventService.getAllEvents,
                    function (errResponse) {
                        console.error('error while deleting event');
                    }
                );
        };

        eventService.getAllEvents();

        eventService.submit = function () {
            if (eventService.event.eventId === null) {
                console.log('saving new event', eventService.event);
                eventService.createEvent(eventService.event);
            } else {
                var eventId = parseInt(eventService.event.eventId);
                console.log('updating event', eventService.event);
                eventService.updateEvent(eventService.event, eventId);
            }
            eventService.reset();
        };

        eventService.edit = function (eventId) {
            console.log('id to be edited: ' + eventId);
            for (var i = 0; i < eventService.events.length; i++) {
                if (eventService.events[i].eventId === eventId) {
                    eventService.event = angular.copy(eventService.events[i]);
                    break;
                }
            }
        };

        eventService.remove = function (eventId) {
            if (eventService.event.eventId === eventId) {
                eventService.reset();
            }
            eventService.deleteEvent(eventId);
        };

        eventService.reset = function () {
            eventService.event = {eventId: null, name: '', description: '', creationDate: '', filePath: ''};
            $scope.eventForm.$setPristine();
        }
    }
]);