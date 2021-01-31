// Shared methods in app scope.
app.controller("reportsController", function($scope, $rootScope, $location) {
	// Handles http errors.
	$scope.handleHttpError = function(response) {
		if (response.status === 401) {
			$location.path("/login");
		} else if (response.status === 403) {
			$location.path("/404");
		} else {
			$location.path("/error");
		}
	}
	
	// TODO: switch between "" for PROD and "/reports" in DEV
	$scope.appBasePath = "/reports";
});

app
		.controller(
				'loginController',
				function($scope, $rootScope, $http, $location, $cookies,
						$routeParams) {
					// Shows a confirmation error if everything went well.
					if ($routeParams.setPassword) {
						$scope.message = "Succesfully changed password. You can now login.";
					}

					// Performs login.
					$scope.doLogin = function() {
						var url = $scope.appBasePath + "/rest/login?username="
								+ $scope.email + "&password="
								+ md5($scope.password) + "&remember-me="
								+ $scope.rememberMe;
						$http
								.post(url)
								.then(
										function(response) {
											if (response.data.outcome) {
												$scope.loginError = false;
												$rootScope.isAdmin = response.data.user.authorities
														.some(function(element) {
															return element.authority === "ROLE_ADMIN";
														});
												$location.path("/");
											} else {
												$scope.loginError = true;
												$scope.message = response.data.errorMessage;
											}
										}, $scope.handleHttpError);
					}

					// Shows the option to recover a password.
					$scope.showPasswordRecovery = function() {
						return $scope.loginError === true
								&& $scope.message === "Wrong Email or password."
								&& $scope.loginForm.email.$valid;
					}

					// Sends a password recovery mail.
					$scope.sendRecoverPasswordEmail = function() {
						$http
								.get(
										$scope.appBasePath + "/rest/user/sendRecoverPasswordEmail?email="
												+ $scope.email)
								.then(
										function(response) {
											if (response.data.outcome) {
												$scope.loginError = false;
												$scope.message = "We sent an email to the last address inserted with the instruction to recover your password.";
											} else {
												$scope
														.handleHttpError(response);
											}
										}, $scope.handleHttpError);
					}
				});

app.controller("passwordRecoveryController", function($scope, $routeParams,
		$location, $http) {
	$scope.message = "Your new password must be at least 8 characters long.";
	$scope.passwordRecovery = function() {
		var email = $routeParams.email;
		var token = $routeParams.token;
		// Error if no parameters are passed.
		if (!email || !token) {
			$location.path("/error");
			return;
		}

		$http.post($scope.appBasePath + "/rest/user/passwordRecovery", {
			email : email,
			token : token,
			newPassword : md5($scope.newPassword)
		}).then(function(response) {
			if (response.data.outcome) {
				$location.search({
					setPassword : true
				});
				$location.path("/login");
			} else {
				$scope.message = response.data.errorMessage;
				$scope.error = true;
			}
		}, $scope.handleHttpError);
	}
});

app.controller('navbarController', function($scope, $http, $location,
		$rootScope) {
	// Performs logout.
	$scope.doLogout = function() {
		$http.post($scope.appBasePath + "/rest/logout").then(function() {
			$location.path("/login")
		}, $scope.handleHttpError);
	}
	// Whether to show toolbar or not.
	$scope.showToolbar = function() {
		return [ "/login", "/error", "/password-recovery", "/404" ]
				.indexOf($location.path()) === -1;
	}

	// Whether to show the user toolbar or not.
	$scope.showUserToolbar = function() {
		return [ "/admin-report-summary", "/admin-user-list"]
			.indexOf($location.path()) === -1;
	}

	// Whether to show the admin toolbar or not.
	$scope.showAdminToolbar = function() {
		return $rootScope.isAdmin;
	}

	// Whether the page is the same as the name passed as argument or not.
	$scope.isPage = function(pageName) {
		return $location.path().startsWith(pageName);
	}

	$scope.redirectWithQuery = function(pageName) {
		$location.path(pageName);
	}
});

