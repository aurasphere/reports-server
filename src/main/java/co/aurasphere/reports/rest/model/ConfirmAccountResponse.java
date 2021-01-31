package co.aurasphere.reports.rest.model;

/**
 * Response for
 * {@link co.aurasphere.reports.rest.UserRestController#confirmAccount(ConfirmAccountRequest)}.
 * 
 * @author Donato Rimenti
 *
 */
public class ConfirmAccountResponse extends BaseReportsRestResponse {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new ConfirmAccountResponse.
	 */
	public ConfirmAccountResponse() {
	}

	/**
	 * Instantiates a new ConfirmAccountResponse.
	 *
	 * @param outcome
	 *            the {@link BaseReportsRestResponse#outcome}.
	 * @param errorMessage
	 *            the {@link BaseReportsRestResponse#errorMessage}.
	 */
	public ConfirmAccountResponse(boolean outcome, String errorMessage) {
		super(outcome, errorMessage);
	}

}
