package co.aurasphere.reports.rest.model;

import java.util.List;

import co.aurasphere.reports.model.User;

/**
 * Response for {@link co.aurasphere.reports.rest.UserRestController#getUserList()}.
 * 
 * @author Donato Rimenti
 */
public class GetUserListResponse extends BaseReportsRestResponse {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The requested users.
	 */
	private List<User> users;

	/**
	 * Instantiates a new GetUserListResponse.
	 */
	public GetUserListResponse() {
	}

	/**
	 * Instantiates a new GetUserListResponse.
	 *
	 * @param users
	 *            the {@link #users}
	 */
	public GetUserListResponse(List<User> users) {
		super(true);
		this.users = users;
	}

	/**
	 * Gets the {@link #users}.
	 *
	 * @return the {@link #users}
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the {@link #users}.
	 *
	 * @param users
	 *            the new {@link #users}
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetUserListResponse other = (GetUserListResponse) obj;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetUserListResponse [users=" + users + "]";
	}

}