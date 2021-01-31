package co.aurasphere.reports.rest.model;

import co.aurasphere.reports.model.Report;

/**
 * Response for
 * {@link co.aurasphere.reports.rest.UserRestController#getUserReport(String, java.time.YearMonth)}.
 * 
 * @author Donato Rimenti
 */
public class GetUserReportResponse extends BaseReportsRestResponse {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The requested report of the user.
	 */
	private Report report;

	/**
	 * Instantiates a new GetUserReportResponse.
	 */
	public GetUserReportResponse() {
	}

	/**
	 * Instantiates a new GetUserReportResponse.
	 *
	 * @param report
	 *            the {@link #report}
	 */
	public GetUserReportResponse(Report report) {
		super(true);
		this.report = report;
	}

	/**
	 * Instantiates a new GetUserReportResponse.
	 *
	 * @param outcome
	 *            the {@link BaseInterviewServletRestResponse#outcome}.
	 */
	public GetUserReportResponse(boolean outcome) {
		super(outcome);
	}

	/**
	 * Instantiates a new CreateUserResponse.
	 *
	 * @param errorMessage
	 *            the {@link BaseInterviewServletRestResponse#errorMessage}.
	 */
	public GetUserReportResponse(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Gets the {@link #report}.
	 *
	 * @return the {@link #report}
	 */
	public Report getReport() {
		return report;
	}

	/**
	 * Sets the {@link #report}.
	 *
	 * @param report
	 *            the new {@link #report}
	 */
	public void setReport(Report report) {
		this.report = report;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((report == null) ? 0 : report.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetUserReportResponse other = (GetUserReportResponse) obj;
		if (report == null) {
			if (other.report != null)
				return false;
		} else if (!report.equals(other.report))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetUserReportResponse [report=" + report + "]";
	}

}