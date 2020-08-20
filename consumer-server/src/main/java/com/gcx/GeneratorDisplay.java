package com.gcx;


import com.alibaba.fastjson.JSON;
import com.gcx.model.Gcxuser;
import com.gcx.support.redis.RedisObjectSerializer;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GeneratorDisplay {
    RedisObjectSerializer RedisSerializer1 = new RedisObjectSerializer();

    public void generator() throws Exception {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定逆向工程配置文件 E:\springclouddemo\mybitas_ger
        File configFile = new File("consumer-server/src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }
    public static void main(String[] args) throws Exception {



        try {


            GeneratorDisplay generatorSqlmap = new GeneratorDisplay();
           generatorSqlmap.generator();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}

