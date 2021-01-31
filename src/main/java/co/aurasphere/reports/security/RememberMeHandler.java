package co.aurasphere.reports.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Extracts the remember me cookie from an header of an HTTP request.
 * 
 * @author Donato Rimenti
 *
 */
public class RememberMeHandler extends TokenBasedRememberMeServices {

	/**
	 * Remember me cookie header name.
	 */
	private final String REMEMBER_ME_COOKIE_NAME = "reports-remember-me";

	/**
	 * Instantiates a new RememberMeHandler.
	 *
	 * @param key
	 *            the {@link TokenBasedRememberMeServices#key}.
	 * @param userDetailsService
	 *            the {@link TokenBasedRememberMeServices#userDetailsService}.
	 */
	public RememberMeHandler(String key, UserDetailsService userDetailsService) {
		super(key, userDetailsService);

		// Custom name for the cookie.
		setCookieName(REMEMBER_ME_COOKIE_NAME);
		setUseSecureCookie(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.rememberme.
	 * AbstractRememberMeServices#extractRememberMeCookie(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected String extractRememberMeCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(REMEMBER_ME_COOKIE_NAME)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}