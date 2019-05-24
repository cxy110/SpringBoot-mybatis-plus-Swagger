package com.solr;

import lombok.Data;

/**
 * 关键词查询时,从后台查到的返回的数据信息
 */
@Data
public class SolrSearchResult {
  private String item_sell_point;
  private String  item_title;
  private long id;
  private long item_price;
}
