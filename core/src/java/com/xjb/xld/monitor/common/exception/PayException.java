/**
 * 
 */
package com.xjb.xld.monitor.common.exception;

import com.xjb.xld.monitor.common.support.ResponseCodeProperties;

public class PayException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7002036086093717394L;
	/**
	 * 错误返回码
	 */
	private int retCode;
	/**
	 * 异常
	 */
	private Throwable cause;
	/**
	 * 异常描述
	 */
	private String causeMessage;
	/**
	 * 异常类型
	 */
	private ExceptionType exceptionType;

	public PayException(int retCode) {
		super();
		this.retCode = retCode;
		this.causeMessage = ResponseCodeProperties.getProperty(retCode);
	}

	public PayException(int retCode, Throwable cause, String causeMessage) {
		super();
		this.retCode = retCode;
		this.cause = cause;
		this.causeMessage = causeMessage;
	}

	public PayException(int retCode, Throwable cause) {
		super();
		this.retCode = retCode;
		this.cause = cause;
		this.causeMessage = ResponseCodeProperties.getProperty(retCode);
	}

	public PayException(int retCode, Throwable cause, String causeMessage,
			ExceptionType exceptionType) {
		super();
		this.retCode = retCode;
		this.cause = cause;
		this.causeMessage = causeMessage;
		this.exceptionType = exceptionType;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public String getCauseMessage() {
		return causeMessage;
	}

	public void setCauseMessage(String causeMessage) {
		this.causeMessage = causeMessage;
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

}