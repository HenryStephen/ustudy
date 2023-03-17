package cn.edu.nciae.ustudy.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author YeLi
 * @date 2020-09-06
 */
public class Course extends Model<Course> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 预览图
     */
    private String previewPicture;

    /**
     * 免费
     */
    @TableField("is_free")
    private Boolean free;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal presentPrice;

    /**
     * 分类id
     */
    private Long categoryId;

    private Long subjectId;

    /**
     * 授课方式（用+间隔）
     */
    private String teachingMethods;

    /**
     * 推出时间
     */
    private LocalDate publishedTime;

    /**
     * 学习人数
     */
    private Integer studentsNumber;

    /**
     * 有效期
     */
    private Integer validDays;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 课时
     */
    private Integer classHour;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewPicture() {
        return previewPicture;
    }

    public void setPreviewPicture(String previewPicture) {
        this.previewPicture = previewPicture;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(BigDecimal presentPrice) {
        this.presentPrice = presentPrice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTeachingMethods() {
        return teachingMethods;
    }

    public void setTeachingMethods(String teachingMethods) {
        this.teachingMethods = teachingMethods;
    }

    public LocalDate getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(LocalDate publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Integer getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(Integer studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getClassHour() {
        return classHour;
    }

    public void setClassHour(Integer classHour) {
        this.classHour = classHour;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", name=" + name +
        ", priviewPicture=" + previewPicture +
        ", isFree=" + free +
        ", originalPrice=" + originalPrice +
        ", presentPrice=" + presentPrice +
        ", categoryId=" + categoryId +
        ", teachingMethods=" + teachingMethods +
        ", publishedTime=" + publishedTime +
        ", studentsNumber=" + studentsNumber +
        ", validDays=" + validDays +
        ", phone=" + phone +
        ", classHour=" + classHour +
        ", isDeleted=" + deleted +
        "}";
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
