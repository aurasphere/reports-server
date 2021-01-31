package co.aurasphere.reports.rest;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.aurasphere.reports.model.ExpenseAccount;
import co.aurasphere.reports.model.Report;
import co.aurasphere.reports.model.Timesheet;
import co.aurasphere.reports.model.User;
import co.aurasphere.reports.rest.model.BaseReportsRestResponse;
import co.aurasphere.reports.rest.model.GetReportsSummaryResponse;
import co.aurasphere.reports.rest.model.GetUserReportResponse;
import co.aurasphere.reports.rest.model.SaveUserExpenseAccountRequest;
import co.aurasphere.reports.rest.model.SaveUserTimesheetRequest;
import co.aurasphere.reports.rest.model.UpdateReportsLockRequest;
import co.aurasphere.reports.service.ReportsService;

/**
 * REST controller for operations on a {@link Report}.
 * 
 * @author Donato Rimenti
 */
@RestController
@RequestMapping("/report")
public class ReportRestController {

	/**
	 * Logger.
	 */
	private final static Logger LOG = LoggerFactory.getLogger(ReportRestController.class);

	/**
	 * The service.
	 */
	@Autowired
	private ReportsService service;

	/**
	 * Returns the {@link Report} of a user for a given month and year.
	 *
	 * @param date
	 *            the date of the report which needs to be retrieved
	 * @return the report of a user for a given month and year
	 */
	@GetMapping(value = "/getUserReport", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GetUserReportResponse getUserReport(@RequestParam("date") YearMonth date, Authentication authentication) {
		LOG.info("ReportRestController.getUserReport({})", date);
		Report report = service.getUserReport(getUsernameFromAuthentication(authentication), date);
		return new GetUserReportResponse(report);
	}

	/**
	 * Returns the {@link Report} of a user for a given month and year.
	 *
	 * @param date
	 *            the date of the report which needs to be retrieved
	 * @param user
	 *            the user whose report must be retrieved
	 * @return the report of a user for a given month and year
	 */
	@GetMapping(value = "/getUserReportForAdmin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GetUserReportResponse getUserReportForAdmin(@RequestParam("date") YearMonth date, String user) {
		LOG.info("ReportRestController.getUserReportForAdmin({})", date);
		Report report = service.getUserReport(user, date);
		return new GetUserReportResponse(report);
	}

	/**
	 * Saves the {@link Timesheet} data of a user for a given month and year.
	 *
	 * @param request
	 *            the data of the timesheet to be saved
	 * @param authentication
	 *            the user credentials
	 * @return the outcome of the operation
	 */
	@PostMapping(value = "/saveUserTimesheet", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse saveUserTimesheet(@RequestBody SaveUserTimesheetRequest request,
			Authentication authentication) {
		LOG.info("ReportRestController.saveUserTimesheet({})", request);
		service.saveUserTimesheet(getUsernameFromAuthentication(authentication), request.getTimesheet(),
				request.getTimesheetDate());
		return new BaseReportsRestResponse(true);
	}

	/**
	 * Saves the {@link ExpenseAccount} data of a user for a given month and
	 * year.
	 *
	 * @param request
	 *            the data of the expense account to be saved
	 * @param authentication
	 *            the user credentials
	 * @return the outcome of the operation
	 */
	@PostMapping(value = "/saveUserExpenseAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse saveUserExpenseAccount(@RequestBody SaveUserExpenseAccountRequest request,
			Authentication authentication) {
		LOG.info("ReportRestController.saveUserExpenseAccount({})", request);
		service.saveUserExpenseAccount(getUsernameFromAuthentication(authentication), request.getExpenseAccount(),
				request.getExpenseAccountDate());
		return new BaseReportsRestResponse(true);
	}

	@GetMapping(value = "/getReportsSummary", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse getReportsSummary(@RequestParam("date") YearMonth date) {
		LOG.info("ReportRestController.getReportsSummary({})", date);
		List<Report> reports = service.getReportsSummary(date);
		return new GetReportsSummaryResponse(reports);
	}

	@PostMapping(value = "/updateReportsLock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BaseReportsRestResponse updateReportsLock(@RequestBody UpdateReportsLockRequest request) {
		LOG.info("ReportRestController.updateReportsLock(*)", request);
		service.updateReportsLock(request.getReports());
		return new BaseReportsRestResponse(true);
	}

	@GetMapping(value = "/getExcelReportSummary", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getExcelReportSummary(@RequestParam("reportDate") YearMonth reportDate, HttpServletResponse response)
			throws IOException {
		LOG.info("ReportRestController.getExcelReportSummary(*)", reportDate);
		service.getExcelReportSummary(reportDate, response.getOutputStream());
	}

	/**
	 * Utility method which returns a user email from an {@link Authentication}
	 * object.
	 * 
	 * @param authentication
	 *            the user authentication
	 * @return the user email
	 */
	public String getUsernameFromAuthentication(Authentication authentication) {
		return ((User) authentication.getPrincipal()).getEmail();
	}

}