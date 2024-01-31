package gr.vasilakos.watermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WaterMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterMicroserviceApplication.class, args);
    }

}
