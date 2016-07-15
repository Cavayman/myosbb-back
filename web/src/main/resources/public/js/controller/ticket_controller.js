/**
 * Created by Kris on 15.07.2016.
 */

App.controller('TicketController', ['$scope', 'TicketService',

    function ($scope, TicketService) {
        var ticketController = this;

        ticketController.ticket = {ticketId: null, name: '', description: '', time: ''};
        ticketController.tickets = [];

        ticketController.getAllTickets = function () {
            TicketService.getAllTickets()
                .then(
                    function (data) {
                        ticketController.tickets = data;
                    },
                    function (errResponse) {
                        console.error('error while fetching ticket');
                    }
                );
        };

        ticketController.createTicket = function (ticket) {
            if (ticket.time == null || ticket.time.length == 0) {
                console.log('specifying date');
                ticket.time = new Date().toJSON().slice(0, 10);
                console.log(ticket.time);
            }
            TicketService.createTicket(ticket)
                .then(
                    ticketController.getAllTickets,
                    function (errResponse) {
                        console.error('error while creating ticket');
                    }
                );
        };

        ticketController.updateTicket = function (ticket, ticketId) {
            TicketService.updateTicket(ticket, ticketId)
                .then(
                    ticketController.getAllTickets,
                    function (errResponse) {
                        console.error('error while updating ticket');
                    }
                );
        };

        ticketController.deleteTicket = function (ticketId) {
            TicketService.deleteTicket(ticketId)
                .then(
                    ticketController.getAllTickets,
                    function (errResponse) {
                        console.error('error while deleting ticket');
                    }
                );
        };

        ticketController.deleteAllTickets = function () {
            TicketService.deleteAllTickets()
                .then(
                    ticketController.getAllTickets,
                    function (errResponse) {
                        console.error('error while deleting tickets');
                    }
                );
        };

        ticketController.getAllTickets();

        ticketController.submit = function () {
            if (ticketController.ticket.ticketId === null) {
                console.log('saving new ticket', ticketController.ticket, ticketController.ticket.ticketId);
                ticketController.createTicket(ticketController.ticket);
            } else {
                var ticketId = parseInt(ticketController.ticket.ticketId);
                console.log('updating ticket', ticketController.ticket);
                ticketController.updateTicket(ticketController.ticket, ticketId);
            }
            ticketController.reset();
        };

        ticketController.edit = function (ticketId) {
            console.log('id to be edited: ' + ticketId);
            for (var i = 0; i < ticketController.tickets.length; i++) {
                if (ticketController.tickets[i].ticketId === ticketId) {
                    ticketController.ticket = angular.copy(ticketController.tickets[i]);
                    break;
                }
            }
        };

        ticketController.remove = function (ticketId) {
            if (ticketController.ticket.ticketId === ticketId) {
                ticketController.reset();
            }
            ticketController.deleteTicket(ticketId);
        };

        ticketController.reset = function () {
            ticketController.ticket = {ticketId: null, name: '', description: '', time: ''};
            $scope.ticketForm.$setPristine();
        }
    }
]);