app.controller("monthSelectorController", function($scope, $http, $rootScope,
		dateService) {
	// Array of months.
	$scope.months = dateService.months;

	// Current year and month to determine the current report.
	$scope.reportYear = dateService.reportYear;
	$scope.reportMonth = dateService.reportMonth;

	// Init the report-month-slider.
	$scope.initCarousel = function() {
		$('.carousel').carousel({
			interval : false
		}).on("slid.bs.carousel", function(event) {

			// TODO: check for modification with ng-touched and
			// save the timesheet if true. Would also be good if
			// saveTimesheet did that itself to prevent too many
			// db calls.
			// $scope.saveTimesheet();

			dateService.reportMonth = event.to;

			// Check whether the year should be incremented or
			// decremented.
			if (event.to === 0 && event.from === 11) {
				$scope.reportYear = ++dateService.reportYear;
			}

			if (event.to === 11 && event.from === 0) {
				$scope.reportYear = --dateService.reportYear;
			}
			$scope.$emit("monthSelectorChanged");
		});
		$scope.$emit("monthSelectorInit");
	}

	// Fires an event.
	$scope.monthChangedStart = function() {
		$scope.$emit("monthSelectorChangedStart");
	}

});

app
		.controller(
				"timesheetAdminController",
				[
						'$scope',
						'$http',
						'$rootScope',
						'timesheetService',
						'dateService',
						'$routeParams',
						'$location',
						function($scope, $http, $rootScope, timesheetService,
								dateService, $routeParams, $location) {
							// View variables.
							$rootScope.isAdmin = true;
							$scope.loading = true;
							$scope.isHoliday = dateService.isHoliday;
							$scope.reportLocked = true;
							$scope.getDaysForCurrentMonth = dateService.getDaysForCurrentMonth;
							$scope.showAdminReport = true;
							$scope.formatRowName = timesheetService.formatRowName;

							var username = $routeParams.username;
							var userid = $routeParams.userid;

							// If any parameter is missing return to the
							// report-summary page.
							if (!username || !userid) {
								$location.path("/admin-report-summary");
								return;
							}

							$scope.detailedReportName = function() {
								return [
										username,
										dateService.months[dateService.reportMonth],
										dateService.reportYear ].join(" ");
							}

							// Loads the report.
							$http
									.get(
											$scope.appBasePath + "/rest/report/getUserReportForAdmin?date="
													+ dateService
															.formattedReportDate()
													+ "&user=" + userid)
									.then(
											function(response) {
												if (response.data.outcome) {
													$scope.timesheet = timesheetService
															.makeBindableHoursArray(response.data.report.timesheet);
													$scope.loading = false;
												} else {
													$scope
															.handleHttpError(response);
												}
											}, $scope.handleHttpError);

						}

				]);

