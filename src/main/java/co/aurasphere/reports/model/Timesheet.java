package co.aurasphere.reports.model;

import java.util.List;

/**
 * Represents the hours of work of a user, grouped by customer orders.
 * 
 * @author Donato Rimenti
 *
 */
/**
 * @author Donato
 *
 */
public class Timesheet {

	/**
	 * The rows which makes up this timesheet. Each row represents working
	 * hours, leave hours...
	 */
	private List<TimesheetRow> rows;

	/**
	 * Notes of the report.
	 */
	private String notes;

	/**
	 * Gets the {@link #notes}.
	 *
	 * @return the {@link #notes}
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the {@link #notes}.
	 *
	 * @param notes
	 *            the new {@link #notes}
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Gets the {@link #rows}.
	 *
	 * @return the {@link #rows}
	 */
	public List<TimesheetRow> getRows() {
		return rows;
	}

	/**
	 * Sets the {@link #rows}.
	 *
	 * @param rows
	 *            the new {@link #rows}
	 */
	public void setRows(List<TimesheetRow> rows) {
		this.rows = rows;
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
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((rows == null) ? 0 : rows.hashCode());
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
		Timesheet other = (Timesheet) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (rows == null) {
			if (other.rows != null)
				return false;
		} else if (!rows.equals(other.rows))
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
		return "Timesheet [rows=" + rows + ", notes=" + notes + "]";
	}

}