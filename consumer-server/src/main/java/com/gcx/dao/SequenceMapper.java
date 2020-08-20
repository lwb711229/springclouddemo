package com.gcx.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper {

	@Select("SELECT NEXTVAL('Seq')")
	BigDecimal getNextval();
	@Select("SELECT NEXTVAL('Seq')")
	BigDecimal getContractSeq();
	@Select("SELECT NEXTVAL('Other')")
	BigDecimal getMyNextval();//八位自增
	
	@Select("SELECT DATE_FORMAT(NOW(),'%Y%m%d%h%i%s')")
	String getCurrentTime();

	
	//  SELECT gcx_bh_child('GCX-01-170516-003')
	//   SELECT gcx_bh('GCX-01')
	
	@Select("SELECT gcx_bh(#{level})")
	String gcx_bh(@Param("level") String level);
	
	@Select("SELECT gcx_bh_child(#{level})")
	String gcx_bh_child(@Param("level") String level);
	
	
	// 生成用户账号
		@Select("SELECT NEXTVAL('account')")
		String getAccount(@Param("account") String account);
	
}
