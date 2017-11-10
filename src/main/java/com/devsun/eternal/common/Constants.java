package com.devsun.eternal.common;

import com.devsun.tool.base.common.BaseConstants;

public class Constants extends BaseConstants {
	
	/** 普通用户的Session存储名称 */
	public static String SESSION_NAME_USER = "sessionUser";
	
	/** 管理员的Session存储名称 */
	public static String SESSION_NAME_ADMIN = "sessionAdmin";
	
	/** User类型 */
	public final static int USER_TYPE_USER = 0;		//普通
	public final static int USER_TYPE_ADMIN = 1;	//管理员
	
	/** User状态 */
	public final static int USER_STATUS_DISABLED = 0;//禁用
	public final static int USER_STATUS_ENABLED = 1;//启用
	
	/** HttpConfig Id */
	public final static String HTTP_CONFIG_ID_RESULT1 = "result1";
	public final static String HTTP_RESULT1_HOME = "/one/LLP";
}
