package org.example.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.23
 */
@SpringBootApplication
@Slf4j
public class IMClient002Application {
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(IMClient002Application.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
        log.info(" ==============  IMClient002Application Application start ============== ");
    }
}
