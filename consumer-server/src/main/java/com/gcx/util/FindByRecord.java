package com.gcx.util;
/** 
 * @author	yang 
 * @version 创建时间：2017年1月13日 上午11:45:21 
 * 类说明:	
 */
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class FindByRecord extends PluginAdapter {

	/**
	 * {@inheritDoc}
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
			Interface interfaze, IntrospectedTable introspectedTable) {

			interfaze.addMethod(generateDeleteLogicByIds(method,
					introspectedTable));

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
			Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

			interfaze.addMethod(generateDeleteLogicByIds(method,
					introspectedTable));

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

			topLevelClass.addMethod(generateDeleteLogicByIds(method,
					introspectedTable));
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
			Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

			topLevelClass.addMethod(generateDeleteLogicByIds(method,
					introspectedTable));
		return true;
	}
	
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		
		String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名  
		List<IntrospectedColumn> list =  introspectedTable.getAllColumns();
		
		XmlElement parentElement = document.getRootElement();

		// 产生分页语句前半部分
		XmlElement findByRecordElement = new XmlElement("select");
		findByRecordElement.addAttribute(new Attribute("id", "findByRecord"));
		findByRecordElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		
		String str = "select <include refid='Base_Column_List' /> from " + tableName + " where 1 = 1 ";
		for(int i=0; i<list.size(); i++){
			String aa = list.get(i).getActualColumnName();
			String bb = list.get(i).getJavaProperty();
			String cc = list.get(i).getJdbcTypeName();
			str += "<if test='record."+ bb +" != null and record."+ bb +" != \"\" '> and "+ aa +" = #{record."+ bb +",jdbcType="+ cc +"} </if>";
		}
		// str += " limit #{start}, #{end}";
		
		findByRecordElement.addElement(new TextElement(str));
		
		parentElement.addElement(findByRecordElement);

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	private Method generateDeleteLogicByIds(Method method, IntrospectedTable introspectedTable) {

		System.out.println("findByRecord");

		String modelName = introspectedTable.getBaseRecordType();//实体类名
		
		Method m = new Method();
		m.setName("findByRecord");

		m.setVisibility(method.getVisibility());
		
		m.setReturnType(new FullyQualifiedJavaType("List<" + modelName + ">"));
		m.addParameter(new Parameter(new FullyQualifiedJavaType(modelName), "record", "@Param(\"record\")"));
		m.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "start", "@Param(\"start\")"));
		m.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "end", "@Param(\"end\")"));

		context.getCommentGenerator().addGeneralMethodComment(m,introspectedTable);
		
		//调用生成controller，service，serviceImpl方法
//		try {
//			FreeMarkerForJava.createCode(modelName);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return m;
	}

}
