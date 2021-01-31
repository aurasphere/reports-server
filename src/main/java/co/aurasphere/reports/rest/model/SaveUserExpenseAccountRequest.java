package co.aurasphere.reports.rest.model;

import java.io.Serializable;
import java.time.YearMonth;

import co.aurasphere.reports.model.ExpenseAccount;

/**
 * Request for
 * {@link co.aurasphere.reports.rest.ReportRestController#saveUserExpenseAccount(SaveUserExpenseAccountRequest)}.
 * 
 * @author Donato Rimenti
 *
 */
public class SaveUserExpenseAccountRequest implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The expense account object to be saved.
	 */
	private ExpenseAccount expenseAccount;

	/**
	 * The year and month of the expense account.
	 */
	private YearMonth expenseAccountDate;

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
	 * Gets the {@link #expenseAccountDate}.
	 *
	 * @return the {@link #expenseAccountDate}
	 */
	public YearMonth getExpenseAccountDate() {
		return expenseAccountDate;
	}

	/**
	 * Sets the {@link #expenseAccountDate}.
	 *
	 * @param expenseAccountDate
	 *            the new {@link #expenseAccountDate}
	 */
	public void setExpenseAccountDate(YearMonth expenseAccountDate) {
		this.expenseAccountDate = expenseAccountDate;
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
		result = prime * result + ((expenseAccount == null) ? 0 : expenseAccount.hashCode());
		result = prime * result + ((expenseAccountDate == null) ? 0 : expenseAccountDate.hashCode());
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
		SaveUserExpenseAccountRequest other = (SaveUserExpenseAccountRequest) obj;
		if (expenseAccount == null) {
			if (other.expenseAccount != null)
				return false;
		} else if (!expenseAccount.equals(other.expenseAccount))
			return false;
		if (expenseAccountDate == null) {
			if (other.expenseAccountDate != null)
				return false;
		} else if (!expenseAccountDate.equals(other.expenseAccountDate))
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
		return "SaveUserExpenseAccountRequest [expenseAccount=" + expenseAccount + ", expenseAccountDate="
				+ expenseAccountDate + "]";
	}

}