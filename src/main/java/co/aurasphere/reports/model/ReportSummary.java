package co.aurasphere.reports.model;

import java.util.List;

public class ReportSummary {
	
	private String username;
	
	private String userId;
	
	private List<OrderSummary> orderSummary;
	
	private int totalWorked;

	private int totalOvertime;

	private int totalVacation;
	
	private int totalLeave;
	
	private int totalMarriage;
	
	private int totalDeath;
	
	private int totalOthers;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<OrderSummary> getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(List<OrderSummary> orderSummary) {
		this.orderSummary = orderSummary;
	}

	public int getTotalWorked() {
		return totalWorked;
	}

	public void setTotalWorked(int totalWorked) {
		this.totalWorked = totalWorked;
	}

	public int getTotalOvertime() {
		return totalOvertime;
	}

	public void setTotalOvertime(int totalOvertime) {
		this.totalOvertime = totalOvertime;
	}

	public int getTotalVacation() {
		return totalVacation;
	}

	public void setTotalVacation(int totalVacation) {
		this.totalVacation = totalVacation;
	}

	public int getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	public int getTotalMarriage() {
		return totalMarriage;
	}

	public void setTotalMarriage(int totalMarriage) {
		this.totalMarriage = totalMarriage;
	}

	public int getTotalDeath() {
		return totalDeath;
	}

	public void setTotalDeath(int totalDeath) {
		this.totalDeath = totalDeath;
	}

	public int getTotalOthers() {
		return totalOthers;
	}

	public void setTotalOthers(int totalOthers) {
		this.totalOthers = totalOthers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderSummary == null) ? 0 : orderSummary.hashCode());
		result = prime * result + totalDeath;
		result = prime * result + totalLeave;
		result = prime * result + totalMarriage;
		result = prime * result + totalOthers;
		result = prime * result + totalOvertime;
		result = prime * result + totalVacation;
		result = prime * result + totalWorked;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		ReportSummary other = (ReportSummary) obj;
		if (orderSummary == null) {
			if (other.orderSummary != null)
				return false;
		} else if (!orderSummary.equals(other.orderSummary))
			return false;
		if (totalDeath != other.totalDeath)
			return false;
		if (totalLeave != other.totalLeave)
			return false;
		if (totalMarriage != other.totalMarriage)
			return false;
		if (totalOthers != other.totalOthers)
			return false;
		if (totalOvertime != other.totalOvertime)
			return false;
		if (totalVacation != other.totalVacation)
			return false;
		if (totalWorked != other.totalWorked)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReportSummary [username=" + username + ", userId=" + userId + ", orderSummary=" + orderSummary
				+ ", totalWorked=" + totalWorked + ", totalOvertime=" + totalOvertime + ", totalVacation="
				+ totalVacation + ", totalLeave=" + totalLeave + ", totalMarriage=" + totalMarriage + ", totalDeath="
				+ totalDeath + ", totalOthers=" + totalOthers + "]";
	}

}