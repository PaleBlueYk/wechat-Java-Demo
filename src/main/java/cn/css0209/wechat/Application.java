package cn.css0209.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableWebFlux
@MapperScan("cn.css0209.wechat.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
