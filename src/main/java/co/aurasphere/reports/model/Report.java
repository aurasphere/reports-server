package co.aurasphere.reports.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a report for a given month of a given users.
 * 
 * @author Donato Rimenti
 *
 */
@Document(collection = "report")
public class Report {

	/**
	 * Display name of the user.
	 */
	private String userDisplayName;

	/**
	 * Header of the report with the user and date.
	 */
	@Id
	private ReportHeader header;

	/**
	 * Timesheet component of the report.
	 */
	private Timesheet timesheet;

	/**
	 * Expense account component of the report.
	 */
	private ExpenseAccount expenseAccount;

	/**
	 * Indicates whether this report can be edited or not.
	 */
	private boolean locked;

	/**
	 * Creation date of the report.
	 */
	private LocalDateTime creationTime;

	/**
	 * Update time of the report.
	 */
	private LocalDateTime updateTime;
	
	/**
	 * Gets the {@link #header}.
	 *
	 * @return the {@link #header}
	 */
	public ReportHeader getHeader() {
		return header;
	}

	/**
	 * Sets the {@link #header}.
	 *
	 * @param header
	 *            the new {@link #header}
	 */
	public void setHeader(ReportHeader header) {
		this.header = header;
	}

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
	 * Gets the {@link #expenseAccount}.
	 *
	 * @return the {@link #expenseAccount}
	 */
	public ExpenseAccount getExpenseAccount() {
		return expenseAccount;
	}

	/**
	 * Sets the {@link #expenseAccount}.
	 *
	 * @param expenseAccount
	 *            the new {@link #expenseAccount}
	 */
	public void setExpenseAccount(ExpenseAccount expenseAccount) {
		this.expenseAccount = expenseAccount;
	}

	/**
	 * Gets the {@link #locked}.
	 *
	 * @return the {@link #locked}
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the {@link #locked}.
	 *
	 * @param locked
	 *            the new {@link #locked}
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets the {@link #creationTime}.
	 *
	 * @return the {@link #creationTime}
	 */
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	/**
	 * Sets the {@link #creationTime}.
	 *
	 * @param creationTime
	 *            the new {@link #creationTime}
	 */
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * Gets the {@link #updateTime}.
	 *
	 * @return the {@link #updateTime}
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the {@link #updateTime}.
	 *
	 * @param updateTime
	 *            the new {@link #updateTime}
	 */
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Gets the {@link #userDisplayName}.
	 *
	 * @return the {@link #userDisplayName}
	 */
	public String getUserDisplayName() {
		return userDisplayName;
	}

	/**
	 * Sets the {@link #userDisplayName}.
	 *
	 * @param userDisplayName
	 *            the new {@link #userDisplayName}
	 */
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
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
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((expenseAccount == null) ? 0 : expenseAccount.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((timesheet == null) ? 0 : timesheet.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((userDisplayName == null) ? 0 : userDisplayName.hashCode());
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
		Report other = (Report) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (expenseAccount == null) {
			if (other.expenseAccount != null)
				return false;
		} else if (!expenseAccount.equals(other.expenseAccount))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (locked != other.locked)
			return false;
		if (timesheet == null) {
			if (other.timesheet != null)
				return false;
		} else if (!timesheet.equals(other.timesheet))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (userDisplayName == null) {
			if (other.userDisplayName != null)
				return false;
		} else if (!userDisplayName.equals(other.userDisplayName))
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
		return "Report [userDisplayName=" + userDisplayName + ", header=" + header + ", timesheet=" + timesheet
				+ ", expenseAccount=" + expenseAccount + ", locked=" + locked + ", creationTime=" + creationTime
				+ ", updateTime=" + updateTime + "]";
	}

}