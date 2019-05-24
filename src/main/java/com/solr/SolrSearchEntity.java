package com.solr;

import lombok.Data;

import java.util.List;

/**
 * 封装关键词查询的数据信息
 * 以及高亮显示信息的类
 */
@Data
public class SolrSearchEntity {
  //高亮信息
  private List<String> list2;
  //关键词查询的基本信息
  private  List<SolrSearchResult>list1;



}
