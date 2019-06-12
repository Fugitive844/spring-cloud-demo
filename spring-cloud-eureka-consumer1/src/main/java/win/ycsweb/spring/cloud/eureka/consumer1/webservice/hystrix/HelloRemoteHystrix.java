package win.ycsweb.spring.cloud.eureka.consumer1.webservice.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import win.ycsweb.spring.cloud.eureka.consumer1.webservice.HelloRemote;

/**
 * @ClassName HelloRemoteHystrix
 * @Description 熔断器
 * @Author yangchongshun
 * @Date 2019/6/5 17:07
 * @Version v1.0
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello" +name+", this messge send failed ";
    }
}
