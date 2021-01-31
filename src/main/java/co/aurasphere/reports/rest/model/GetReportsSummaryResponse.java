package co.aurasphere.reports.rest.model;

import java.util.List;

import co.aurasphere.reports.model.Report;

/**
 * Response for
 * {@link co.aurasphere.reports.rest.ReportRestController#getReportsSummary(java.time.YearMonth)}.
 * 
 * @author Donato Rimenti
 */
public class GetReportsSummaryResponse extends BaseReportsRestResponse {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The requested reports.
	 */
	private List<Report> reports;

	/**
	 * Instantiates a new GetReportsSummaryResponse.
	 */
	public GetReportsSummaryResponse() {
	}

	/**
	 * Instantiates a new GetReportsSummaryResponse.
	 *
	 * @param reports
	 *            the {@link #reports}
	 */
	public GetReportsSummaryResponse(List<Report> reports) {
		super(true);
		this.reports = reports;
	}

	/**
	 * Instantiates a new GetReportsSummaryResponse.
	 *
	 * @param outcome
	 *            the {@link BaseReportsRestResponse#outcome}.
	 */
	public GetReportsSummaryResponse(boolean outcome) {
		super(outcome);
	}

	/**
	 * Instantiates a new GetReportsSummaryResponse.
	 *
	 * @param errorMessage
	 *            the {@link BaseReportsRestResponse#errorMessage}.
	 */
	public GetReportsSummaryResponse(String errorMessage) {
		super(errorMessage);
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
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
		GetReportsSummaryResponse other = (GetReportsSummaryResponse) obj;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetReportsSummaryResponse [reports=" + reports + "]";
	}

}