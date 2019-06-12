package win.ycsweb.spring.cloud.eureka.consumer1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.ycsweb.spring.cloud.eureka.consumer1.webservice.HelloRemote;

/**
 * @ClassName ConsumerController
 * @Description TODO
 * @Author yangchongshun
 * @Date 2019/6/5 15:29
 * @Version v1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    win.ycsweb.spring.cloud.eureka.consumer1.webservice.HelloRemote HelloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return HelloRemote.hello(name);
    }

}
