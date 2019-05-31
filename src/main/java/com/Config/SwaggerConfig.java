package com.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Autowired
  private Environment environment;
  @Bean
  public Docket docket() {
    /*
    项目所处环境的判断(生成或开发)
    */
    Profiles profile=Profiles.of("prod");
    Boolean isprod=environment.acceptsProfiles(profile);

    return new Docket(DocumentationType.SWAGGER_2)
        //API分组
        .groupName("学生")
        //保证生产环境不可访问swagger
        .enable(!isprod)
        //swagger描述信息的配置
        .apiInfo(apiInfo())
        //配置接口扫描策略
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.controller"))
        /*
        扫描指定controller接口路径
        .paths(PathSelectors.ant("/indexBook/**"))
        */
        //扫描与正则表达式匹配的接口
        .paths(PathSelectors.regex("/*.*"))
        .build();
  }

  //swagger描述信息配置

  private ApiInfo apiInfo() {
    Contact contact =
        new Contact("cxy", "https://www.baidu.com", "18616148247@163.com");
    return new ApiInfo("swagger练习文档",
        "swagger接口API的各种测试", "1.0",
        "localhost:9110/kaiyu",
        contact,
        "Apache2.0",
        "www.jd.com",
        new ArrayList<>()
    );


  }
  /*
  全局参数配置
  * */
  @Bean
  public Docket globelDocket(){
    Parameter parameter=new ParameterBuilder().name("token")
        .description("登录令牌")
        .parameterType("header")
        .modelRef(new ModelRef("String"))
        .build();
    List<Parameter> list=new ArrayList<>();
    list.add(parameter);
 return new Docket(DocumentationType.SWAGGER_2)
     .globalOperationParameters(list)
     .groupName("其他")
     .apiInfo(apiInfo())
     .select().paths(PathSelectors.ant("/hello/**")).build();

  }
}

