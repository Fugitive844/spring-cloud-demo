package win.ycsweb.spring.cloud.zuul1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import win.ycsweb.spring.cloud.zuul1.filter.TestFilter;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudZuul1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuul1Application.class, args);
    }

    /**
     * 加入过滤器
     * @return
     */
    @Bean
    public TestFilter testFilter(){
        return new TestFilter();
    }

}
