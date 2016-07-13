/**
 * Created by nataliia on 12.07.16.
 */


App.controller('EventController', ['$scope', 'EventService',

    function($scope, EventService){


        var rs = this;

        rs.event = {eventId:null, name:'', description:'', creationDate: '', filePath:''};
        rs.events =[];


        rs.getAllEvents = function(){

            EventService.getAllEvents()
                .then(

                    function(d){
                        rs.events = d;
                    },


                    function(errResponse){
                        console.error('error while fetching events');
                    }


                );


        };

        rs.createEvent = function(event){

            if(event.creationDate == null || event.creationDate.length == 0){
                console.log('specifying date');
                event.creationDate = new Date().toJSON().slice(0,10);
                console.log(event.creationDate);
            }


            EventService.createEvent(event)
                .then(

                    rs.getAllEvents,

                    function(errResponse){
                        console.error('error while creating event');
                    }


                );


        };

        rs.updateEvent = function(event, eventId){

            EventService.updateEvent(event, eventId)
                .then(

                    rs.getAllEvents,

                    function(errResponse){
                        console.error('error while updating event');
                    }

                );



        };


        rs.deleteEvent = function(eventId){


            EventService.deleteEvent(eventId)
                .then(

                    rs.getAllEvents,

                    function(errResponse){
                        console.error('error while deleting event');
                    }


                );

        };


        rs.getAllEvents();



        rs.submit = function(){

            if(rs.event.eventId === null){

                console.log('saving new event', rs.event);

                rs.createEvent(rs.event);

            }else{

                var eventId = parseInt(rs.event.eventId);

                console.log('updating event', rs.event);

                rs.updateEvent(rs.event, eventId);

            }

            rs.reset();

        };


        rs.edit = function(eventId){

            console.log('id to be edited: '+eventId);

            for(var i=0; i<rs.events.length; i++){

                if(rs.events[i].eventId === eventId){
                    rs.event = angular.copy(rs.events[i]);
                    break;
                }
            }


        };

        rs.remove = function(eventId){


            if(rs.event.eventId === eventId){
                rs.reset();
            }

            rs.deleteEvent(eventId);

        };


        rs.reset = function(){

            rs.event = {eventId: null, name:'', description:'', creationDate:'', filePath: ''};
            $scope.eventForm.$setPristine();

        }





    }


]);