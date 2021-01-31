package co.aurasphere.reports.rest.model;

/**
 * Response for
 * {@link co.aurasphere.reports.rest.UserRestController#createUser(User, javax.servlet.http.HttpServletRequest)}.
 * 
 * @author Donato Rimenti
 */
public class CreateUserResponse extends BaseReportsRestResponse {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new CreateUserResponse.
	 */
	public CreateUserResponse() {
	}

	/**
	 * Instantiates a new CreateUserResponse.
	 *
	 * @param outcome
	 *            the {@link BaseInterviewServletRestResponse#outcome}.
	 */
	public CreateUserResponse(boolean outcome) {
		super(outcome);
	}

	/**
	 * Instantiates a new CreateUserResponse.
	 *
	 * @param errorMessage
	 *            the {@link BaseInterviewServletRestResponse#errorMessage}.
	 */
	public CreateUserResponse(String errorMessage) {
		super(errorMessage);
	}
	
	
	public CreateUserResponse(String errorMessage, boolean outcome) {
		this.errorMessage = errorMessage;
		this.outcome = outcome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CreateUserResponse [outcome=" + outcome + ", errorMessage=" + errorMessage + "]";
	}

}