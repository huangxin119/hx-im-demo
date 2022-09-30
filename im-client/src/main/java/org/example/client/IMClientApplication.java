package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @desc：
 * @author: huangxin
 * @date: 2022.05.24
 */
@SpringBootApplication
@Slf4j
public class IMClientApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(IMClientApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
        log.info(" ==============  IMClientApplication Application start ============== ");
    }
}
