/* creating angularJS service to communicate with server side, here defined by Spring Rest Controller */

App.factory('ProviderService', [ '$http', '$q', function($http, $q){
			return{
					getAllProviders: function(){
						return $http.get('restful/provider/all')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching providers');
                                        return $q.reject(errResponse);
                                    }
                            );
					},

					createProvider : function(provider){
						return $http.post('restful/provider/', provider)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating provider');
                                        return $q.reject(errResponse);
                                    }
                            );
					},

					updateProvider: function(provider, providerId){
						return $http.put('restful/provider/'+providerId, provider)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating provider');
                                        return $q.reject(errResponse);
                                    }
                            );
					},
					deleteProvider: function(providerId){
							return $http.delete('restful/provider/'+providerId)
								.then(

									function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting provider');
                                        return $q.reject(errResponse);
                                    }

                                );

							},

                    searchBy: function(searchParam){

                        return $http.get('restful/provider/find?name='+searchParam)
                            .then(

                                function(response){
                                    return response.data;
                                },

                                function(errResponse){
                                    console.error('Error while searching for provider');
                                    return $q.reject(errResponse);
                                }


                            );
                    }
					};

			}
])