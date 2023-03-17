package cn.edu.nciae.ustudy.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YeLi
 * @date 2020-09-06
 */
public class OrderItem extends Model<OrderItem> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String orderId;

    private Long courseId;

    /**
     * 课程价钱
     */
    private BigDecimal coursePrice;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    @TableField("is_quitted")
    private Boolean quitted;

    public OrderItem() {
    }

    private OrderItem(Builder builder) {
        setId(builder.id);
        setOrderId(builder.orderId);
        setCourseId(builder.courseId);
        setCoursePrice(builder.coursePrice);
        setDeleted(builder.deleted);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getQuitted() {
        return quitted;
    }

    public void setQuitted(Boolean quitted) {
        this.quitted = quitted;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", courseId=" + courseId +
                ", coursePrice=" + coursePrice +
                ", deleted=" + deleted +
                ", quitted=" + quitted +
                '}';
    }

    public static final class Builder {
        private Long id;
        private String orderId;
        private Long courseId;
        private BigDecimal coursePrice;
        private Boolean deleted;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder orderId(String val) {
            orderId = val;
            return this;
        }

        public Builder courseId(Long val) {
            courseId = val;
            return this;
        }

        public Builder coursePrice(BigDecimal val) {
            coursePrice = val;
            return this;
        }

        public Builder deleted(Boolean val) {
            deleted = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
