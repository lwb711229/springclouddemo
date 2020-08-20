package com.gcx.config;


import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class PreFilter extends ZuulFilter {
    //private static final RateLimiter RATE_LIMITER = RateLimiter.QUOTA_SUFFIX

    @Override
    public String filterType() {
        //前置过滤器
        /*
        pre：可以在请求被路由之前调用
        route：在路由请求时候被调用
        post：在route和error过滤器之后被调用
        error：处理请求时发生错误时被调用
*/
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }
    @Override
    public boolean shouldFilter() {


        //return "yes".equals(request.getParameter("shouldFilter"));
        //是否执行该过滤器，true代表需要过滤
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response =ctx.getResponse();
        System.out.println("==================shouldFilter=============="+request.getRequestURI());
        String order = request.getParameter("order");

        //获取传来的参数accessToken
     //   Object accessToken = request.getParameter("accessToken");
     /*
        if(order == null) {

            //过滤该请求，不往下级服务去转发请求，到此结束
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"result\":\"accessToken为空!\"}");
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            return null;
        }
        */
        String authId = "uuuuu";
        CookieUtils.setCookie(request, response, "GCXID", authId);
       // ctx.setSendZuulResponse(true); //令zuul过滤该请求，不对其进行路由
       // ctx.setResponseStatusCode(401); //设置了其返回的错误码
      //  ctx.setResponseBody("{\"result\":\"accessToken为空!\"}");
        //这里return的值没有意义，zuul框架没有使用该返回值

      /*  if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimitException(ExceptionEnum.RATERLIMIT_NO.getCode()
                    , ExceptionEnum.RATERLIMIT_NO.getMessage());
        }*/

        return null;

    }



}
