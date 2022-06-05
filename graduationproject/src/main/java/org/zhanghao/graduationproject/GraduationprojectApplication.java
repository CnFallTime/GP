package org.zhanghao.graduationproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.zhanghao.graduationproject.mapper")
@SpringBootApplication
public class GraduationprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationprojectApplication.class, args);
    }

}
