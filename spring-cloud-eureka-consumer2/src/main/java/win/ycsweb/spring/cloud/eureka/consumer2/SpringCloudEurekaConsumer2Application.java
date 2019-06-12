package win.ycsweb.spring.cloud.eureka.consumer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringCloudEurekaConsumer2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaConsumer2Application.class, args);
    }

}
