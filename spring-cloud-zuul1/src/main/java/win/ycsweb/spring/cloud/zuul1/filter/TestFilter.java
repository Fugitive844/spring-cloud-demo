package win.ycsweb.spring.cloud.zuul1.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @ClassName TestFilter
 * @Description TODO
 * @Author yangchongshun
 * @Date 2019/6/12 11:58
 * @Version v1.0
 */
public class TestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; //定义filter的类型，有pre、route、post、error四种
    }

    @Override
    public int filterOrder() {
        return 10; //定义filter的顺序，数字越小表示顺序越高，越先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; //表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul网关自定义过滤器测试");
        return null; //filter需要执行的具体操作
    }
}
