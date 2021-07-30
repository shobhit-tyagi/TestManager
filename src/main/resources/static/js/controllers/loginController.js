angular.module('tm-app').controller("loginController", function ($scope, $state, $rootScope, $timeout, localStorageService) {
	$scope.userId = "A10000";
	$scope.userPass = "1";
	
	if(localStorageService.get("tmCookie")) {
		$rootScope.userBean = localStorageService.get("tmCookie").user;
		$state.go('app.dboard');
	}
	
	$scope.login = function() {
		var userBean = {
			"userId" : $scope.userId,
			"userPass" : $scope.userPass
		};
		$.ajax({
		    url: '/tmLogin/validateLogin',
		    type: 'POST',
			contentType: 'application/json',
		    dataType: 'json',
			data: JSON.stringify(userBean),
		    async: false,
		    success: function(data) {
		    	$rootScope.userBean = data;
		    	var tmCookie = {
		    		"user" : data,
		    		"login_time" : new Date()
		    	};
		    	localStorageService.set("tmCookie", tmCookie);
		    	$rootScope.panelMessage = "Welcome back " + data.userName + ". Nice to see you again!!";
		    	$rootScope.successBoxFlag = true;
		    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
		    	$state.go('app.dboard');
		    }
		}).fail(function() {
	    	$rootScope.panelMessage = "Could not validate the user.";
	    	$rootScope.errorBoxFlag = true;
	    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
		});
	};
	
	$scope.loginFacebook = function() {
		$rootScope.panelMessage = "This feature is currently not supported";
    	$rootScope.errorBoxFlag = true;
    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
	};
	
	$scope.register = function() {
		$rootScope.panelMessage = "This feature is currently not supported";
    	$rootScope.errorBoxFlag = true;
    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
	};
});