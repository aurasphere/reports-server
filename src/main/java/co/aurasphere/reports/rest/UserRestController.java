package co.aurasphere.reports.rest;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.aurasphere.reports.model.User;
import co.aurasphere.reports.rest.model.BaseReportsRestResponse;
import co.aurasphere.reports.rest.model.ConfirmAccountRequest;
import co.aurasphere.reports.rest.model.ConfirmAccountResponse;
import co.aurasphere.reports.rest.model.CreateUserResponse;
import co.aurasphere.reports.rest.model.GetUserListResponse;
import co.aurasphere.reports.rest.model.PasswordRecoveryRequest;
import co.aurasphere.reports.rest.model.PasswordRecoveryResponse;
import co.aurasphere.reports.rest.model.UpdateUserOrdersRequest;
import co.aurasphere.reports.service.ReportsService;

/**
 * REST controller for operations on a {@link User}.
 * 
 * @author Donato Rimenti
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

	/**
	 * Logger.
	 */
	private final static Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	/**
	 * The service.
	 */
	@Autowired
	private ReportsService service;

	/**
	 * Creates a new user.
	 * 
	 * @param user
	 *            the user to create
	 * @return the outcome of the operation
	 * @throws IOException
	 */
	@PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CreateUserResponse createUser(@RequestBody User user) throws IOException {
		LOG.info("UserRestController.createUser(*)");
		return service.createUser(user);
	}

	/**
	 * Sends a confirmation email to activate an account.
	 *
	 * @param email
	 *            the email to activate
	 * @return the outcome of the operation
	 */
	@GetMapping(value = "/sendAccountActivationEmail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse sendAccountActivationEmail(@RequestParam("email") String email) {
		LOG.info("UserRestController.sendAccountActivationEmail({})", email);
		service.sendConfirmationEmailAgain(email);
		return new BaseReportsRestResponse(true, "Email succesfully sent");
	}

	/**
	 * Activates an account.
	 *
	 * @param request
	 *            contains the data of the account to activate
	 * @return the outcome of the operation
	 */
	@PostMapping(value = "/confirmAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ConfirmAccountResponse confirmAccount(@RequestBody ConfirmAccountRequest request) {
		LOG.info("UserRestController.confirmAccount(*)");
		boolean outcome = service.activateAccount(request.getEmail(), request.getToken());
		String message;
		if (outcome) {
			message = "Succesfully activated account.";
		} else {
			message = "The link is expired or invalid. Log in again to send a new activation email.";
		}
		return new ConfirmAccountResponse(outcome, message);
	}

	/**
	 * Sends an email to recover an account password.
	 *
	 * @param email
	 *            the email whose password needs to be recovered
	 * @return the outcome of the operation
	 */
	@GetMapping(value = "/sendRecoverPasswordEmail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse sendRecoverPasswordEmail(@RequestParam("email") String email) {
		LOG.info("UserRestController.sendRecoverPasswordEmail({})", email);
		service.sendRecoverPasswordEmail(email);
		return new BaseReportsRestResponse(true, "Email succesfully sent");
	}
	
	@GetMapping(value = "/getUserList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GetUserListResponse getUserList() {
		LOG.info("UserRestController.getUserList()");
		List<User> users = service.getUserList();
		return new GetUserListResponse(users);
	}
	
	@PostMapping(value = "/updateUserOrders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse updateUserOrders(@RequestBody UpdateUserOrdersRequest request) {
		LOG.info("UserRestController.updateUserOrders()");
		service.updateUserOrders(request.getUser());
		return new BaseReportsRestResponse(true);
	}

	/**
	 * Confirms the password recovery by storing the new password.
	 *
	 * @param request
	 *            contains the data of the account whose password needs to be
	 *            recovered
	 * @return the outcome of the operation
	 */
	@PostMapping(value = "/passwordRecovery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PasswordRecoveryResponse passwordRecovery(@RequestBody PasswordRecoveryRequest request) {
		LOG.info("UserRestController.passwordRecovery(*)");
		boolean outcome = service.passwordRecovery(request.getEmail(), request.getToken(), request.getNewPassword());
		String message;
		if (outcome) {
			message = "New password succesfully saved.";
		} else {
			message = "The link is expired or invalid.";
		}
		return new PasswordRecoveryResponse(outcome, message);
	}

	/**
	 * This webservice does nothing. It is used only to extend the user session
	 * if needed.
	 */
	@GetMapping("/extendUserSession")
	public void extendUserSession() {
	}

}