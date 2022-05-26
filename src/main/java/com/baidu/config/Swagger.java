package com.baidu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger {
    @Bean
    public Docket docket(){
        // 创建ApiInfo
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("标题")
                .description("文档的描述").version("版本号")
                .contact(new Contact("作者名","首页","邮箱地址"))
                .build();


        // 创建Docket
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.baidu.controller"))
                .paths(regex("/service/.*"))
                .build()
                .apiInfo(apiInfo);

        // 返回docket对象
        return docket;
    }
}
