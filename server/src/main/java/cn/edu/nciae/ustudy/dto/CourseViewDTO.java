package cn.edu.nciae.ustudy.dto;

import java.math.BigDecimal;

/**
 * @author zhang
 * @date 2020/9/14
 */
public class CourseViewDTO {
	private Long courseId;
	private String courseName;
	private BigDecimal coursePrice;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}

	@Override
	public String toString() {
		return "CourseViewDTO{" +
				"courseId=" + courseId +
				", courseName='" + courseName + '\'' +
				", coursePrice=" + coursePrice +
				'}';
	}
}
