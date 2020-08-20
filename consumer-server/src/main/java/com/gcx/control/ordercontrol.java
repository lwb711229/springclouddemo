package com.gcx.control;

import com.github.pagehelper.PageHelper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;


import com.gcx.dao.GcxuserMapper;
import com.gcx.model.Gcxuser;

import com.gcx.support.DataCache;
import com.gcx.support.UserDate;
import com.gcx.support.Useryzm;
import com.gcx.support.redis.RedisFacade;

import com.gcx.util.*;
//import net.sf.json.JSONObject;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Map;


@RestController
//@RequestMapping(value = "/sendYzm")
public class ordercontrol {


    @Value("${gcxId}")
    String gcxId;


    @Autowired
     com.gcx.remote.HelloRemote HelloRemote;
    //这里注入的restTemplate就是在com.sam.ConsumerApp中通过@Bean配置的实例
    // @Resource(name="restTemplate")
    @Autowired
    private RestTemplate restTemplate;
    // AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(ConsumerServerApplication.class);
    //RestTemplate restTemplate = context.getBean(RestTemplate.class);
    @Autowired
    private  EurekaClient disenc;

    private org.slf4j.Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    GcxuserMapper gcxuserMapper;

    @Autowired
    private DataCache dataCache;

    @Autowired
    private UserDate userDate;

    @Autowired
    RedisFacade redisFacade;

    @Autowired
    Useryzm useryzm;

                         //需要应用开启@EnableHystrix

    @GetMapping("/test")
    //@HystrixCommand(fallbackMethod = "helloError")
    public  String test(@PathVariable long id, HttpServletRequest request, HttpServletResponse response){

        InstanceInfo  instance = disenc.getNextServerFromEureka("PRODUCER-SERVER",false);
        return instance.getHomePageUrl();

    }

//http://localhost:8900/order/12
    @GetMapping("/order/{id}")
    //@HystrixCommand(fallbackMethod = "helloError")
    public  String order(@PathVariable long id, HttpServletRequest request, HttpServletResponse response){

        Gcxuser gcxuser = new Gcxuser();
        gcxuser.setUserId(new BigDecimal("1111"));
        gcxuser.setUserName("张三");
        gcxuser.setPassword("123");

        //  int i = gcxuserMapper.insertSelective(gcxuser);

        PageHelper.startPage(2, 5);
        // List<MONITORUSER>  list =  mONITORUSERMapper.select(new MONITORUSER());
        // List<Map<String, Object>>  list =  mONITORUSERMapper.findUserByDepartId();
        Gcxuser gcxuser1 = new Gcxuser();
        gcxuser1.setUserId(new BigDecimal("1111"));
        Gcxuser  list =  gcxuserMapper.selectByPrimaryKey(gcxuser1);

        logger.info(" i am info=");

        InstanceInfo  instance = disenc.getNextServerFromEureka("PRODUCER-SERVER",false);
        String surl = "";
        surl  = instance.getHomePageUrl()+"user/"+id; //surl+"user/"+id
        //   surl  = restTemplate.getForObject(surl,String.class);
        System.out.println(surl);
        System.out.println(IPUtil.getIpAdd(request));

    /*    list.forEach((k)->{
            System.out.println("Item : " + k.getUserId());
          dataCache.setValue("key1",k);
            redisFacade.setValue("user:"+"a",k,1);
            redisFacade.setValue("user:"+"b","zhangsain",1);
           // System.out.println("Item : " + k.get("USER_ID"));
        });
        System.out.println(useryzm.getString(msgCheckCode));
        MONITORUSER mmm = (MONITORUSER) dataCache.getValue("key1");
        System.out.println(mmm.getUserId());
    */

        redisFacade.setValue("user:"+"a",list,20);
        redisFacade.setValue("user:"+"b","zhangsain",20);

        redisFacade.setexpire("user:"+"b",3600);
        Gcxuser vv=  (Gcxuser) redisFacade.getValue("user:a",list);
        System.out.println(vv.getPassword());
        System.out.println(redisFacade.getValue("user:b"));
        System.out.println(redisFacade.getValue("user:a"));
        //  ElasticsearchUtils.createIndex("school");
/*

        JSONObject student1 = new JSONObject();
        student1.put("name","杨旭你好");
        student1.put("address","山东烟台");
        student1.put("age",22);
        student1.put("date","1995-04-12");
        JSONObject student2 = new JSONObject();
        student2.put("name","张小花1");
        student2.put("address","山东烟台你好");
        student2.put("age",24);
        student2.put("date","1996-07-24");
         //1、使用JSONObject
        JSONObject json = JSONObject.fromObject(stu);
        //2、使用JSONArray
        JSONArray array=JSONArray.fromObject(stu);
*/

/*
        ElasticsearchUtils.addData(student1,"school","student","1111");
        ElasticsearchUtils.addData(student2,"school","student","1112");
        student2.put("date","1996-07-25");
        ElasticsearchUtils.updateDataById(student2,"school","student","1112");
*/
        //"  GCX_USER WHERE USER_NAME='"+TellNumber+"'"
        String where = "  GCX_USER WHERE USER_NAME='"+"张三"+"'";
        Map<String, Object> mapuser  = mapUser(where) ;
        if (mapuser==null){
            System.out.println("空值===");
        }else
        {
            System.out.println(mapuser.get("USER_ID"));
        }
/*
        if( !ElasticsearchUtils.isIndexExist("school")){
            ElasticsearchUtils.createIndex("school");
        }
        JSONObject jsonobject = JSONObject.parseObject(JSONObject.toJSONString(gcxuser, SerializerFeature.WriteMapNullValue));
        ElasticsearchUtils.addData(jsonobject,"school","student",UUIDUtils.createUuid());
        EsPage esPage = ElasticsearchUtils.searchDataPage("school","student",0,5,"1995-04-13","1996-07-24","name1,address,age","name1",false,"name1","name1=中华 人民共和国" );
        esPage.getRecordList().forEach((k)->{
            System.out.println( k.toString());
        });
        */
        return  restTemplate.getForObject(surl,String.class) ;


/*
       // return  rst1.getForObject(surl+"/user/"+id,String.class);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        return result.toString();
 */

    }

