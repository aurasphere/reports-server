package co.aurasphere.reports.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Expense account which represents the expenses of a {@link User} in a
 * {@link Report}.
 * 
 * @author Donato Rimenti
 */
public class ExpenseAccount {

	/**
	 * The expense account rows.
	 */
	private List<ExpenseAccountRow> rows;

	/**
	 * The notes.
	 */
	private String notes;

	/**
	 * Km fare applied to the user.
	 */
	private BigDecimal fare;

	/**
	 * Instantiates a new ExpenseAccount.
	 */
	public ExpenseAccount() {
		this.rows = new ArrayList<ExpenseAccountRow>();
	}

	/**
	 * Instantiates a new ExpenseAccount.
	 *
	 * @param fare
	 *            the {@link #fare}
	 */
	public ExpenseAccount(BigDecimal fare) {
		this();
		this.fare = fare;
	}

	/**
	 * Gets the {@link #rows}.
	 *
	 * @return the {@link #rows}
	 */
	public List<ExpenseAccountRow> getRows() {
		return rows;
	}

	/**
	 * Sets the {@link #rows}.
	 *
	 * @param rows
	 *            the new {@link #rows}
	 */
	public void setRows(List<ExpenseAccountRow> rows) {
		this.rows = rows;
	}

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
	 * Gets the {@link #fare}.
	 *
	 * @return the {@link #fare}
	 */
	public BigDecimal getFare() {
		return fare;
	}

	/**
	 * Sets the {@link #fare}.
	 *
	 * @param fare
	 *            the new {@link #fare}
	 */
	public void setFare(BigDecimal fare) {
		this.fare = fare;
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
		result = prime * result + ((fare == null) ? 0 : fare.hashCode());
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
		ExpenseAccount other = (ExpenseAccount) obj;
		if (fare == null) {
			if (other.fare != null)
				return false;
		} else if (!fare.equals(other.fare))
			return false;
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
		return "ExpenseAccount [rows=" + rows + ", notes=" + notes + ", fare=" + fare + "]";
	}

}