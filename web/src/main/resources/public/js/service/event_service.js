/**
 * Created by nataliia on 12.07.16.
 */

App.factory('EventService', [ '$http', '$q', function($http, $q){

    return{


        getAllEvents: function(){

            return $http.get('http://localhost:52430/event/')
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching events');
                        return $q.reject(errResponse);
                    }
                );



        },


        createEvent : function(event){


            return $http.post('http://localhost:52430/event/', event)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating event');
                        return $q.reject(errResponse);
                    }
                );


        },


        updateEvent: function(event, eventId){

            return $http.put('http://localhost:52430/event/'+eventId, event)
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while updating event');
                        return $q.reject(errResponse);
                    }
                );

        },


        deleteEvent: function(eventId){

            return $http.delete('http://localhost:52430/event/'+eventId)
                .then(

                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while deleting event');
                        return $q.reject(errResponse);
                    }



                );


        },


    };



}

])
