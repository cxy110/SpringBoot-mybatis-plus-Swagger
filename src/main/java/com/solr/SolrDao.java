package com.solr;

import org.apache.solr.client.solrj.SolrServerException;

/**
 * 关键词查询时查询索引库的Dao层接口
 */
public interface SolrDao {
  public SolrSearchEntity solrSearch(String baseURL, String price, String keyword, String sort
  ) throws SolrServerException;
}
