/**
 * 
 */
package com.xjb.xld.monitor.common.support;

/**
 * 
 * @author zhipeng.zheng 2013-5-2 下午2:20:30
 */
public class ResponseCode {
	public static final int OK = 0;
	public static final int SYSTEM_ERROR = 50000;
	public static final int FUNDBIZ_TRANSFER_ERROR = 50001;
	public static final int SYSTEM_PARAMETERS_EMPTY = 50002;
	public static final int SYSTEM_PARAMETERS_FORMAT_ERROR = 50003;
	public static final int FUNDBIZ_EMPTY_RESULT = 50004;

	public static final int MERCHANT_NOT_EXIST = 10001;
	public static final int MERCHANT_LOGIN_ERROR = 10002;
	public static final int BIZ_PAY_WRITE_ERROR = 10003;
	public static final int BIZ_APPLY_WRITE_ERROR = 10004;
	public static final int BIZ_APPLY_EXIST = 10005;

	public static final int CHANNEL_NOT_EXIST = 10006;
	public static final int BIZ_APPLY_NOT_EXIST = 10007;
	public static final int BIZID_APPLYID_NOTMATCH = 10008;
	public static final int DATE_FORMAT_ERROR = 10009;
	public static final int INTEGER_FORMAT_ERROR = 10010;
	/**
	 * 备付金不足
	 */
	public static final int PAY_MONEY_SHORTAGE = 10011;
	/**
	 * 签名验证失败
	 */
	public static final int PAY_CHANNEL_VALID_FAIL = 10012;
	/**
	 * 接口访问验证签名失败
	 */
	public static final int INTERFACE_VALID_FAIL = 10013;
}
