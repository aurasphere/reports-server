package co.aurasphere.reports.model;

import java.math.BigDecimal;

public class ExpenseAccountRow {
	
	private int day;
	
	private String customer;
	
	private String place;
	
	private String description;
	
	private BigDecimal km;
	
	private BigDecimal dailyRefund;
	
	private BigDecimal transportation;
	
	private BigDecimal accomodation;
	
	private BigDecimal meals;
	
	private BigDecimal other;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getKm() {
		return km;
	}

	public void setKm(BigDecimal km) {
		this.km = km;
	}

	public BigDecimal getDailyRefund() {
		return dailyRefund;
	}

	public void setDailyRefund(BigDecimal dailyRefund) {
		this.dailyRefund = dailyRefund;
	}

	public BigDecimal getTransportation() {
		return transportation;
	}

	public void setTransportation(BigDecimal transportation) {
		this.transportation = transportation;
	}

	public BigDecimal getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(BigDecimal accomodation) {
		this.accomodation = accomodation;
	}

	public BigDecimal getMeals() {
		return meals;
	}

	public void setMeals(BigDecimal meals) {
		this.meals = meals;
	}

	public BigDecimal getOther() {
		return other;
	}

	public void setOther(BigDecimal other) {
		this.other = other;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accomodation == null) ? 0 : accomodation.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dailyRefund == null) ? 0 : dailyRefund.hashCode());
		result = prime * result + day;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((km == null) ? 0 : km.hashCode());
		result = prime * result + ((meals == null) ? 0 : meals.hashCode());
		result = prime * result + ((other == null) ? 0 : other.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((transportation == null) ? 0 : transportation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpenseAccountRow other = (ExpenseAccountRow) obj;
		if (accomodation == null) {
			if (other.accomodation != null)
				return false;
		} else if (!accomodation.equals(other.accomodation))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (dailyRefund == null) {
			if (other.dailyRefund != null)
				return false;
		} else if (!dailyRefund.equals(other.dailyRefund))
			return false;
		if (day != other.day)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (km == null) {
			if (other.km != null)
				return false;
		} else if (!km.equals(other.km))
			return false;
		if (meals == null) {
			if (other.meals != null)
				return false;
		} else if (!meals.equals(other.meals))
			return false;
		if (this.other == null) {
			if (other.other != null)
				return false;
		} else if (!this.other.equals(other.other))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (transportation == null) {
			if (other.transportation != null)
				return false;
		} else if (!transportation.equals(other.transportation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExpenseAccountRow [day=" + day + ", customer=" + customer + ", place=" + place + ", description="
				+ description + ", km=" + km + ", dailyRefund=" + dailyRefund + ", transportation=" + transportation
				+ ", accomodation=" + accomodation + ", meals=" + meals + ", other=" + other + "]";
	}

}
