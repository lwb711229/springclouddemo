package com.gcx.${projectName}.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcx.${projectName}.service.util.MyResult;
import com.gcx.${projectName}.model.${table.javaName};
import com.gcx.${projectName}.service.${table.javaName}Service;

/** 
 * @author zhengxiaorong
 * @version 创建时间：${time} 
 */
@Controller
@RequestMapping("${table.javaName2}Controller")
public class ${table.javaName}Controller {

	@Autowired
	${table.javaName}Service ${table.javaName2}Service;

	//查询
	@RequestMapping("findRecords")
	@ResponseBody
	public MyResult<Object> findRecords(HttpServletRequest request,HttpServletResponse response, ${table.javaName} record,
			@RequestParam(value="pagenow", defaultValue="1") int pagenow, 
			@RequestParam(value="pagesize", defaultValue="10") int pagesize){
		MyResult<Object> result = ${table.javaName2}Service.findRecords(request,response, record, pagenow, pagesize);
		return result;
	}
	//新增
	@RequestMapping("addRecord")
	@ResponseBody
	public MyResult<Object> addRecord(HttpServletRequest request,HttpServletResponse response, 
			${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.addRecord(request, response, record);
		return result;
	}
	//删除
	@RequestMapping("delRecord")
	@ResponseBody
	public MyResult<Object> delRecord(HttpServletRequest request,HttpServletResponse response, 
			${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.delRecord(request, response, record);
		return result;
	}
	//修改
	@RequestMapping("updateRecord")
	@ResponseBody
	public MyResult<Object> updateRecord(HttpServletRequest request,HttpServletResponse response, 
			${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.updateRecord(request, response, record);
		return result;
	}


	//审核查询
	@RequestMapping("auditfind")
	@ResponseBody
	public MyResult<Object> auditfind(HttpServletRequest request,HttpServletResponse response, ${table.javaName} record,
			@RequestParam(value="pagenow", defaultValue="1") int pagenow, 
			@RequestParam(value="pagesize", defaultValue="10") int pagesize){
		MyResult<Object> result = ${table.javaName2}Service.auditfind(request,response, record, pagenow, pagesize);
		return result;
	}
	//审核
	@RequestMapping("auditRecord")
	@ResponseBody
	public MyResult<Object> auditRecord(HttpServletRequest request,HttpServletResponse response, 
			${table.javaName} record){
		MyResult<Object> result = ${table.javaName2}Service.auditRecord(request, response, record);
		return result;
	}
	//根据ID查询
	@RequestMapping("findById")
	@ResponseBody
	public MyResult<Object> findById(HttpServletRequest request,HttpServletResponse response, 
			String ${table.column.javaName}){
		MyResult<Object> result = ${table.javaName2}Service.findById(request, response, ${table.column.javaName});
		return result;
	}
	//前台展示
	@RequestMapping("showList")
	@ResponseBody
	public MyResult<Object> showList(HttpServletRequest request,HttpServletResponse response, ${table.javaName} record,
			@RequestParam(value="pagenow", defaultValue="1") int pagenow, 
			@RequestParam(value="pagesize", defaultValue="10") int pagesize){
		MyResult<Object> result = ${table.javaName2}Service.showList(request,response, record, pagenow, pagesize);
		return result;
	}


}
