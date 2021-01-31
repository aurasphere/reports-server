package co.aurasphere.reports.model;

import java.time.LocalDate;

/**
 * Represents an order which includes the customer and the beginning/ending date.
 * 
 * @author Donato Reimenti
 *
 */
public class Order {

	/**
	 * The customer name.
	 */
	private String customer;

	/**
	 * The beginning date of the order.
	 */
	private LocalDate from;

	/**
	 * The ending date of the order.
	 */
	private LocalDate to;

	/**
	 * Gets the {@link #customer}.
	 *
	 * @return the {@link #customer}
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * Sets the {@link #customer}.
	 *
	 * @param customer
	 *            the new {@link #customer}
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * Gets the {@link #from}.
	 *
	 * @return the {@link #from}
	 */
	public LocalDate getFrom() {
		return from;
	}

	/**
	 * Sets the {@link #from}.
	 *
	 * @param from
	 *            the new {@link #from}
	 */
	public void setFrom(LocalDate from) {
		this.from = from;
	}

	/**
	 * Gets the {@link #to}.
	 *
	 * @return the {@link #to}
	 */
	public LocalDate getTo() {
		return to;
	}

	/**
	 * Sets the {@link #to}.
	 *
	 * @param to
	 *            the new {@link #to}
	 */
	public void setTo(LocalDate to) {
		this.to = to;
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
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Order other = (Order) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
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
		return "Order [customer=" + customer + ", from=" + from + ", to=" + to + "]";
	}

}