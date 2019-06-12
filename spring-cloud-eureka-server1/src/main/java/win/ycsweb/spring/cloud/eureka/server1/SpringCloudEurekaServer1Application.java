package win.ycsweb.spring.cloud.eureka.server1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaServer1Application.class, args);
    }

}