app
		.controller(
				"timesheetUserController",
				function($scope, $http, $rootScope, timesheetService,
						dateService, alertService) {
					// View variables.
					$scope.isHoliday = dateService.isHoliday;
					$scope.getDaysForCurrentMonth = dateService.getDaysForCurrentMonth;
					$scope.showAdminReport = false;
					$scope.formatRowName = timesheetService.formatRowName;

					var loadTimesheet = function() {
						$scope.loading = true;
						$http
								.get(
										$scope.appBasePath + "/rest/report/getUserReport?date="
												+ dateService
														.formattedReportDate())
								.then(
										function(response) {
											if (response.data.outcome) {
												$scope.timesheet = timesheetService
														.makeBindableHoursArray(response.data.report.timesheet);
												$scope.reportLocked = response.data.report.locked;
												$scope.loading = false;
											} else {
												$scope
														.handleHttpError(response);
											}
										}, $scope.handleHttpError);
					}
					loadTimesheet();

					$scope.$on("monthSelectorChanged", function() {
						// Clears any animation when going to another month.
						$scope.showAlert = false;
						loadTimesheet();
					});
					$scope.$on("monthSelectorInit", loadTimesheet);
					$scope.$on("monthSelectorChangedStart", function() {
						$scope.loading = true
					});

					$scope.saveTimesheet = function() {
						$scope.loading = true;
						// Sends the request to the server.
						$http
								.post(
										$scope.appBasePath + "/rest/report/saveUserTimesheet",
										{
											timesheetDate : dateService
													.formattedReportDate(),
											timesheet : timesheetService
													.unwindBindableHoursArray($scope.timesheet)
										})
								.then(
										function(response) {
											if (response.data.outcome) {
												// Restores the hours array in
												// its
												// bindable form.
												timesheetService
														.makeBindableHoursArray($scope.timesheet);

												$scope.loading = false;
												alertService.showAlert($scope, 'Updates saved succesfully.');
											} else {
												$scope
														.handleHttpError(response);
											}
										}, $scope.handleHttpError);
					};

					$scope.columnsInError = new Array(31);
					$scope.isColumnValid = function(index) {
						var isColumnValid = true;
						var hourSum = 0;
						$scope.timesheet.rows.forEach(function(row) {
							// TODO: a volte elementi array sono nulli,
							// indagare
							hourSum += row.hours[index].value;
						});
						if (hourSum > 24) {
							isColumnValid = false;
							$scope.errorMessage = "Inserted hours for day "
									+ (index + 1) + " are more than 24.";
							$scope.columnsInError[index] = 1;
						} else {
							$scope.columnsInError[index] = 0;
						}
						return isColumnValid;
					}

					$scope.limitToMax = function(e, index, row) {
						var rowName = row.name;
						var maxNumberOfHours;

						if (rowName == 'Other' || rowName == 'Overtime') {
							maxNumberOfHours = 24;
						} else {
							maxNumberOfHours = 8;
						}

						if (row.hours[index].value > maxNumberOfHours
								&& e.keyCode != 46 // delete
								&& e.keyCode != 8 // backspace
						) {
							e.preventDefault();
							row.hours[index].value = maxNumberOfHours;
						}
					}

					// Shows the add rows button if a row is not already there.
					$scope.timesheetNotContainsAnyRow = function(newRowNames) {
						var timesheet = $scope.timesheet;
						if (timesheet) {
							var rowsToFind = newRowNames.length;
							var rowsFound = 0;
							newRowNames.forEach(function(newRowName) {
								timesheet.rows.forEach(function(orderRow) {
									if (orderRow.name === newRowName) {
										rowsFound++;
									}
								});
							});
							return rowsFound != rowsToFind;
						}
					}

					// Adds a row to the timesheet.
					$scope.addRowToTimesheet = function(newRowName) {
						var numberOfDays = dateService.getDaysForCurrentMonth().length;
						var hoursArray = new Array(numberOfDays);
						hoursArray.fill(0);

						var newRow = {
							name : newRowName,
							hours : hoursArray
						};
						timesheetService
								.unwindBindableHoursArray($scope.timesheet);
						$scope.timesheet.rows.push(newRow);

						timesheetService
								.makeBindableHoursArray($scope.timesheet);
					}

				});

