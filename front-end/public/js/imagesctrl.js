var imagesCtrl = angular.module('imagesCtrl', ['imByEx']);

imagesCtrl.controller('imagesCtrl', ['$rootScope','$scope','$http','$q','config',
    function($rootScope,$scope, $http, $q, config){
			$scope.searchImages = function(){
				var image = $scope.imageEx;
				console.log(image);
	      var url = 'http://' + config.serverIp + ':' + config.serverPort + '/images' ; 
	      var fd = new FormData();
	      fd.append('image', image);
	      $http.post(url, fd, {
	          transformRequest: angular.identity,
	          headers: {'Content-Type': undefined}
	      })
	      .success(function(data){
	         $scope.images = data.images;
	         console.log(data);
	      })
	      .error(function(error){
	         console.log(error);
	      });
  	};
		}
]);