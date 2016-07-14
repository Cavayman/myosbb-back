

App.controller('ReportController', ['$scope', 'ReportService',

	function($scope, ReportService){

			var rs = this;
			rs.report = {reportId:null, name:'', description:'', creationDate: '', filePath:''};
			rs.reports =[];
			rs.searchParam=null;

			rs.getAllReports = function(){

					ReportService.getAllReports()
					.then(

						function(d){
							rs.reports = d;
						},


						function(errResponse){
							console.error('error while fetching reports');
						}


					);

			};

			rs.createReport = function(report){

				if(report.creationDate == null || report.creationDate.length == 0){
					console.log('specifying date');
					report.creationDate = new Date().toJSON().slice(0,10);
					console.log(report.creationDate);
				}


				ReportService.createReport(report)
				.then(

					rs.getAllReports,

					function(errResponse){
							console.error('error while creating report');
						}


					);


			};

			rs.updateReport = function(report, reportId){

					ReportService.updateReport(report, reportId)
						.then(

							rs.getAllReports,

							function(errResponse){
								console.error('error while updating report');
							}

						);



			};


			rs.searchBy = function(searchParam){

				if(searchParam == null || searchParam.trim().length == 0){

					throw e;

				}else{

				ReportService.searchBy(searchParam)
					.then(

						function(s){
							rs.reports = s;
						},

						function(errResponse){
							console.error("error while searching");
						}
					)

				}


			};


			rs.deleteReport = function(reportId){


				ReportService.deleteReport(reportId)
				.then(

						rs.getAllReports,

						function(errResponse){
							console.error('error while deleting report');
						}


					);

			};

			rs.getAllReports();

			rs.search = function(){

				console.log('passing '+rs.searchParam);
				try {
					rs.searchBy(rs.searchParam);
				}catch(err){
					console.error('error on search, the searchParam is null or empty');
				}
			};

			rs.submit = function(){

				if(rs.report.reportId === null){

					console.log('saving new report', rs.report);

					rs.createReport(rs.report);

				}else{

					var reportId = parseInt(rs.report.reportId);

					console.log('updating report', rs.report);

					rs.updateReport(rs.report, reportId);

				}

			rs.reset();

			};


			rs.edit = function(reportId){

				console.log('id to be edited: '+reportId);

				for(var i=0; i<rs.reports.length; i++){

					if(rs.reports[i].reportId === reportId){
						rs.report = angular.copy(rs.reports[i]);
						break;
					}
				}


			};

			rs.remove = function(reportId){


				if(rs.report.reportId === reportId){
					rs.reset();
				}

				rs.deleteReport(reportId);

			};


			rs.reset = function(){

				rs.report = {reportId: null, name:'', description:'', creationDate:'', filePath: ''};
				$scope.reportForm.$setPristine();

			}





}


	]);