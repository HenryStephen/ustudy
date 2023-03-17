package cn.edu.nciae.ustudy.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@TableName("`order`")
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

    private String id;

    private Long userId;

    /**
     * 提交时间
     */
    private LocalDateTime submitTime;

    /**
     * 已支付
     */
    @TableField("is_paid")
    private Boolean paid;

    /**
     * 付款时间
     */
    private LocalDateTime paymentTime;

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 优惠之后的total
     */
    private BigDecimal total;

    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    private Order(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setSubmitTime(builder.submitTime);
        setPaid(builder.paid);
        setPaymentTime(builder.paymentTime);
        setPaymentMethod(builder.paymentMethod);
        setDiscount(builder.discount);
        setTotal(builder.total);
        setDeleted(builder.deleted);
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        return "Order{" +
        "id=" + id +
        ", userId=" + userId +
        ", submitTime=" + submitTime +
        ", isPaid=" + paid +
        ", paymentTime=" + paymentTime +
        ", paymentMethod=" + paymentMethod +
        ", discount=" + discount +
        ", total=" + total +
        ", isDeleted=" + deleted +
        "}";
    }

    public static final class Builder {
        private String id;
        private Long userId;
        private LocalDateTime submitTime;
        private Boolean paid;
        private LocalDateTime paymentTime;
        private String paymentMethod;
        private BigDecimal discount;
        private BigDecimal total;
        private Boolean deleted;

        public Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder userId(Long val) {
            userId = val;
            return this;
        }

        public Builder submitTime(LocalDateTime val) {
            submitTime = val;
            return this;
        }

        public Builder paid(Boolean val) {
            paid = val;
            return this;
        }

        public Builder paymentTime(LocalDateTime val) {
            paymentTime = val;
            return this;
        }

        public Builder paymentMethod(String val) {
            paymentMethod = val;
            return this;
        }

        public Builder discount(BigDecimal val) {
            discount = val;
            return this;
        }

        public Builder total(BigDecimal val) {
            total = val;
            return this;
        }

        public Builder deleted(Boolean val) {
            deleted = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
