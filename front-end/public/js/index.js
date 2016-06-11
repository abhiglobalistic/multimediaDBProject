var imByEx = angular.module('imByEx', ['imagesCtrl']);

imByEx.constant('config', {
    serverIp : '127.0.0.1',
    serverPort : '3000'
});

imByEx.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);