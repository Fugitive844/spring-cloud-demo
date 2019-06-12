package win.ycsweb.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author yangchongshun
 * @Date 2019/6/11 15:27
 * @Version v1.0
 */
@RestController
class HelloController {
    @Value("${sso.token.max.inactive.interval}")
    private String hello;

    @RequestMapping("/hello")
    public String from() {
        return this.hello;
    }
}
