package co.aurasphere.reports.model;

import java.time.YearMonth;

/**
 * Contains the user and date information of a {@link Report}. Used to identify
 * a unique report.
 * 
 * @author Donato Rimenti
 *
 */
public class ReportHeader {

	/**
	 * Date of the report.
	 */
	private YearMonth date;

	/**
	 * User of the report.
	 */
	private String user;

	/**
	 * Instantiates a new ReportHeader.
	 */
	public ReportHeader() {
	}

	/**
	 * Instantiates a new ReportHeader.
	 *
	 * @param user
	 *            the {@link #user}
	 * @param date
	 *            the {@link #date}
	 */
	public ReportHeader(String user, YearMonth date) {
		this.user = user;
		this.date = date;
	}

	/**
	 * Gets the {@link #date}.
	 *
	 * @return the {@link #date}
	 */
	public YearMonth getDate() {
		return date;
	}

	/**
	 * Sets the {@link #date}.
	 *
	 * @param date
	 *            the new {@link #date}
	 */
	public void setDate(YearMonth date) {
		this.date = date;
	}

	/**
	 * Gets the {@link #user}.
	 *
	 * @return the {@link #user}
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the {@link #user}.
	 *
	 * @param user
	 *            the new {@link #user}
	 */
	public void setUser(String user) {
		this.user = user;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ReportHeader other = (ReportHeader) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
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
		return "ReportHeader [date=" + date + ", user=" + user + "]";
	}

}
