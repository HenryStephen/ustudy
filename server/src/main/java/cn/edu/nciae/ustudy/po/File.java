package cn.edu.nciae.ustudy.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YeLi
 * @date 2020-09-06
 */
public class File extends Model<File> {

    private static final long serialVersionUID=1L;

    @TableId
    private String id;

    private Long courseId;

    /**
     * 上传用户id
     */
    private Long userId;

    /**
     * 课时安排id
     */
    private Long scheduleId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件类型
     */
    private String type;

    /**
     * 上传时间
     */
    private LocalDateTime time;

    /**
     * 文件大小  /bytes
     */
    private Long fileSize;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    public File() {
    }

    public File(String id, Long courseId, Long userId, Long scheduleId, String name, String type, LocalDateTime time, Long fileSize) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.name = name;
        this.type = type;
        this.time = time;
        this.fileSize = fileSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
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
        return "File{" +
        "id=" + id +
        ", courseId=" + courseId +
        ", userId=" + userId +
        ", scheduleId=" + scheduleId +
        ", name=" + name +
        ", type=" + type +
        ", time=" + time +
        ", fileSize=" + fileSize +
        ", isDeleted=" + deleted +
        "}";
    }


}
