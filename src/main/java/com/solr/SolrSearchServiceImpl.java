package com.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolrSearchServiceImpl implements SolrSearchService {
  @Autowired
  private  SolrDao solrDao;
  @Override
  public SolrSearchEntity SolrSearch(String baseURL, String price, String keyword, String sort) throws SolrServerException {
    SolrSearchEntity searchEntity= solrDao.solrSearch(baseURL,price,keyword,sort);
    return searchEntity;
  }
}
