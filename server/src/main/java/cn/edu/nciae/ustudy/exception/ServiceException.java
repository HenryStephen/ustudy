package cn.edu.nciae.ustudy.exception;

/**
 * @author tang
 * @date 2020/9/8 10:59
 */
public class ServiceException extends RuntimeException {

    private int status;

    private String error;

    private String message;

    public ServiceException(String message) {
        this.status = 500;
        this.message = message;
    }

    public ServiceException(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
