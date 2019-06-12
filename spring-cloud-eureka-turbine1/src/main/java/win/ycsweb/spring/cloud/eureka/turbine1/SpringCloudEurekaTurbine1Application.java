package win.ycsweb.spring.cloud.eureka.turbine1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
public class SpringCloudEurekaTurbine1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaTurbine1Application.class, args);
    }

}
