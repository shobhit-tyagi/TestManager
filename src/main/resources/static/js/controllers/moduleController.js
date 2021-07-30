angular.module('tm-app').controller("moduleController", function ($scope, $rootScope, $state, ngDialog, $timeout) {
	$.ajax({
        url: '/tmModule/getProjectModules?id='+$rootScope.projectId,
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function(data) {
        	$scope.projectModules = [];
        	for(var index = 0; index < data.length; index ++) {
        		var temp = null;
        		if(index == 0) {
        			$rootScope.selectedModule = data[index].id;
        			temp = {"open" : true, "module" : data[index]};
        		} else {
        			temp = {"open" : false, "module" : data[index]};
        		}
        		$scope.projectModules.push(temp);
        	}
        	if(data.length == 0) {
        		$scope.emptyListMessage = "This project does not contain any modules. Lets add some?";
        	} else {
        		$scope.emptyListMessage = "";
        	}
        	$state.go('app.dboard.module.'+$rootScope.selectedSubModule);
        }
    }).fail(function() {
    	$rootScope.panelMessage = "Could not retrieve the project modules at this moment.";
    	$rootScope.errorBoxFlag = true;
    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
    });

	$scope.openAddModuleBox = function(projectBean) {
		ngDialog.open({
			template: 'addModule',
			className: 'ngdialog-theme-default addModule',
			scope: $scope,
			preCloseCallback: function(value) {
				return true;
			}
		});
	};
	
	$scope.addModuleToProject = function(moduleBean) {
		moduleBean.projId = $rootScope.projectId;
		$.ajax({
	        url: '/tmModule/addModuleToProject',
	        type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(moduleBean),
			dataType: 'json',
	        async: false,
	        success: function(data) {
	        	for(var index = 0; index < $scope.projectModules.length; index ++) {
	        		if($scope.projectModules[index].module.id == $rootScope.selectedModule) {
	        			$scope.projectModules[index].open = false;
	        			break;
	        		}
	        	}
	        	var temp = null;
        		$rootScope.selectedModule = data.id;
        		temp = {"open" : true, "module" : data};
	        	$scope.projectModules.push(temp);
	        	ngDialog.close();
	        	$rootScope.panelMessage = "New module added successfully.";
		    	$rootScope.successBoxFlag = true;
		    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
	        }
	    }).fail(function() {
	    	ngDialog.close();
	    	$rootScope.panelMessage = "Could not add a project module at this moment.";
	    	$rootScope.errorBoxFlag = true;
	    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
	    });
	};
	
	$scope.getModuleComponents = function(moduleId) {
		for(var index = 0; index < $scope.projectModules.length; index ++) {
			if($scope.projectModules[index].module.id == moduleId) {
				$scope.projectModules[index].open = true;
			} else {
				$scope.projectModules[index].open = false;
			}
		}
		$rootScope.selectedModule = moduleId;
		$state.reload('app.dboard.module.'+$rootScope.selectedSubModule);
	};
	
	$scope.openEditModuleBox = function(moduleBean) {
		ngDialog.open({
			template: 'editModule',
			className: 'ngdialog-theme-default editModule',
			data: moduleBean.module,
			scope: $scope,
			preCloseCallback: function(value) {
				return true;
			}
		});
	}
	
	$scope.deleteModule = function(moduleId) {
		$.ajax({
	        url: '/tmModule/deleteModule?id='+moduleId,
	        type: 'POST',
	        async: false,
	        success: function(data) {
	        	var allClosed = true;
	        	for(var index = 0; index < $scope.projectModules.length; index ++) {
	        		if($scope.projectModules[index].module.id == moduleId) {
	        			$scope.projectModules.splice(index, 1);
	        			break;
	        		} else if($scope.projectModules[index].open) {
	        			allClosed = false;
	        		}
	        	}
	        	if($scope.projectModules.length > 0 && allClosed) {
	        		$scope.projectModules[0].open = true;
	        		$rootScope.selectedModule = $scope.projectModules[0].module.id;
	        		$state.reload('app.dboard.module.issue');
	        	}
	        	ngDialog.close();
	        	$rootScope.panelMessage = "Module deleted successfully.";
		    	$rootScope.successBoxFlag = true;
		    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
	        }
	    }).fail(function() {
	    	ngDialog.close();
	    	$rootScope.panelMessage = "Could not delete the project module at this moment.";
	    	$rootScope.errorBoxFlag = true;
	    	$timeout( function(){ $rootScope.autoHide(); }, 2000);
	    });
	}
});