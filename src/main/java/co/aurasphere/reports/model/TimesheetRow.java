package co.aurasphere.reports.model;

import java.util.Arrays;

/**
 * Represents a row in a {@link Timesheet}. Each row represents working hours,
 * leave hours and similar.
 * 
 * @author Donato Rimenti
 *
 */
public class TimesheetRow {

	/**
	 * Displayed name of the row.
	 */
	private String name;

	/**
	 * Hours spent by the user in the activity described by this row.
	 */
	// TODO: è possibile frazioni di ore??
	private int[] hours;

	/**
	 * The order associated with the row, if any.
	 */
	private Order order;

	/**
	 * Instantiates a new TimesheetRow.
	 */
	public TimesheetRow() {
	}
	
	/**
	 * Instantiates a new TimesheetRow.
	 *
	 * @param name
	 *            the {@link #name}
	 * @param order
	 *            the {@link #order}
	 */
	public TimesheetRow(String name, Order order) {
		this.name = name;
		this.order = order;
	}

	/**
	 * Instantiates a new TimesheetRow.
	 *
	 * @param name
	 *            the {@link #name}
	 * @param numberOfDays
	 *            the number of days in the month of this row
	 */
	public TimesheetRow(String name, int numberOfDays) {
		this(name, numberOfDays, null);
	}

	/**
	 * Instantiates a new TimesheetRow.
	 *
	 * @param name
	 *            the {@link #name}
	 * @param numberOfDays
	 *            the number of days in the month of this row
	 * @param order
	 *            the {@link #order}
	 */
	public TimesheetRow(String name, int numberOfDays, Order order) {
		this(name, order);

		// Prefills the hours field with 0.
		this.hours = new int[numberOfDays];
		Arrays.fill(hours, 0);
	}

	/**
	 * Gets the {@link #name}.
	 *
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the {@link #name}.
	 *
	 * @param name
	 *            the new {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the {@link #hours}.
	 *
	 * @return the {@link #hours}
	 */
	public int[] getHours() {
		return hours;
	}

	/**
	 * Sets the {@link #hours}.
	 *
	 * @param hours
	 *            the new {@link #hours}
	 */
	public void setHours(int[] hours) {
		this.hours = hours;
	}

	/**
	 * Gets the {@link #order}.
	 *
	 * @return the {@link #order}
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * Sets the {@link #order}.
	 *
	 * @param order
	 *            the new {@link #order}
	 */
	public void setOrder(Order order) {
		this.order = order;
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
		result = prime * result + Arrays.hashCode(hours);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		TimesheetRow other = (TimesheetRow) obj;
		if (!Arrays.equals(hours, other.hours))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
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
		return "TimesheetRow [name=" + name + ", hours=" + Arrays.toString(hours) + ", order=" + order + "]";
	}

}