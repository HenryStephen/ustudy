package cn.edu.nciae.ustudy.config;

import java.io.Serializable;

/**
 * Global response format.
 *
 * @author YeLi
 * @date 2020/9/7 16:17
 */
public class Result<T> implements Serializable {

    public Result() {
    }

    /**
     * @param error 错误
     * @param status 状态码
     * @param message 提示信息
     * @param data 数据
     */
    private Result(int status, T data, String error, String message) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200, data, null, null);
    }

    public static <T> Result<T> fail(int status, String error, String message) {
        return new Result<>(status, null, error, message);
    }

    private int status;

    private T data;

    private String error;

    private String message;

    private long timestamp;

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", data=" + data +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