app
		.controller(
				"expenseAccountAdminController",
				function($scope, $rootScope, $http, dateService,
						expenseAccountService, $routeParams, $location) {
					$rootScope.isAdmin = true;
					$scope.total = expenseAccountService.total;
					$scope.partialTotal = expenseAccountService.partialTotal;
					$scope.showAdminReport = true;
					$scope.reportLocked = true;
					$scope.getDaysForCurrentMonth = dateService.getDaysForCurrentMonth;
					$scope.totalKm = expenseAccountService.totalKm;
					$scope.totalKmFare = expenseAccountService.totalKmFare;

					var username = $routeParams.username;
					var userid = $routeParams.userid;

					// If any parameter is missing return to the report-summary
					// page.
					if (!username || !userid) {
						$location.path("/admin-report-summary");
						return;
					}

					$scope.detailedReportName = function() {
						return [ username,
								dateService.months[dateService.reportMonth],
								dateService.reportYear ].join(" ");
					}

					$http
							.get(
									$scope.appBasePath + "/rest/report/getUserReportForAdmin?date="
											+ dateService.formattedReportDate()
											+ "&user=" + userid)
							.then(
									function(response) {
										if (response.data.outcome) {
											$scope.expenseAccount = response.data.report.expenseAccount;
											$scope.loading = false;
										} else {
											$scope.handleHttpError(response);
										}
									}, $scope.handleHttpError);

				});

app
		.controller(
				"expenseAccountUserController",
				function($scope, $rootScope, $http, dateService,
						expenseAccountService, alertService) {
					// View variables.
					$scope.total = expenseAccountService.total;
					$scope.partialTotal = expenseAccountService.partialTotal;
					$scope.showAdminReport = false;
					$scope.getDaysForCurrentMonth = dateService.getDaysForCurrentMonth;
					$scope.totalKm = expenseAccountService.totalKm;
					$scope.totalKmFare = expenseAccountService.totalKmFare;

					// Loads the expense account.
					var loadExpenseAccount = function() {
						$scope.loading = true;

						$http
								.get(
										$scope.appBasePath + "/rest/report/getUserReport?date="
												+ dateService
														.formattedReportDate())
								.then(
										function(response) {
											if (response.data.outcome) {
												$scope.expenseAccount = response.data.report.expenseAccount;
												$scope.reportLocked = response.data.report.locked;
												$scope.loading = false;
											} else {
												$scope
														.handleHttpError(response);
											}
										}, $scope.handleHttpError);
					}

					$scope.$on("monthSelectorChanged", function() {
						// Clears any animation when going to another month.
						$scope.showAlert = false;
						loadExpenseAccount();
					});
					$scope.$on("monthSelectorInit", loadExpenseAccount);
					$scope.$on("monthSelectorChangedStart", function() {
						$scope.loading = true
					});

					$scope.addRow = function() {
						trimEmptyRows();
						addNewRowIfLastNotEmpty();
					}

					// Saves the expense account.
					var dismissOkAlertPromise;
					$scope.saveExpenseAccount = function() {
						$scope.loading = true;

						// Trims the empty rows before saving them.
						trimEmptyRows();

						// Sends the request to the server.
						$http
								.post(
										$scope.appBasePath + "/rest/report/saveUserExpenseAccount",
										{
											expenseAccountDate : dateService
													.formattedReportDate(),
											expenseAccount : $scope.expenseAccount
										})
								.then(
										function(response) {
											if (response.data.outcome) {
												$scope.loading = false;
												alertService.showAlert($scope, 'Updates saved succesfully.');
											} else {
												$scope.handleHttpError(response);
											}
										}, $scope.handleHttpError);
					}

					// Deletes a row.
					$scope.deleteRow = function(row) {
						var rows = $scope.expenseAccount.rows;
						rows.splice(rows.indexOf(row), 1);
					}

					// Checks whether a row is empty or not.
					var isRowEmpty = function(row) {
						return !(row.day || row.customer || row.place
								|| row.description || row.km || row.dailyRefund
								|| row.transportation || row.accomodation
								|| row.meals || row.other)
					};

					// Removes any empty rows from the expense account.
					var trimEmptyRows = function() {
						$scope.expenseAccount.rows = $scope.expenseAccount.rows
								.filter(function(row) {
									return !isRowEmpty(row);
								})
					};

					// Adds a new row if the last one is empty or inexistent.
					var addNewRowIfLastNotEmpty = function() {
						var rows = $scope.expenseAccount.rows;
						var lastRow = rows[rows.length - 1];

						// There should always be a writeable empty row. Add one
						// if there's none
						// or if the last is not empty.
						if (lastRow == null || !isRowEmpty(lastRow)) {
							rows.push({});
						}
					}
				});

