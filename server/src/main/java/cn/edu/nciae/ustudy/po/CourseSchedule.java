package cn.edu.nciae.ustudy.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * @author YeLi
 * @date 2020-09-06
 */
public class CourseSchedule extends Model<CourseSchedule> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long courseId;

    /**
     * 章节或第几讲名称
     */
    private String name;

    /**
     * 类型（0代表章节；1代表讲）
     */
    private Integer level;

    /**
     * 章节无父id
     */
    private Long parentId;

    /**
     * 授课方式（直播、面授、视频、资料）
     */
    private String type;

    @TableField("is_deleted")
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "CourseSchedule{" +
        "id=" + id +
        ", courseId=" + courseId +
        ", name=" + name +
        ", level=" + level +
        ", parentId=" + parentId +
        ", type=" + type +
        ", isDeleted=" + deleted +
        "}";
    }
}
