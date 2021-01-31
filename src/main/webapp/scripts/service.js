app.service("dateService", function() {
	var dateService = this;

	// Array of months.
	this.months = [ "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" ];

	// Current year and month to determine the current report.
	this.reportYear = new Date().getFullYear();
	this.reportMonth = new Date().getMonth();

	// Builds a date in the format yyyy-MM by padding the month.
	this.formatDate = function(year, month) {
		return year + "-" + ("0" + (month + 1)).slice(-2);
	}

	this.getDaysForCurrentMonth = function() {
		var weekDays = [ "S", "M", "T", "W", "T", "F", "S" ];
		var reportYearMonth = moment({
			year : dateService.reportYear,
			month : dateService.reportMonth
		});
		var daysInMonth = reportYearMonth.daysInMonth();
		var arrDays = [];

		while (daysInMonth) {
			var current = reportYearMonth.date(daysInMonth).day();
			arrDays.push(weekDays[current]);
			daysInMonth--;
		}

		return arrDays.reverse();
	}
	
	this.getNumberOfDaysInCurrentMonth = function() {
		var reportYearMonth = moment({
			year : dateService.reportYear,
			month : dateService.reportMonth
		});
		return reportYearMonth.daysInMonth();
	}

	this.formattedReportDate = function() {
		return this.formatDate(dateService.reportYear, dateService.reportMonth)
	};

	this.isHoliday = function(index, rowName) {
		var currentDate = new Date(dateService.reportYear,
				dateService.reportMonth, index + 1);
		// Disables all the rows except "Straordinari" and
		// "Altro"
		return holidays.isWeekendOrHoliday(currentDate)
				&& (!rowName || (rowName != 'Altro' && !rowName
						.startsWith('Straordinari')));
	}

});

app.service("timesheetService", function(dateService) {
	
	this.formatRowName = function(row) {
		var rowName = row.name;
		if (row.order) {
			rowName += " (" + row.order.customer + ")";
		}
		return rowName;
	}
	
	// Function to generate an array of elements which can be
	// binded with
	// AngularJS ngModel directive by wrapping primitive values
	// into objects.
	this.makeBindableHoursArray = function(timesheet) {
		timesheet.rows.forEach(function(row) {
			if (!row.hours) {
				var daysInMonth = dateService.getNumberOfDaysInCurrentMonth();
				row.hours = new Array(daysInMonth);
				row.hours.fill(0);
			}
			row.hours = row.hours.map(function(hour) {
				return {
					value : hour
				};
			});
		});
		return timesheet;
	}

	// Function to unwind an array of elements which can be
	// binded with
	// AngularJS ngModel directive by unwrapping primitive
	// values from objects.
	this.unwindBindableHoursArray = function(timesheet) {
		timesheet.rows.forEach(function(row) {
			row.hours = row.hours.map(function(hour) {
				return hour.value;
			});
		});
		return timesheet;
	}

});

//app.service("adminReportService", function(dateService) {
//	this.detailedReportName = function() {
//		return [ adminReportService.requestedUserName,
//				dateService.months[dateService.reportMonth],
//				dateService.reportYear ].join(" ");
//	}
//
//});

app.service("expenseAccountService", function(){
	var expenseAccountService = this;
	
	// Converts a string to a number handling undefined values as 0.
	this.safeStringToNum = function(num) {
		return +num || 0;
	}
	
	// Partial total of the row.
	this.partialTotal = function(row) {
		row.partialTotal = 
			expenseAccountService.safeStringToNum(row.dailyRefund) +
			expenseAccountService.safeStringToNum(row.transportation) +
			expenseAccountService.safeStringToNum(row.accomodation) + 
			expenseAccountService.safeStringToNum(row.meals) + 
			expenseAccountService.safeStringToNum(row.other);
		return row.partialTotal;
	}

	// Full total of the expense account.
	this.total = function(expenseAccount) {
		var total = 0;
		if (expenseAccount) {
			expenseAccount.rows.forEach(function(row) {
				total += row.partialTotal;
			});
		}
		total += expenseAccountService.totalKmFare(expenseAccount);
		return total;
	}
	
	// Full total of the expense account.
	this.totalKm = function(expenseAccount) {
		var total = 0;
		if (expenseAccount) {
			expenseAccount.rows.forEach(function(row) {
				total += expenseAccountService.safeStringToNum(row.km);
			});
		}
		return total;
	}
	
	this.totalKmFare = function(expenseAccount) {
		if (!expenseAccount) {
			return 0;
		}
		return expenseAccountService.totalKm(expenseAccount) * expenseAccount.fare;
	}
	
});

// Handles alerts.
app.service("alertService", function($timeout) {
		this.showAlert = function($scope, message, isError) {
			// Cancel any other previous
			// animation before showing the
			// new one.
			if ($scope.dismissAlertPromise) {
				$timeout.cancel($scope.dismissAlertPromise);
			}
			// Shows a message for 5 seconds.
			$scope.showAlert = true;
			$scope.message = message;
			$scope.error = isError;
			$scope.dismissAlertPromise = $timeout(
					function() {
						$scope.showAlert = false;
					}, 5000);
		}
});