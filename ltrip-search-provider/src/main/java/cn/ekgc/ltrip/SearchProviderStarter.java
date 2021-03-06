package cn.ekgc.ltrip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("cn.ekgc.ltrip.dao")
@SpringBootApplication
public class SearchProviderStarter {
	public static void main(String[] args) {
		SpringApplication.run(SearchProviderStarter.class, args);
	}
}
