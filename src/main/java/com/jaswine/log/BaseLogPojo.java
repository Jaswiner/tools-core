package com.jaswine.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jaswine.serial.SnowFlake;
import lombok.Data;

import java.util.Date;

/**
 * 日志对象
 *
 * @author : Jaswine
 * @date : 2020-04-16 22:54
 **/
@Data
public abstract class BaseLogPojo {

	/** id */
	private Long id = SnowFlake.nextId();
	/** 操作人 */
	private String username;
	/** 操作说明 */
	private String operation;
	/** HTTP方法 */
	private String methodType;
	/** 时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss",timezone = "GMT+8")
	private Date startTime;
	/** 耗时(ms) */
	private Long time;
	/** 方法名 */
	private String method;
	/** 参数 */
	private String params;
	/** IP */
	private String ip;
}
