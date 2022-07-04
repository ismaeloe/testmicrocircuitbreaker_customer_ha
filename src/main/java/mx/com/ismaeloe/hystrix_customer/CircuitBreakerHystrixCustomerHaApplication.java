package mx.com.ismaeloe.hystrix_customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
//ok @ComponentScan(basePackageClasses=TestController.class)
//ok @ComponentScan(basePackages = "mx.com.ismaeloe")
@ComponentScan("mx.com.ismaeloe")
public class CircuitBreakerHystrixCustomerHaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerHystrixCustomerHaApplication.class, args);
	}

}
