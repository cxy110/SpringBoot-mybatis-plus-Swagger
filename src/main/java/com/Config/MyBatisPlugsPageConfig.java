package com.Config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 分页的配置
 */
@Configuration
public class MyBatisPlugsPageConfig {
  @Bean
  public PaginationInterceptor paginationInterceptor(){

    return  new PaginationInterceptor();
  }
}
