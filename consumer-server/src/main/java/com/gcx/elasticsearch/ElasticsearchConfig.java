package com.gcx.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

/**
 * 描述: elasticsearch 配置
 *
 * @author yanpenglei
 * @create 2017-11-02 16:41
 **/
@Configuration

public class ElasticsearchConfig {
    @Bean
    public RestHighLevelClient client(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("localhost",9200,"http")
        ));
        return client;
    }
}
