package com.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 关键词查询时查询索引库的Dao层接口实现类
 */
@Repository
public class SolrDaoImpl implements SolrDao {

  @Override
  public SolrSearchEntity solrSearch(String baseURL, String price, String keyword, String sort) throws SolrServerException {

    SolrServer solrServer = new HttpSolrServer(baseURL);
    //查询条件,过滤条件,分页,指定查询结果中的域,默认域,高亮显示,排序
    SolrQuery solrQuery = new SolrQuery();
    //设置查询的域(查询的关键词)
    //"item_sell_point:清仓"
    solrQuery.set("q", keyword);
    //设置过滤条件
    //"item_price:[200000 TO *]"
    if (price != null && !"".equals(price)) {
      String[] split = price.split("-");
      solrQuery.setFilterQueries("item_price:[" + split[0] + " TO " + split[1] + "]");
      String x = "item_price:[" + split[0] + "TO" + split[1] + "]";
    }
    //设置排序方式
    if ("1".equals(sort)) {
      solrQuery.addSort("item_price", SolrQuery.ORDER.desc);
    } else {
      solrQuery.addSort("item_price", SolrQuery.ORDER.asc);
    }
    //设置查询结果中显示的域
    solrQuery.set("fl", "item_sell_point,item_price,item_title,id");
    //设置默认域
    solrQuery.set("df", "item_keywords");
    //设置分页(从第几条开始)
    solrQuery.setStart(0);
    //设置每页记录数
    solrQuery.setRows(10);
    //设置高亮
    //打开高亮
    solrQuery.setHighlight(true);
    //设置高亮域
    solrQuery.addHighlightField("item_sell_point");
    //设置高亮前缀
    solrQuery.setHighlightSimplePre("<span style='color:red'>");
    //设置高亮后缀
    solrQuery.setHighlightSimplePost("</span>");
    //执行查询
    QueryResponse response = solrServer.query(solrQuery);
    //

    SolrDocumentList docs = response.getResults();
    //获取高亮结果集
    //第一个map的key是这个索引对应的数据的id,第二个map的key是高亮对应的域名,List中是高亮对应的内容
    Map<String, Map<String, List<String>>> map = response.getHighlighting();
    List<SolrSearchResult> A = new ArrayList();
    //将高亮得数据放在这个list中
    List<String> highlight = new ArrayList();
    //遍历查询出来的结果,每一条商品信息放在一个SolrSearchResult的对象中
    for (
        SolrDocument doc : docs) {
      SolrSearchResult result = new SolrSearchResult();
      if ( doc.get("item_sell_point") == null ||
          doc.get("item_title") == null) {
        continue;
      }
      result.setId((long) doc.get("id"));

      result.setItem_price((long) doc.get("item_price"));

      result.setItem_sell_point((String) doc.get("item_sell_point"));

      result.setItem_title((String) doc.get("item_title"));
      Object x = doc.get("id");
      //然后把每一条的商品SolrSearchResult对象放在List<SolrSearchResult> A中
      A.add(result);
      //为获得高亮显示的数据
      //其中response.getHighlighting()中的id要求是String,但数据库中是long型,所以先转化成string类型
      String number = doc.get("id").toString();
      //通过转化后的商品id查找高亮域和高亮数据

      Map<String, List<String>> C = map.get(number);
      //map中对应的高亮信息,有的可能为null
      //如果C为空,说明数据库中高亮域所对应的数据为空,那就跳出这次循环,不查高亮信息
      if(C.isEmpty()){
        continue;
      }
      //通过高亮域查找具体高亮的信息
      List<String> d = C.get("item_sell_point");
     /* if(d.isEmpty()){
        continue;
      }*/

      highlight.add(d.get(0));
      System.out.println(highlight.get(0));
    }

    //最后把高亮信息的list集合和查询出的结果集合放入
    SolrSearchEntity solrSearchEntity = new SolrSearchEntity();
    solrSearchEntity.setList1(A);
    solrSearchEntity.setList2(highlight);
    return solrSearchEntity;
  }
}
