package co.aurasphere.reports.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.aurasphere.reports.rest.model.AuthenticationResponse;

/**
 * Handler for failed authentication. Returns a 401 error along with a JSON
 * response with a detailed error message.
 * 
 * @author Donato Rimenti
 */
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	/**
	 * Logger.
	 */
	private final static Logger LOG = LoggerFactory.getLogger(AuthFailureHandler.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOG.trace("AuthFailureHandler.onAuthenticationFailure");
		ObjectMapper mapper = new ObjectMapper();
		AuthenticationResponse responseJson = new AuthenticationResponse(exception.getMessage());
		mapper.writeValue(response.getWriter(), responseJson);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().flush();
	}
}