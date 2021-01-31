package co.aurasphere.reports.rest.model;

import java.io.Serializable;

/**
 * Request for
 * {@link co.aurasphere.reports.rest.UserRestController#passwordRecovery(PasswordRecoveryRequest)}.
 * 
 * @author Donato Rimenti
 * 
 */
public class PasswordRecoveryRequest implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The email of the user whose password needs to be recovered.
	 */
	private String email;

	/**
	 * The security token for the operation.
	 */
	private String token;

	/**
	 * The new password of the user.
	 */
	private String newPassword;

	/**
	 * Gets the {@link #email}.
	 *
	 * @return the {@link #email}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the {@link #email}.
	 *
	 * @param email
	 *            the new {@link #email}
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the {@link #token}.
	 *
	 * @return the {@link #token}
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the {@link #token}.
	 *
	 * @param token
	 *            the new {@link #token}
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the {@link #newPassword}.
	 *
	 * @return the {@link #newPassword}
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the {@link #newPassword}.
	 *
	 * @param newPassword
	 *            the new {@link #newPassword}
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((newPassword == null) ? 0 : newPassword.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordRecoveryRequest other = (PasswordRecoveryRequest) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (newPassword == null) {
			if (other.newPassword != null)
				return false;
		} else if (!newPassword.equals(other.newPassword))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PasswordRecoveryRequest [email=" + email + ", token=" + token + ", newPassword=" + newPassword + "]";
	}

}