app
		.controller(
				"adminReportSummaryController",
				function($scope, $rootScope, $http, $location, dateService,
						alertService) {
					$rootScope.isAdmin = true;
					$rootScope.messageOnsuccess ='';

					var hourSumReduction = function(total, currentHour) {
						return total + currentHour
					};

					// Loads the report summary.
					var dismissOkAlertPromise;
					var loadReportSummary = function() {
						$scope.loading = true;
						$http
								.get(
										$scope.appBasePath + "/rest/report/getReportsSummary?date="
												+ dateService
														.formattedReportDate())
								.then(
										function(response) {
											if (response.data.outcome) {
												$scope.reports = response.data.reports;
												
												// TODO: questo schifo fatto al frontend dovrebbe essere gestito a backend.
												// Al momento viene fatto qui perchè mi sono state imposte delle scadenze su un progetto che ho
												// iniziato per cavoli miei (straordinari non pagati) e non ho intenzione di sistemarlo. 
												// Chiedo scusa per questo scempio.
												
												// Adds the summary fields to
												// each report.
												$scope.reports
														.forEach(function(
																report) {
															report.summary = {};
															report.summary.workedDetails = [];
															report.summary.overtimeDetails = [];
															report.summary.employee = report.userDisplayName;
															report.summary.customer = report.timesheet.rows
																	.reduce(
																			function(
																					total,
																					currentRow) {
																				var order = currentRow.order;
																				// Adds
																				// the
																				// customer
																				// to
																				// the
																				// array
																				// only
																				// if
																				// not
																				// present
																				// already.
																				if (order
																						&& total
																								.indexOf(order.customer) === -1) {
																					total
																							.push(order.customer);
																				}
																				return total;
																			},
																			[])
																	.join(" / ");

															if (report.timesheet
																	&& report.timesheet.rows) {

																report.timesheet.rows
																		.forEach(function(
																				row) {
																			var total = 0;
																			if (row.hours) {
																				total = row.hours
																				.reduce(
																						hourSumReduction,
																						0);
																			}
																			if (row.name
																					.startsWith("Worked")) {
																				report.summary.workedDetails
																						.push({
																							customer : row.order.customer,
																							total : total
																						});
																			} else if (row.name
																					.startsWith("Overtime")) {
																				report.summary.overtimeDetails
																						.push({
																							customer : row.order.customer,
																							total : total
																						});
																			} else if (row.name === "Holiday") {
																				report.summary.vacation = total;
																			} else if (row.name === "Leaves") {
																				report.summary.leave = total;
																			}

																		});

																// Final
																// reduction of
																// overtime and
																// worked hours.
																report.summary.worked = report.summary.workedDetails
																		.reduce(
																				function(
																						total,
																						current) {
																					return total
																							+ current.total;
																				},
																				0);
																report.summary.overtime = report.summary.overtimeDetails
																		.reduce(
																				function(
																						total,
																						current) {
																					return total
																							+ current.total;
																				},
																				0);

															}
														});

												$scope.updateLockAllReportsCheckbox();
												$scope.loading = false;
											} else {
												$scope
														.handleHttpError(response);
											}
										}, $scope.handleHttpError);
					};
					
					$scope.getRowSpanForReportSummary = function(summary) {
						var workedDetailsLength = summary.workedDetails.length;
						if (workedDetailsLength == 1) {
							return workedDetailsLength;
						} 
						return workedDetailsLength + 1;
					}

					$scope.$on("monthSelectorChanged", function() {
						// Clears any animation when going to another month.
						$scope.showAlert = false;
						loadReportSummary();
					});
					$scope.$on("monthSelectorInit", loadReportSummary);
					$scope.$on("monthSelectorChangedStart", function() {
						$scope.loading = true
					});

					$scope.reportsLength = function() {
						return !$scope.reports || $scope.reports.length === 0;
					}

					$scope.updateLocks = function() {
						$scope.loading = true;
						// TODO: pair <id, flag> instead?
						$http.post(
								$scope.appBasePath + "/rest/report/updateReportsLock",
								{
									reports : $scope.reports
						}).then(function(response) {
							$scope.loading = false;
							alertService.showAlert($scope, 'Updates saved sucesfully.');
						}, $scope.handleHttpError);
	};
	
	$scope.$on("monthSelectorChanged", loadReportSummary);
	$scope.$on("monthSelectorInit", loadReportSummary);
	$scope.$on("monthSelectorChangedStart", () => $scope.loading = true);
	
	$scope.reportsLength = function() {
		return !$scope.reports || $scope.reports.length === 0;
	}

	$scope.showReportDetails = function(report) {
		var userid = report.header.user;
		var username = report.userDisplayName;
		var reportDate = dateService.formattedReportDate();

		$location.path("/timesheet").search({
			"userid" : userid,
			"username" : username,
			"reportDate" : reportDate
		});
	}

	$scope.updateLockAllReportsCheckbox = function() {
		var someReportsLocked = false;
		var someReportsUnlocked = false;
		$scope.reports.forEach(function(report) {
			if (report.locked) {
				someReportsLocked = true;
			} else {
				someReportsUnlocked = true;
			}
			if (someReportsLocked && someReportsUnlocked) {
				return;
			}
		});
		
		if (someReportsLocked && someReportsUnlocked) {
			$("#lockAllReportsCheckbox").prop("indeterminate", true);
		} else {
			$("#lockAllReportsCheckbox").prop("indeterminate", false);
			if (someReportsLocked) {
				$scope.adminReportSummary.lockAllReports = true;
			} else if (someReportsUnlocked) {
				$scope.adminReportSummary.lockAllReports = false;
			}
		}
	}
	
	$scope.toggleAllReports = function() {
		$scope.reports.forEach(function(report) {	
			report.locked = $scope.adminReportSummary.lockAllReports;
		});
	}
	
	$scope.printReports = function() {
		return $scope.appBasePath + "/rest/report/getExcelReportSummary?reportDate="
			+ dateService.formattedReportDate();
	}
});