    public String helloError(@PathVariable long id){

        return "服务器出现异常...异常参数："+id;
    }


    //需要应用开启@EnableFeignClients
   //http://localhost:8900/hello/zhang
       @RequestMapping("/hello/{name}")
       public String index(@PathVariable("name") String name) {
          return HelloRemote.hello(name);

       }

/*
    // 获取验证码
    @RequestMapping("/userLogin")
    public MONITORUSER userLogin(HttpServletRequest request, HttpServletResponse response) {

        //用户唯一身份标示
        String authId = "";
        //判断是否已生成 cookie唯一标示
        authId = CookieUtils.getCookieValue(request, gcxId);
        //如果没有 生成 并存入cookie
        if("".equals(authId) || authId==null){
            authId = UUID.randomUUID().toString();
            CookieUtils.setCookie(request, response, gcxId, authId);
            return null;
        }
        MONITORUSER mmm = (MONITORUSER) userDate.getValue(authId);
        return mmm ;
    }
    */
    // 获取验证码
    @RequestMapping("/getPicCheckCode")
    public void getPicCheckCode(HttpServletRequest request, HttpServletResponse response) {

        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/gif");

        // 生成的图片
        CheckCodeImageUtil imgUtil = new CheckCodeImageUtil();
        BufferedImage image = imgUtil.createImage();
        // request.getSession().setAttribute("SESSION_CHECKCODE", imgUtil.getRandString());
        System.err.println(imgUtil.getRandString());

        OutputStream out = null;

        try {
            // Servlet输出流
            out = response.getOutputStream();
            // 将图片写入到输出流中去
            ImageIO.write(image, "JPG", out);
            // 强制刷新
            out.flush();
            // 关闭输出流
            out.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public Map<String, Object>  mapUser(String where){

        /*	if("1".equals(Type) ){ where1 = "  GCX_USER_CORP_PERFECT WHERE CORP_ID='"+TellNumber+"'";}
		if("0".equals(Type) ){ where1 = "  GCX_USER_EMPLOYEE_PERFECT WHERE EMP_ID='"+TellNumber+"'";}
		if("8".equals(Type) ){ where1 = "  GCX_USER_GOV_PERFECT WHERE Z6='"+TellNumber+"'";}*/

        //   "  GCX_USER WHERE USER_NAME='"+TellNumber+"'"
        //
        //

        return  gcxuserMapper.mapUser(where) ;
    }

}
