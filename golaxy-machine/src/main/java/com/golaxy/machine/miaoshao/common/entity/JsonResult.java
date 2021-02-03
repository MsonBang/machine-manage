package com.golaxy.machine.miaoshao.common.entity;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description= "返回响应数据")
public class JsonResult<T> {

	public static final int SUCCESS = 1;
	public static final int FAIL = 0;

	@ApiModelProperty(value = "是否成功 0失败 1成功")
	private int result;

	@ApiModelProperty(value = "返回信息")
	private String msg;

	@ApiModelProperty(value = "返回对象")
	private T info;

	public JsonResult() {
	}

	public JsonResult(int result, String msg) {
		this.result = result;
		this.msg = msg;
	}

	public JsonResult(int result, int resCode, String msg, T info) {
		this.result = result;
		this.msg = msg;
		this.info = info;
	}

	public JsonResult(int result, String msg, T info, long total) {
		this.result = result;
		this.msg = msg;
		this.info = info;
	}

	public JsonResult(int result, String msg, T info) {
		this.result = result;
		this.msg = msg;
		this.info = info;
	}

	public JsonResult(int result, T info) {
		this.result = result;
		this.info = info;
	}

	public static JsonResult success(String msg) {
		final JsonResult result = new JsonResult(SUCCESS, msg);
		return result;
	}

	public static JsonResult fail(String msg) {
		final JsonResult result = new JsonResult(FAIL, msg);
		return result;
	}

	public JsonResult put(T t) {
		info = t;
		return this;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public String toJsonString() {
		return JSON.toJSONString(this);
	}
}
