package co.aurasphere.reports.model;

public class OrderSummary {

	private String customer;

	private int worked;

	private int overtime;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getWorked() {
		return worked;
	}

	public void setWorked(int worked) {
		this.worked = worked;
	}

	public int getOvertime() {
		return overtime;
	}

	public void setOvertime(int overtime) {
		this.overtime = overtime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + overtime;
		result = prime * result + worked;
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
		OrderSummary other = (OrderSummary) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (overtime != other.overtime)
			return false;
		if (worked != other.worked)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderSummary [customer=" + customer + ", worked=" + worked + ", overtime=" + overtime + "]";
	}

}
