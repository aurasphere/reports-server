package co.aurasphere.reports.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.aurasphere.reports.model.User;
import co.aurasphere.reports.rest.model.AuthenticationResponse;

/**
 * Handler for successful authentication. Returns a code 200 along with a JSON
 * response with the authenticated user.
 * 
 * @author Donato Rimenti
 */
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	/**
	 * Logger.
	 */
	private final static Logger LOG = LoggerFactory.getLogger(AuthSuccessHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SimpleUrlAuthenticationSuccessHandler#onAuthenticationSuccess(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		LOG.trace("AuthSuccessHandler.onAuthenticationSuccess");
		ObjectMapper mapper = new ObjectMapper();
		User principal = (User) authentication.getPrincipal();
		AuthenticationResponse responseJson = new AuthenticationResponse(true, principal);
		mapper.writeValue(response.getWriter(), responseJson);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().flush();
	}
}