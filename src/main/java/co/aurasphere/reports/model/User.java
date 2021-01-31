package co.aurasphere.reports.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents a user of this application.
 * 
 * @author Donato Rimenti
 */
@Document(collection = "user")
public class User implements UserDetails {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the user.
	 */
	private String name;

	/**
	 * The surname of the user.
	 */
	private String surname;

	/**
	 * The birthday of the user.
	 */
	private LocalDate birthday;

	/**
	 * The email of the user. Must be unique.
	 */
	@Id
	private String email;

	/**
	 * The authorities of the user which represents its granted privileges.
	 */
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * The password of the user.
	 */
	private String password;

	/**
	 * The date and time the user was created.
	 */
	private LocalDateTime creationTime;

	/**
	 * The date and time the user was last updated.
	 */
	private LocalDateTime updateTime;

	/**
	 * Whether or not this account is enabled.
	 */
	private boolean enabled;

	/**
	 * Orders of the user.
	 */
	private List<Order> orders;

	/**
	 * User refund.
	 */
	private BigDecimal refund;

	/**
	 * User fare.
	 */
	private BigDecimal fare;

	/**
	 * Type of refund.
	 */
	private RefundType refundType;

	/**
	 * Gets the {@link #name}.
	 *
	 * @return the {@link #name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the {@link #name}.
	 *
	 * @param name
	 *            the new {@link #name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the {@link #surname}.
	 *
	 * @return the {@link #surname}
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the {@link #surname}.
	 *
	 * @param surname
	 *            the new {@link #surname}
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the {@link #birthday}.
	 *
	 * @return the {@link #birthday}
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * Sets the {@link #birthday}.
	 *
	 * @param birthday
	 *            the new {@link #birthday}
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Sets the {@link #authorities}.
	 *
	 * @param authorities
	 *            the new {@link #authorities}
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the {@link #password}.
	 *
	 * @param password
	 *            the new {@link #password}
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the {@link #creationTime}.
	 *
	 * @return the {@link #creationTime}
	 */
	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	/**
	 * Sets the {@link #creationTime}.
	 *
	 * @param creationTime
	 *            the new {@link #creationTime}
	 */
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * Gets the {@link #updateTime}.
	 *
	 * @return the {@link #updateTime}
	 */
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the {@link #updateTime}.
	 *
	 * @param updateTime
	 *            the new {@link #updateTime}
	 */
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the {@link #enabled}.
	 *
	 * @param enabled
	 *            the new {@link #enabled}
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the {@link #orders}.
	 *
	 * @return the {@link #orders}
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Sets the {@link #orders}.
	 *
	 * @param orders
	 *            the new {@link #orders}
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Gets the {@link #refund}.
	 *
	 * @return the {@link #refund}
	 */
	public BigDecimal getRefund() {
		return refund;
	}

	/**
	 * Sets the {@link #refund}.
	 *
	 * @param refund
	 *            the new {@link #refund}
	 */
	public void setRefund(BigDecimal refund) {
		this.refund = refund;
	}

	/**
	 * Gets the {@link #fare}.
	 *
	 * @return the {@link #fare}
	 */
	public BigDecimal getFare() {
		return fare;
	}

	/**
	 * Sets the {@link #fare}.
	 *
	 * @param fare
	 *            the new {@link #fare}
	 */
	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	/**
	 * Gets the {@link #refundType}.
	 *
	 * @return the {@link #refundType}
	 */
	public RefundType getRefundType() {
		return refundType;
	}

	/**
	 * Sets the {@link #refundType}.
	 *
	 * @param refundType
	 *            the new {@link #refundType}
	 */
	public void setRefundType(RefundType refundType) {
		this.refundType = refundType;
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
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((fare == null) ? 0 : fare.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((refund == null) ? 0 : refund.hashCode());
		result = prime * result + ((refundType == null) ? 0 : refundType.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
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
		User other = (User) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (fare == null) {
			if (other.fare != null)
				return false;
		} else if (!fare.equals(other.fare))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (refund == null) {
			if (other.refund != null)
				return false;
		} else if (!refund.equals(other.refund))
			return false;
		if (refundType != other.refundType)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
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
		return "User [name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", email=" + email
				+ ", authorities=" + authorities + ", password=" + password + ", creationTime=" + creationTime
				+ ", updateTime=" + updateTime + ", enabled=" + enabled + ", orders=" + orders + ", refund=" + refund
				+ ", fare=" + fare + ", refundType=" + refundType + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return getEmail();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}