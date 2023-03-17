package cn.edu.nciae.ustudy.dto;

import cn.edu.nciae.ustudy.po.Favorites;

import java.math.BigDecimal;

/**
 * @author zhang
 * @date 2020/9/20
 */
public class UserFavoriteDTO extends Favorites {
	private String courseName;
	private BigDecimal coursePrice;
	private Integer studentsNumber;
	private Integer classHour;

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

	public Integer getStudentsNumber() {
		return studentsNumber;
	}

	public void setStudentsNumber(Integer studentsNumber) {
		this.studentsNumber = studentsNumber;
	}

	public Integer getClassHour() {
		return classHour;
	}

	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}
}
