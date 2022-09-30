package org.example.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @desc：
 * @author: huangxin
 * @date: 2022.08.24
 */
@SpringBootApplication
@Slf4j
public class IMServer002Application {
    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(IMServer002Application.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
        log.info(" ==============  IMServer002Application ApiApplication start ============== ");
    }
}
