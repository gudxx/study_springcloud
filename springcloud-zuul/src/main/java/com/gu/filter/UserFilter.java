package com.gu.filter;

import com.ctc.wstx.util.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class UserFilter extends ZuulFilter {

    /**
     * 过滤器类型： pre route post error
     * @return   pre 登录验证，在路由之前拦截
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 拦截的级别， 返回值越小，级别越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 是否执行run方法
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写过滤器的业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取zuul网关上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        //获取request
        HttpServletRequest request = context.getRequest();
        //模拟用户登录，root为地址传递的值
        String root = request.getParameter("root");

        //判断root是否为null
        if(StringUtils.isBlank(root)){
            //拦截pre     设置false为拦截,不转发请求
            context.setSendZuulResponse(false);
            //设置状态码   401权限不足
            context.setResponseStatusCode(401);
            context.setResponseBody("request error!");
        }
        //返回值null，代表该过滤器什么都不做
        return null;
    }
}
