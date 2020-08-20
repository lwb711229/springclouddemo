package com.gcx.dao;

import com.gcx.model.Gcxuser;
import com.gcx.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GcxuserMapper extends MyMapper<Gcxuser> {

    @Select("SELECT * FROM ${where1} ")
    Map<String, Object> mapUser(@Param("where1") String where1);

    List<Gcxuser> findByRecord(@Param("record") Gcxuser record);

}