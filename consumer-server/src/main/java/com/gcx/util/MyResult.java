package com.gcx.util;

import java.io.Serializable;
/**
 * 数据返回返回结果
 * @author ywb
 *
 * @param <T>
 */
public class MyResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private String msg;
	private T data;
	private int count;

	//不带参数
	public static MyResult<Object> ok(){
		MyResult<Object> result = new MyResult<Object>();
		result.setStatus(0);
		result.setMsg("success");
		result.setData(null);
		return result;
	}
	//带 返回值
	public static MyResult<Object> ok(Object object){
		MyResult<Object> result = new MyResult<Object>();
		result.setStatus(0);
		result.setMsg("success");
		result.setData(object);
		return result;
	}
	//带 总记录数 和 返回值
	public static MyResult<Object> ok(int count, Object object){
		MyResult<Object> result = new MyResult<Object>();
		result.setStatus(0);
		result.setMsg("success");
		result.setData(object);
		result.setCount(count);
		return result;
	}
	//不带参数
	public static MyResult<Object> error(){
		MyResult<Object> result = new MyResult<Object>();
		result.setStatus(1);
		result.setMsg("fail");
		result.setData(null);
		return result;
	}
	//带参数
	public static MyResult<Object> error(Object object){
		MyResult<Object> result = new MyResult<Object>();
		result.setStatus(1);
		result.setMsg("fail");
		result.setData(object);
		return result;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "MyResult [status=" + status + ", msg=" + msg + ", data=" + data + ", count=" + count + "]";
	}

}
