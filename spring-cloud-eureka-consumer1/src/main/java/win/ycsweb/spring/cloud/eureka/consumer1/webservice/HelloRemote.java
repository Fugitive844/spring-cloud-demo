package win.ycsweb.spring.cloud.eureka.consumer1.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import win.ycsweb.spring.cloud.eureka.consumer1.webservice.hystrix.HelloRemoteHystrix;

/**
 * @ClassName HelloRemote
 * @Description TODO
 * @Author yangchongshun
 * @Date 2019/6/5 15:28
 * @Version v1.0
 */
@FeignClient(name= "spring-cloud-producer",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}