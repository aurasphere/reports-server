package co.aurasphere.reports.rest.model;

import java.io.Serializable;
import java.util.List;

import co.aurasphere.reports.model.Report;

/**
 * Request for
 * {@link co.aurasphere.reports.rest.ReportRestController#updateReportsLock(UpdateReportsLockRequest)}.
 * 
 * @author Donato Rimenti
 *
 */
public class UpdateReportsLockRequest implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The reports to be updated.
	 */
	private List<Report> reports;

	/**
	 * Gets the {@link #reports}.
	 *
	 * @return the {@link #reports}
	 */
	public List<Report> getReports() {
		return reports;
	}

	/**
	 * Sets the {@link #reports}.
	 *
	 * @param reports
	 *            the new {@link #reports}
	 */
	public void setReports(List<Report> reports) {
		this.reports = reports;
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
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
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
		UpdateReportsLockRequest other = (UpdateReportsLockRequest) obj;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
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
		return "UpdateReportsLockRequest [reports=" + reports + "]";
	}

}
