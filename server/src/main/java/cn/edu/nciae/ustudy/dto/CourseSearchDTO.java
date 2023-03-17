package cn.edu.nciae.ustudy.dto;

import java.math.BigDecimal;

/**
 * @author YeLi
 * @date 2020/9/10 21:19
 */
public class CourseSearchDTO {

    /**
     * 课程名称
     */
    private String name;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 科目id
     */
    private Long subjectId;

    /**
     * 是否免费 0收费 1免费
     */
    private Boolean free;

    /**
     * 起始价钱
     */
    private BigDecimal startPrice;

    /**
     * 结束价钱
     */
    private BigDecimal EndPrice;

    /**
     * 授课方式
     */
    private String teachingMethods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getEndPrice() {
        return EndPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        EndPrice = endPrice;
    }

    public String getTeachingMethods() {
        return teachingMethods;
    }

    public void setTeachingMethods(String teachingMethods) {
        this.teachingMethods = teachingMethods;
    }
}
