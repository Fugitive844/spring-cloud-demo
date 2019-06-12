package win.ycsweb.spring.cloud.eureka.provider2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudEurekaProvider2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaProvider2Application.class, args);
    }

}
