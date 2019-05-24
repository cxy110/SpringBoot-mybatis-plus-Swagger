package com.solr;

import lombok.Data;

/**
 * 在前端提交给后端的参数过多时,可以将这些参数封装在一个对象中,然后通过
 * RequestBody注解接收,前端将这些参数和值封装成Json对象,传给后端
 *
 */
@Data
public class SolrParam {
  private final String baseURL = "http://localhost:8080/solr/test";
  String price;
  String keyword;
  String sort;
}
