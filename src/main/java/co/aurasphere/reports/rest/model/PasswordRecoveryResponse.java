package co.aurasphere.reports.rest.model;

/**
 * Response for
 * {@link co.aurasphere.reports.rest.UserRestController#passwordRecovery(PasswordRecoveryRequest)}.
 * 
 * @author Donato Rimenti
 *
 */
public class PasswordRecoveryResponse extends BaseReportsRestResponse {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new PasswordRecoveryResponse.
	 */
	public PasswordRecoveryResponse() {
	}

	/**
	 * Instantiates a new PasswordRecoveryResponse.
	 *
	 * @param outcome
	 *            the {@link BaseReportsRestResponse#outcome}.
	 * @param errorMessage
	 *            the {@link BaseReportsRestResponse#errorMessage}.
	 */
	public PasswordRecoveryResponse(boolean outcome, String errorMessage) {
		super(outcome, errorMessage);
	}

}