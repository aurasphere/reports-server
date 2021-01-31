package co.aurasphere.reports.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Entry point for REST services within this application.
 * 
 * @author Donato Rimenti
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/**
	 * Logger.
	 */
	private final static Logger LOG = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.AuthenticationEntryPoint#commence(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		LOG.trace("RestAuthenticationEntryPoint.commence");
		// Returns a 401 error.
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}