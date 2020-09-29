import com.gcx.mygenerate.Column;
import com.gcx.mygenerate.Table;
import com.gcx.mygenerate.Underline2Camel;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * @author	yang 
 * @version 创建时间：2017年8月4日 上午10:18:13 
 * 类说明:	
 */
public class YangGenerate11 {
	//数据库名（同项目名和包名）
	private static String projectName = "usermanage";
	//表名
	private static String fileEnd = "JedisTest";
	//dao、model、mapper开关
	private static boolean flag1 = true;
	//controller、service、serviceImpl开关
	private static boolean flag2 = true;
	//工作空间地址
	private static String workeSpace = "D:\\workspace\\";


	public static void main(String[] args) throws Exception {

		//生成dao、model、mapper

			freeMarker(  );


	}

	//FreeMarker代码生成    table:表数据结构  	ftl:模板文件名 	  packageName:包名	fileEnd:文件后缀
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void freeMarker(  ) throws Exception {
		// 第一步：把freemarker的jar包添加到工程中
		// 第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		// 第三步：创建一个Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第四步：告诉config对象模板文件存放的路径。
		// TODO
		//String fileURL = workeSpace + projectName + "api\\src\\main\\java\\com\\gcx\\" + projectName;

		String fileURL = "F:\\gcx\\springclouddemo\\consumer-server\\src\\test\\ftl";
		configuration.setDirectoryForTemplateLoading(new File(fileURL ));
		// 第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		// 第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate("Controller11.ftl");
		// 第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的数据都放入数据集。
		Map root = new HashMap<>();

		root.put("projectName", projectName);
		root.put("time", getTime());
		root.put("fileEnd", fileEnd);

		// 第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File(fileURL + "\\" +"" + fileEnd+".java"));
		// 第九步：调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		// 第十步：关闭writer对象。
		out.flush();
		out.close();
	}


	//首字母转大写
	public static String upperCase(String str) {  
		char[] ch = str.toCharArray();  
		if (ch[0] >= 'a' && ch[0] <= 'z') {  
			ch[0] = (char) (ch[0] - 32);  
		}  
		return new String(ch);  
	}  
	//获取当前时间
	private static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	//根据java类型获取类型名称简称
	private static String getSimple(String str){
		String[] strs = str.split("\\.");
		return strs[strs.length-1];
	}
	//字段类型转换 "INT"转"INTEGER"
	private static String IntToInteger(String str){
		if("INT".equals(str))
			return "INTEGER";
		if("DATETIME".equals(str))
			return "TIMESTAMP";

		return str;
	}
	//对象属性类型转换 
	private static String IntToInteger2(String str){
		if("Timestamp".equals(str))
			return "Date";
		
		return str;
	}

}
