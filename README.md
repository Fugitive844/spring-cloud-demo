demo主要参考http://www.ityouknow.com/spring-cloud.html （纯洁的微笑博客）

eureka server1端口=8000
eureka server2端口=8001
eureka provider1端口=9000
eureka provider2端口=9001
eureka consumer1端口=9006
eureka consumer2端口=9008
config端口 = 8010
config client端口= 8016

zuul网关端口=8020


1.粗暴的开放所有节点数据，生产环境根据实际情况配置。这是actuator（性能信息管理监控的组件）配置
management.endpoints.web.exposure.include = *

2.在eureka server中，注册中心可以存在多个，作为集群部署，保证注册中心高可用，注册中心之间会相互注册

3.hystrix熔断器相关。

4.配置中心访问地址 http://server:port/config/application.properties
与之对应的配置文件如下
server.port=8010
spring.application.name=spring-cloud-config
spring.cloud.config.enabled=true
spring.cloud.config.server.svn.uri=http://127.0.0.1/svn/repository/spring/cloud/
spring.cloud.config.server.svn.username=test
spring.cloud.config.server.svn.password=test
spring.cloud.config.server.default-label=config
spring.profiles.active=subversion
logging.level.root=info

5.eureka.client.register-with-eureka ：表示是否将自己注册到Eureka Server，默认为true。
6.eureka.client.fetch-registry ：表示是否从Eureka Server获取注册信息，默认为true。

6.配置中心(客户端)自动更新机制  在类上使用@RefreshScope注解，那么该类自动注入的参数会自动更新
springboot 1.5.X 以上默认开通了安全认证，所以需要在配置文件application.properties添加以下配置
management.security.enabled=false
以post请求的方式来访问http://clientIP:port/refresh 就会更新修改后的配置文件。（访问客户端本身的IP和端口）

7.上面的配置中心server和client都没有用到服务注册与发现，改造第一步在启动类加入注解@EnableDiscoveryClient，
server改造只要在配置文件中加入注册中心地址配置即可。client端去掉spring.cloud.config.uri配置，增加下面的配置：
spring.cloud.config.discovery.enabled ：开启Config服务发现支持
spring.cloud.config.discovery.serviceId ：指定server端的name,也就是server端spring.application.name的值
eureka.client.serviceUrl.defaultZone ：指向注册中心的地址。

8.zuul网关配置
#这里的配置表示，访问/it/** 直接重定向到http://www.ityouknow.com/**
zuul.routes.baidu.path=/it/**
zuul.routes.baidu.url=http://www.ityouknow.com/
启动类添加@EnableZuulProxy，支持网关路由。
史上最简单的zuul案例就配置完了

9.zuul网关服务化
通过url映射的方式来实现zull的转发有局限性，比如每增加一个服务就需要配置一条内容，另外后端的服务如果是动态来提供，就不能采用这种方案来配置了。
实际上在实现微服务架构时，服务名与服务实例地址的关系在eureka server中已经存在了，
所以只需要将Zuul注册到eureka server上去发现其他服务，就可以实现对serviceId的映射。
spring.application.name=gateway-service-zuul
server.port=8888
zuul.routes.api-a.path=/producer/**
zuul.routes.api-a.serviceId=spring-cloud-producer
eureka.client.serviceUrl.defaultZone=http://localhost:8000/eureka/,http://localhost:8001/eurek
这时候可以通过访问127.0.0.1:8888/producer/**来访问spring-cloud-producer的所有服务，假设spring-cloud-producer有服务地址：hello?name=123,
那么可以通过127.0.0.1:8888/producer/hello?name=123来访问
需要以下包支持
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
 </dependency>

10.zuul网关的默认路由规则
但是如果后端服务多达十几个的时候，每一个都这样配置也挺麻烦的，spring cloud zuul已经帮我们做了默认配置。
默认情况下，Zuul会代理所有注册到Eureka Server的微服务，并且Zuul的路由规则如下：http://ZUUL_HOST:ZUUL_PORT/微服务在Eureka上的serviceId/**会被转发到serviceId对应的微服务。
我们注销掉gateway-service-zuul-eureka项目中关于路由的配置：
#zuul.routes.api-a.path=/producer/**
#zuul.routes.api-a.serviceId=spring-cloud-producer

重新启动后，访问http://localhost:8888/spring-cloud-producer/hello?name=123，测试返回结果和上述示例相同，说明Spring cloud zuul默认已经提供了转发功能。
这里直接不用配置路由映射关系，由网关自己完成。

11.zuul网关自定义过滤器，过滤器类需要实现ZuulFilter接口，然后在启动类中用@Bean把过滤器的对象放入spring容器
public class TestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre"; //定义filter的类型，有pre、route、post、error四种
    }
    @Override
    public int filterOrder() {
        return 10; //定义filter的顺序，数字越小表示顺序越高，越先执行
    }
    @Override
    public boolean shouldFilter() {
        return true; //表示是否需要执行该filter，true表示执行，false表示不执行
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul网关自定义过滤器测试");
        return null; //filter需要执行的具体操作
    }

    在启动类中用@Bean把过滤器的对象放入spring容器

     @Bean
        public TestFilter testFilter(){
            return new TestFilter();
        }

