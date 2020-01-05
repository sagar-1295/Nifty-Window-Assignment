var niftyWindow = angular.module('niftyWindow', [ 'ngRoute', 'ngMaterial',
		'ngAnimate', 'ngAria' ]);

niftyWindow.controller('niftyController', function($scope, $http, $rootScope) {

	$scope.fetchNiftyData = function() {
		$http({
			method : 'GET',
			url : '/nifty'
		}).then(function(response) {
			$scope.niftyData = response.data;
			console.log($scope.niftyData)
		}, function(error) {
			console.log("error in loading data")
		})
	}

});
