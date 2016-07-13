/* creating angularJS service to communicate with server side, here defined by Spring Rest Controller */

App.factory('ReportService', [ '$http', '$q', function($http, $q){
			return{
					getAllReports: function(){
						return $http.get('restful/report/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching reports');
                                        return $q.reject(errResponse);
                                    }
                            );
					},

					createReport : function(report){
						return $http.post('restful/report/', report)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating report');
                                        return $q.reject(errResponse);
                                    }
                            );
					},

					updateReport: function(report, reportId){
						return $http.put('restful/report/'+reportId, report)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating report');
                                        return $q.reject(errResponse);
                                    }
                            );
					},
					deleteReport: function(reportId){
							return $http.delete('restful/report/'+reportId)
								.then(

									function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting report');
                                        return $q.reject(errResponse);
                                    }

                                );

							},
					};

			}
])