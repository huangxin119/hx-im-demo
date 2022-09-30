package org.example.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.09.01
 */
@SpringBootApplication
@Slf4j
public class BusinessServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(BusinessServerApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
        log.info(" ==============  BusinessServerApplication Application start ============== ");
    }
}
