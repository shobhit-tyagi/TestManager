var module = angular.module('tm-app', ['ui.router', 'ngDialog', 'pascalprecht.translate', 'ui.bootstrap',
                                       'angular-table', 'angularTreeview', 'ngAnimate', 'cgBusy', 'ngTouch',
                                       'angucomplete-alt', 'tm-confirm-button', 'LocalStorageModule', 
                                       'tm-tooltip']);

//I18N & L10N
module.config(function ($translateProvider) {
	
	$translateProvider.useStaticFilesLoader({
	    prefix: 'locale/messages-',
	    suffix: '.json'
	});
	
	$translateProvider.preferredLanguage('en');
});

//ROOT SCOPE ELEMENTS
module.run(function($rootScope) {
	$rootScope.successBoxFlag = false;
    $rootScope.errorBoxFlag = false;
    $rootScope.panelMessage = "";
    $rootScope.delay = 0;
    $rootScope.minDuration = 0;
    $rootScope.message = 'Loading...';
    $rootScope.backdrop = true;
    $rootScope.promise = null;
	$rootScope.userBean = null;
	
	$rootScope.autoHide = function() {
	    $rootScope.successBoxFlag = false;
	    $rootScope.errorBoxFlag = false;
	    $rootScope.panelMessage = "";
	};
});

//STATE ROUTES
module.config(function ($stateProvider, $urlRouterProvider, $provide) {
	$stateProvider.state('app',
		{
			url: "/",
			views: {
				'content' : {
					templateUrl : 'pages/login.html',
					controller : 'loginController'
				},
				'userInfo' : {
					templateUrl : 'pages/blankUserInfo.html',
					controller : 'userInfoController'
				},
				'messageBox' : {
					templateUrl : 'pages/messageBox.html',
					controller : 'rootController'
				},
				'footer' : {
					templateUrl : 'pages/footer.html',
					controller : 'rootController'
				}
			}
		}
	)
	.state('app.dboard',
		{
			url: "",
			views: {
				'userInfo@' : {
					templateUrl : 'pages/userInfo.html',
					controller : 'userInfoController'
				},
				'content@' : {
					templateUrl : 'pages/dashboard.html',
					controller : 'dashboardController'
				}
			}
		}
	)
	.state('app.dboard.calendar',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/calendar.html',
					controller : 'calendarController'
				}
			}
		}
	)
	.state('app.dboard.report',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/reports.html',
					controller : 'reportController'
				}
			}
		}
	)
	.state('app.dboard.project',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/projects.html',
					controller : 'projectController'
				}
			}
		}
  	)
	.state('app.dboard.about',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/about.html',
					controller : 'internalController'
				}
			}
		}
	)
	.state('app.dboard.notification',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/notifications.html',
					controller : 'notificationController'
				}
			}
		}
	)
	.state('app.dboard.log',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/logs.html',
					controller : 'loggerController'
				}
			}
		}
	)
	.state('app.dboard.notification.archived',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/archivedNotifications.html',
					controller : 'notificationController'
				}
			}
		}
	)
  	.state('app.dboard.module',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/modules.html',
					controller : 'moduleController'
				}
			}
		}
  	)
  	.state('app.dboard.module.release',
		{
			url: "",
			views: {
				'component' : {
					templateUrl : 'pages/releases.html',
					controller : 'releaseController'
				}
			}
		}
  	)
  	.state('app.dboard.module.issue',
		{
			url: "",
			views: {
				'component' : {
					templateUrl : 'pages/issues.html',
					controller : 'issueController'
				}
			}
		}
  	)
  	.state('app.dboard.settings',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/settings.html'
				}
			}
		}
  	)
	.state('app.dboard.help',
		{
			url: "",
			views: {
				'content@' : {
					templateUrl : 'pages/help.html'
				}
			}
		}
  	);
	$urlRouterProvider.otherwise("/");
});