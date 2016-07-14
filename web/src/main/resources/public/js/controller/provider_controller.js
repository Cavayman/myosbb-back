

App.controller('ProviderController', ['$scope', 'ProviderService',

	function($scope, ProviderService){

			var rs = this;
			rs.provider = {providerId:null, name:'', description:'', logoUrl: '', contracts:[], bills:[]};
			rs.providers =[];
			rs.searchParam=null;

			rs.getAllProviders = function(){

					ProviderService.getAllProviders()
					.then(

						function(d){
							rs.providers = d;
						},


						function(errResponse){
							console.error('error while fetching providers');
						}


					);

			};

			rs.createProvider = function(provider){

				if(provider.creationDate == null || provider.creationDate.length == 0){
					console.log('specifying date');
					provider.creationDate = new Date().toJSON().slice(0,10);
					console.log(provider.creationDate);
				}


				ProviderService.createProvider(provider)
				.then(

					rs.getAllProviders,

					function(errResponse){
							console.error('error while creating provider');
						}


					);


			};

			rs.updateProvider = function(provider, providerId){

					ProviderService.updateProvider(provider, providerId)
						.then(

							rs.getAllProviders,

							function(errResponse){
								console.error('error while updating provider');
							}

						);



			};


			rs.searchBy = function(searchParam){

				if(searchParam == null || searchParam.trim().length == 0){

					throw e;

				}else{

				ProviderService.searchBy(searchParam)
					.then(

						function(s){
							rs.providers = s;
						},

						function(errResponse){
							console.error("error while searching");
						}
					)

				}


			};


			rs.deleteProvider = function(providerId){


				ProviderService.deleteProvider(providerId)
				.then(

						rs.getAllProviders,

						function(errResponse){
							console.error('error while deleting provider');
						}


					);

			};

			rs.getAllProviders();

			rs.search = function(){

				console.log('passing '+rs.searchParam);
				try {
					rs.searchBy(rs.searchParam);
				}catch(err){
					console.error('error on search, the searchParam is null or empty');
				}
			};

			rs.submit = function(){

				if(rs.provider.providerId === null){

					console.log('saving new provider', rs.provider);

					rs.createProvider(rs.provider);

				}else{

					var providerId = parseInt(rs.provider.providerId);

					console.log('updating provider', rs.provider);

					rs.updateProvider(rs.provider, providerId);

				}

			rs.reset();

			};


			rs.edit = function(providerId){

				console.log('id to be edited: '+providerId);

				for(var i=0; i<rs.providers.length; i++){

					if(rs.providers[i].providerId === providerId){
						rs.provider = angular.copy(rs.providers[i]);
						break;
					}
				}


			};

			rs.remove = function(providerId){


				if(rs.provider.providerId === providerId){
					rs.reset();
				}

				rs.deleteProvider(providerId);

			};


			rs.reset = function(){

				rs.provider = {providerId: null, name:'', description:'', creationDate:'', filePath: ''};
				$scope.providerForm.$setPristine();

			}





}


	]);