app.controller("adminUserListController", function($scope, $http, $rootScope, alertService) {
	$rootScope.isAdmin = true;
	$scope.loading = true;
	$scope.showAlert = false;
	
	var localDateToJsDateParser = function(date) {
		var splittedDate = date.split("-");
		return new Date(Date.UTC(splittedDate[0], splittedDate[1] - 1,
				splittedDate[2]));
	}
	
	var cleanData = function(users, conversionFunction) {
		users.forEach(function(user) {
			// Adds a field with the user name.
			user.employee = user.surname + ' ' + user.name;
			if (user.orders) {
				// Converts the dates if present to a JS format.
				user.orders.forEach(function(order) {
					if (order.from) {
						order.from = conversionFunction(order.from);
					}
					if (order.to) {
						order.to = conversionFunction(order.to);
					}
				});
			} else {
				// Initializes the orders to an array.
				user.orders = [];
			}
		});
		return users;
	}

	var getUserList = function() {
		$http.get($scope.appBasePath + "/rest/user/getUserList").then(function(response) {
			if (response.data.outcome) {
				$scope.users = cleanData(response.data.users, localDateToJsDateParser);
				$scope.loading = false;
			} else {
				$scope.handleHttpError();
			}
		}, $scope.handleHttpError);
	}
	getUserList();
	
	$scope.orderButtonsEnabled = function() {
		var buttonEnabled = true;
		$scope.user.orders.forEach(function(order) {
			if (!order.customer || !order.from) {
				buttonEnabled = false;
				return;
			}
		})
		
		return buttonEnabled;
	}
	
	var allRowValids = function(user) {
		var orders = user.orders;
		
		// If there are no orders for a user, the rows are considered invalid.
		if (!orders || orders.length == 0) {
			return false;
		}
		
		var orderValids = true;
		orders.forEach(function(order) {
			if (!order.customer || !order.from) {
				orderValids = false;
				return;
			}
		})
		return orderValids;
	}
	
	var normalizeOrderDates = function(user) {
		var newUser = angular.copy(user);
		newUser.orders.forEach(function(order) {
			order.from = moment(order.from).format("YYYY-MM-DD");
			if (order.to) {
				order.to = moment(order.to).format("YYYY-MM-DD");
			}
		});
		return newUser;
	};

	$scope.updateUserOrders = function(user) {
		// Checks if the rows are valid before saving them.
		if (allRowValids(user)) {
			$http.post($scope.appBasePath + "/rest/user/updateUserOrders", {
				user : normalizeOrderDates(user)
			}).then(function(response) {
				if (response.data.outcome) {
					$scope.loading = false;
					alertService.showAlert($scope, 'Updates saved succesfully.');
				} else {
					$scope.handleHttpError();
				}
			}, $scope.handleHttpError);
		
		} else {
			$scope.message = 'Fields "Customer" and "From" are mandatory.';
			$scope.error = true;
			$scope.showAlert = true;
		}
	}

	$scope.addRow = function(user) {
		$scope.showAlert = false;
		// Checks if the rows are valid before adding a new one.
		if (user.orders.length === 0 || allRowValids(user)) {
			user.orders.push({});
		} else {
			alertService.showAlert($scope, 'Fields "Customer" and "From" are mandatory.', true);
		}
	}
	
	// Add employee modal
	$scope.addEmployee = function() {
		$scope.modalReference.addButtonLocked = true;
		var url = $scope.appBasePath + "/rest/user/createUser";

		var user = {
			name : $scope.modalReference.newEmployeeName,
			surname : $scope.modalReference.newEmployeeLastName,
			email : $scope.modalReference.newEmployeeEmail,
			refund : $scope.modalReference.newEmployeeRefund,
			refundType : $scope.modalReference.newEmployeeRefundType,
			fare : $scope.modalReference.newEmployeeFare
		}

		$scope.modalReference.loading = true;
		$http.post(url, user).then(function(response) {
			if (response.data.outcome) {
				// Cleans the form data.
				$scope.resetEmployeeModalData();
				// Dismiss modal.
				$("#admin-add-employee-modal").modal("hide");
				
				// Fetches the new data.
				getUserList();
				
				// Shows the alert outside the modal.
				alertService.showAlert($scope, response.data.errorMessage, !response.data.outcome);
			} else {
				// Shows error message directly inside the modal.
				$scope.modalReference.showModalAlert = true;
				$scope.modalReference.message = response.data.errorMessage;
				$scope.modalReference.error = !response.data.outcome;
			}
			
			$scope.modalReference.loading = false;
			$scope.modalReference.addButtonLocked = false;
		}, $scope.handleHttpError);
	}

	// Cleans the add employee modal.
	$scope.resetEmployeeModalData = function() {
		$scope.modalReference.showModalAlert = false;
		$scope.modalReference.newEmployeeName = "";
		$scope.modalReference.newEmployeeLastName = "";
		$scope.modalReference.newEmployeeEmail = "";
		$scope.modalReference.newEmployeeRefund = "";
		$scope.modalReference.newEmployeeRefundType = "MONTHLY";
		$scope.modalReference.newEmployeeFare = "";
		
		// Clean the autonumeric fields if the modal has been opened.
		var fareField = AutoNumeric.getAutoNumericElement("#newEmployeeFare");
		var refundField = AutoNumeric.getAutoNumericElement("#newEmployeeRefund");
		if (fareField && refundField) {
			fareField.clear();
			refundField.clear();
		}
	}
	$scope.resetEmployeeModalData();
	
});