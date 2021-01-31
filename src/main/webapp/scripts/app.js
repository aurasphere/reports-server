var app = angular.module("reports", [ "ngRoute", "ngCookies", "ngAnimate"]);
app.config(function($routeProvider) {
	var lastIfAdmin = function($rootScope, first, last) {
		return $rootScope.isAdmin ? last : first;
	}
	var lastIfContainsQuery = function($location, first, last) {
		return angular.equals({}, $location.search()) ? first : last;
	}
	$routeProvider.when("/", {
		resolveRedirectTo : function($rootScope) {return lastIfAdmin($rootScope, "/timesheet", "/admin-report-summary")}
	}).when("/login", {
		templateUrl : "views/login.html"
	}).when("/admin-report-summary", {
		templateUrl : "views/admin-report-summary.html"
	}).when("/timesheet", {
		resolveRedirectTo : function($location, $httpParamSerializer) {return lastIfContainsQuery($location, "/timesheet-user", "/timesheet-admin?" + $httpParamSerializer($location.search()))},
	}).when("/timesheet-user", {
		templateUrl : "views/timesheet.html",
		controller: "timesheetUserController"
	}).when("/timesheet-admin", {
		templateUrl : "views/timesheet.html",
		controller: "timesheetAdminController"
	}).when("/expense-account", {
		resolveRedirectTo : function($location, $httpParamSerializer) {return lastIfContainsQuery($location, "/expense-account-user", "/expense-account-admin?" + $httpParamSerializer($location.search()))},
	}).when("/expense-account-user", {
		templateUrl : "views/expense-account.html",
		controller: "expenseAccountUserController"
	}).when("/expense-account-admin", {
		templateUrl : "views/expense-account.html",
		controller: "expenseAccountAdminController"
	}).when("/password-recovery", {
		templateUrl : "views/password-recovery.html"
	}).when("/registration-confirmation", {
		templateUrl : "views/password-recovery.html"
	}).when("/admin-user-list", {
		templateUrl : "views/admin-user-list.html"
	}).when("/error", {
		templateUrl : "views/error.html"
	}).when("/404", {
		templateUrl : "views/404.html"
	}).otherwise({
		templateUrl : "views/404.html"
	});
});