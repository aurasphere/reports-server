package co.aurasphere.reports.rest.model;

import java.io.Serializable;
import java.time.YearMonth;

import co.aurasphere.reports.model.Timesheet;

/**
 * Request for
 * {@link UserRestController#saveUserTimesheet()}.
 * 
 * @author Donato Rimenti
 *
 */
public class SaveUserTimesheetRequest implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The timesheet object to be saved.
	 */
	private Timesheet timesheet;

	/**
	 * The year and month of the timesheet.
	 */
	private YearMonth timesheetDate;

	/**
	 * Gets the {@link #timesheet}.
	 *
	 * @return the {@link #timesheet}
	 */
	public Timesheet getTimesheet() {
		return timesheet;
	}

	/**
	 * Sets the {@link #timesheet}.
	 *
	 * @param timesheet
	 *            the new {@link #timesheet}
	 */
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}

	/**
	 * Gets the {@link #timesheetDate}.
	 *
	 * @return the {@link #timesheetDate}
	 */
	public YearMonth getTimesheetDate() {
		return timesheetDate;
	}

	/**
	 * Sets the {@link #timesheetDate}.
	 *
	 * @param timesheetDate
	 *            the new {@link #timesheetDate}
	 */
	public void setTimesheetDate(YearMonth timesheetDate) {
		this.timesheetDate = timesheetDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timesheet == null) ? 0 : timesheet.hashCode());
		result = prime * result + ((timesheetDate == null) ? 0 : timesheetDate.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveUserTimesheetRequest other = (SaveUserTimesheetRequest) obj;
		if (timesheet == null) {
			if (other.timesheet != null)
				return false;
		} else if (!timesheet.equals(other.timesheet))
			return false;
		if (timesheetDate == null) {
			if (other.timesheetDate != null)
				return false;
		} else if (!timesheetDate.equals(other.timesheetDate))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaveUserTimesheetRequest [timesheet=" + timesheet + ", timesheetDate="
				+ timesheetDate + "]";
	}

}
