package co.aurasphere.reports.rest.model;

import java.io.Serializable;

import co.aurasphere.reports.model.User;

/**
 * Request for
 * {@link co.aurasphere.reports.rest.UserRestController#updateUserOrders(UpdateUserOrdersRequest)}.
 * 
 * @author Donato Rimenti
 *
 */
public class UpdateUserOrdersRequest implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The user object to be saved.
	 */
	private User user;

	/**
	 * Gets the {@link #user}.
	 *
	 * @return the {@link #user}
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the {@link #user}.
	 *
	 * @param user
	 *            the new {@link #user}
	 */
	public void setUser(User user) {
		this.user = user;
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
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UpdateUserOrdersRequest other = (UpdateUserOrdersRequest) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
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
		return "UpdateUserOrdersRequest [user=" + user + "]";
	}

}
