package com.lz.Exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: lz
 * @Date: 2024/01/03/23:52
 * @Description:
 */

/**
 * @author lz
 */
public class NoAthleteException extends Exception{
    private String message;

    public NoAthleteException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance
     * (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public void setMessage(String message) {
        this.message = message;
    }
}