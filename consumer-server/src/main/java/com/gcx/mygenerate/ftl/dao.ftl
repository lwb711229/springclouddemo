package com.gcx.${projectName}.dao;

import com.gcx.${projectName}.model.${table.javaName};

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ${table.javaName}Mapper {

	int insert(${table.javaName} record);

	int insertSelective(${table.javaName} record);

	int updateByPrimaryKey(${table.javaName} record);

	int updateByPrimaryKeySelective(${table.javaName} record);

	int deleteByPrimaryKey(Long id);

	int deleteLogicById(@Param("deleteFlag") int deleteFlag, @Param("id") ${table.column.javaTypeSimple} id);

	int deleteLogicByIds(@Param("deleteFlag") int deleteFlag, @Param("ids") List<${table.column.javaTypeSimple}> ids);

	${table.javaName} selectByPrimaryKey(${table.column.javaTypeSimple} id);

	List<${table.javaName}> findByRecord(@Param("record") ${table.javaName} record, @Param("start") int start, @Param("end") int end);

	int findByRecordCount(@Param("record") ${table.javaName} record);



}