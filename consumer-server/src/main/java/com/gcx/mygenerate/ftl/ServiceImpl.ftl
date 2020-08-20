package com.gcx.${projectName}.service.impl;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcx.${projectName}.service.util.MyResult;
import com.gcx.${projectName}.model.User;
import com.gcx.${projectName}.dao.${table.javaName}Mapper;
import com.gcx.${projectName}.model.${table.javaName};
import com.gcx.${projectName}.service.${table.javaName}Service;

/** 
 * @author	zhangxinin 
 * @version 创建时间：${time} 
 * 类说明:	
 */
@Service
public class ${table.javaName}ServiceImpl extends CommonImpl implements ${table.javaName}Service {

	@Autowired
	${table.javaName}Mapper ${table.javaName2}Dao;

	@Override
	public MyResult<Object> findRecords(HttpServletRequest request, HttpServletResponse response, 
			${table.javaName} record, int pagenow, int pagesize) {
		int limitStart = (pagenow-1)*pagesize;
		int limitEnd = pagesize;

		try {
			//用户ID
			User user = getUser(request, response);
			if (user==null)
				return MyResult.error("查询失败,请登录！");	
			record.setUserGroup(user.getUserGroup());
			
			List<${table.javaName}> list = ${table.javaName2}Dao.findByRecord(record, limitStart, limitEnd);
			int count = ${table.javaName2}Dao.findByRecordCount(record);

			return MyResult.ok(count, list);
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("查询失败");	
		}
	}

	@Override
	public MyResult<Object> addRecord(HttpServletRequest request, HttpServletResponse response, 
			${table.javaName} record) {
		try {
			//用户ID， 当前时间
			User user = getUser(request, response);
			if (user==null)
				return MyResult.error("增加失败,请登录！");	
			record.setUserIdAdd(user.getUserId().toString());
			record.setUserGroup(user.getUserGroup());
				
			record.set${table.column.javaName2}(getUUID());
			record.setUserIdAdd(user.getUserId().toString());
			record.setTimeAdd(new Date());

			${table.javaName2}Dao.insertSelective(record);
			return MyResult.ok("新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("新增失败");	
		}
	}

	@Override
	public MyResult<Object> delRecord(HttpServletRequest request, HttpServletResponse response, 
			${table.javaName} record) {
		try {
			//用户ID， 当前时间
			User user = getUser(request, response);
			if (user==null)
				return MyResult.error("删除失败,请登录！");	
			record.setTimeDel(new Date());
			record.setUserIdDel(user.getUserId()+"");
			${table.javaName2}Dao.deleteLogicById(1, record.get${table.column.javaName2}());//1表示逻辑删除
			return MyResult.ok("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("删除失败");	
		}
	}

	@Override
	public MyResult<Object> updateRecord(HttpServletRequest request, HttpServletResponse response, 
			${table.javaName} record) {
		try {
			record.setUserGroup("");
			if(record.get${table.column.javaName2}() == null)
				return MyResult.error("你又忘记给我ID了...");
			${table.javaName2}Dao.updateByPrimaryKeySelective(record);
			return MyResult.ok("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("更新失败");	
		}
	}


	@Override
	public MyResult<Object> findById(HttpServletRequest request, HttpServletResponse response, 
			String ${table.column.javaName}) {
		try {
			${table.javaName} record = ${table.javaName2}Dao.selectByPrimaryKey(${table.column.javaName});
			return MyResult.ok(record);
		} catch (Exception e) {
			e.printStackTrace();
			return MyResult.error("查询失败");	
		}
	}

}
