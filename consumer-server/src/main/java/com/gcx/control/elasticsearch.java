package com.gcx.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.gcx.model.Gcxuser;

import com.gcx.util.MyResult;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class elasticsearch {


    @Autowired
    RestHighLevelClient client;



    //创建索引库
    @RequestMapping("/testCreateIndex")

    public MyResult<Object> testCreateIndex() throws IOException {
        //创建索引对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("elasticsearch_test");
        //设置参数
        createIndexRequest.settings(Settings.builder().put("number_of_shards","1").put("number_of_replicas","0"));
        //指定映射
        createIndexRequest.mapping("doc"," {\n" +
                " \t\"properties\": {\n" +
                "            \"studymodel\":{\n" +
                "             \"type\":\"keyword\"\n" +
                "           },\n" +
                "            \"name\":{\n" +
                "             \"type\":\"keyword\"\n" +
                "           },\n" +
                "           \"description\": {\n" +
                "              \"type\": \"text\",\n" +
                "              \"analyzer\":\"ik_max_word\",\n" +
                "              \"search_analyzer\":\"ik_smart\"\n" +
                "           },\n" +
                "           \"pic\":{\n" +
                "             \"type\":\"text\",\n" +
                "             \"index\":false\n" +
                "           }\n" +
                " \t}\n" +
                "}", XContentType.JSON);
        //操作索引的客户端
       // IndicesClient indices = client.indices();
        //执行创建索引库
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        //得到响应
        boolean acknowledged = createIndexResponse.isAcknowledged();

        return MyResult.ok(acknowledged);

    }

    //删除索引库
    @RequestMapping("/testDeleteIndex")
    public MyResult<Object> testDeleteIndex() throws IOException {
        //删除索引对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("elasticsearch_test");
        //操作索引的客户端
        IndicesClient indices = client.indices();
        //执行删除索引
        AcknowledgedResponse delete = indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        //得到响应
        boolean acknowledged = delete.isAcknowledged();
        System.out.println(acknowledged);
        return MyResult.ok(acknowledged);
    }

    //添加文档
    @RequestMapping("/testAddDoc")
    public MyResult<Object> testAddDoc() throws IOException {
        //文档内容
        //准备json数据
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "spring cloud实战");
        jsonMap.put("description", "本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud 基础入门 3.实战Spring Boot 4.注册中心eureka。");
        jsonMap.put("studymodel", "201001");
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMap.put("timestamp", dateFormat.format(new Date()));
        jsonMap.put("price", 5.6f);

        //创建索引创建对象
        IndexRequest indexRequest = new IndexRequest("elasticsearch_test","doc");
        //文档内容
        indexRequest.source(jsonMap);
        //通过client进行http的请求
        IndexResponse indexResponse = client.index(indexRequest,RequestOptions.DEFAULT);
        DocWriteResponse.Result result = indexResponse.getResult();
        System.out.println(result);
        return MyResult.ok(result);
    }

    //查询文档
    @RequestMapping("/testGetDoc")
    public MyResult<Object> testGetDoc() throws IOException {
        //查询请求对象
        GetRequest getRequest = new GetRequest("elasticsearch_test","_doc","RNcZo3MBWhCtjnJutpnP");
        GetResponse getResponse = client.get(getRequest,RequestOptions.DEFAULT);
        //得到文档的内容
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);
        return MyResult.ok(sourceAsMap);
    }
//===================================================================================================================================
//创建索引

void createIndex() throws IOException {
    CreateIndexRequest request = new CreateIndexRequest("index1");
    CreateIndexResponse createIndexResponse =
            client.indices().create(request, RequestOptions.DEFAULT);
}

    //删除文档

    void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("index1", "2");
        request.timeout("1s");
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(deleteResponse.status());
    }

    //判断文档是否存在

    void isExistDocument() throws IOException {
        GetRequest request = new GetRequest("index1", "1");
        //不获取_source内容,提升效率
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");

        boolean exists = client.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    //添加文档
    void addDocument() throws IOException {
      /*  User user = new User("dai", 22);
        IndexRequest request = new IndexRequest("index1")
                .id("1")
                .timeout(TimeValue.timeValueSeconds(1));

        IndexRequest source = request.source(JSON.toJSONString(user), XContentType.JSON);

        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());*/
    }

    //批量插入数据

    void BulkRequest() throws IOException {

        BulkRequest bulkRequest = new BulkRequest()
                .timeout("5s");
      /*  List<User> users = Arrays.asList(new User("dai1", 1), new User("dai2", 2), new User("dai3", 3));

        for (User user : users) {
            bulkRequest.add(new IndexRequest("index1")
                    //.id("xxx")
                    .source(JSON.toJSONString(user), XContentType.JSON));
        }

        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        //是否失败,false表示成功
        System.out.println(bulkResponse.hasFailures());
        System.out.println(bulkResponse.status());*/
    }

    //更新文档

    void updateDocument() throws IOException {
        UpdateRequest request = new UpdateRequest("index1", "1");
        request.timeout("1s");

       /* User user = new User("dai2", 22);
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);

        System.out.println(updateResponse.status());*/
    }

    //查询

    void search() throws IOException {
        SearchRequest request = new SearchRequest("index1");

        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // SearchRequest 搜索请求
        // SearchSourceBuilder 条件构造
        // HighlightBuilder 构建高亮
        // TermQueryBuilder 精确查询
        // MatchAllQueryBuilder .....
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(matchAllQueryBuilder)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(sourceBuilder);

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits(), true));
        System.out.println("===================================");
        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }

    }

}

