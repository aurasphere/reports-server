var holidays = {

	/*
	 * Returns a date object for a specified day and month of this year.
	 */
	fromDate : function(day, month) {
		return new Date(new Date().getFullYear(), month - 1, day);
	},

	/*
	 * Fixed italian holidays in a year. This array contains all holidays except
	 * Easter (Pasqua) and Easter Monday (Pasquetta) which are computed for each
	 * year. 
	 */
	fixedHolidays : function() { return [ holidays.fromDate(1, 1),
		holidays.fromDate(6, 1), holidays.fromDate(25, 4),
		holidays.fromDate(1, 5), holidays.fromDate(2, 6),
		holidays.fromDate(15, 8), holidays.fromDate(1, 11),
		holidays.fromDate(8, 12), holidays.fromDate(25, 12),
		holidays.fromDate(26, 12) ]; },

	/*
	 * Checks if a given date is an holiday.
	 */
	isHoliday : function(date) {
		// Check if it's a fixed holiday.
		var fixedHolidays = holidays.fixedHolidays();
		var isHoliday = false;
		fixedHolidays.forEach(function(holiday) {
			if (holidays.sameDayAndMonth(date, holiday)) {
				isHoliday = true;
				return true;
			}
		});
		if (isHoliday) {
			return true;
		}

		// Check if it's Easter of monday after Easter.
		var easter = holidays.getEasterForYear(date.getFullYear());
		var mondayAfterEaster = holidays.getMondayAfterEasterFromEaster(easter);
		if (holidays.sameDayAndMonth(date, easter)
			|| holidays.sameDayAndMonth(date, mondayAfterEaster)) {
			return true;
		}

		// Not an holiday.
		return false;
	},

	/*
	 * Checks if a given date is a weekend day.
	 */
	isWeekend : function(date) {
		var day = date.getDay();
		return day === 6 || day === 0;
	},

	/*
	 * Checks if a given date is a weekend day or an holiday.
	 */
	isWeekendOrHoliday : function(date) {
		return holidays.isWeekend(date) || holidays.isHoliday(date);
	},

	/*
	 * Checks if a given date is a working day.
	 */
	isWorkingDay : function(date) {
		return !holidays.isWeekendOrHoliday(date);
	},

	/*
	 * Computes the Easter date for the year passed as argument by using the
	 * Gauss algorithm.
	 */
	getEasterForYear : function(year) {
		var f = Math.floor;
		// Golden Number - 1
		var G = year % 19;
		var C = f(year / 100);
		// related to Epact
		var H = (C - f(C / 4) - f((8 * C + 13)/25) + 19 * G + 15) % 30;
		// number of days from 21 March to the Paschal full moon
		var I = H - f(H/28) * (1 - f(29/(H + 1)) * f((21-G)/11));
		// weekday for the Paschal full moon
		var J = (year + f(year / 4) + I + 2 - C + f(C / 4)) % 7;
		// number of days from 21 March to the Sunday on or before the Paschal full moon
		var L = I - J;
		
		// Actual day and month.
		var month = 3 + f((L + 40)/44);
		var day = L + 28 - 31 * f(month / 4);

		// Returns a calendar.
		return new Date(year, month - 1, day);
	},

	/*
	 * Returns the date of the monday after Easter, computed by getting the
	 * Easter date and adding one day.
	 */
	getMondayAfterEasterForYear : function(year) {
		var mondayAfterEaster = holidays.getEasterForYear(year);
		mondayAfterEaster.setDate(mondayAfterEaster.getDate() + 1);
		return mondayAfterEaster;
	},

	/*
	 * Utility method for returning the monday after Easter from an Easter date.
	 * The date is computed by adding one day to the date passed as argument.
	 */
	getMondayAfterEasterFromEaster : function(easter) {
		easter.setDate(easter.getDate() + 1);
		return easter;
	},

	/*
	 * Alias for getMondayAfterEasterForYear(year).
	 */
	getPasquettaForYear : function(year) {
		return holidays.getMondayAfterEasterForYear(year);
	},

	/*
	 * Alias for getMondayAfterEasterFromEaster(easter).
	 */
	getPasquettaFromPasqua : function(pasqua) {
		return holidays.getMondayAfterEasterFromEaster(pasqua);
	},

	/*
	 * Checks if two calendars refers to the same day and month.
	 */
	sameDayAndMonth : function(firstDate, secondDate) {
		return firstDate.getDate() === secondDate.getDate()
			&& firstDate.getMonth() === secondDate
				.getMonth();
	},

	/*
	 * Adds the working days passed as arguments to the given calendar. For
	 * subtraction, you can either pass a negative number or call the more
	 * straightforward subtractWorkingDays(date, workingDays) method.
	 */
	addWorkingDays : function(date, workingDays) {
		var counter = 0;
		while (counter < workingDays) {
			date.setDate(date.getDate() + 1);

			// A day is considered added only if it's a working day.
			if (holidays.isWorkingDay(date)) {
				counter++;
			}
		}
		return date;
	},

	/*
	 * Subtracts the working days passed as arguments from the given calendar.
	 * For addiction, you can either pass a negative number or call the more
	 * straightforward addWorkingDays(date, workingDays) method.
	 */
	subtractWorkingDays : function(date, workingDays) {
		return holidays.addWorkingDays(date, -workingDays);
	}
}