package win.ycsweb.spring.cloud.eureka.provider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaProvider1Application.class, args);
    }

}
