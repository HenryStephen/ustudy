package cn.edu.nciae.ustudy.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhang
 * @date 2020/9/14
 */
public class OrderDTO {
	private String orderId;
	private LocalDateTime submitTime;
	private Boolean paid;
	private LocalDateTime paymentTime;
	private String paymentMethod;
	private BigDecimal total;
	private List<CourseViewDTO> courseViewDTOList;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<CourseViewDTO> getCourseViewDTOList() {
		return courseViewDTOList;
	}

	public void setCourseViewDTOList(List<CourseViewDTO> courseViewDTOList) {
		this.courseViewDTOList = courseViewDTOList;
	}

	@Override
	public String toString() {
		return "OrderDTO{" +
				"orderId='" + orderId + '\'' +
				", submitTime=" + submitTime +
				", paid=" + paid +
				", paymentTime=" + paymentTime +
				", paymentMethod='" + paymentMethod + '\'' +
				", total=" + total +
				", courseViewDTOList=" + courseViewDTOList +
				'}';
	}
}
