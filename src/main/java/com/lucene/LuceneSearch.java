package com.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//关键词查询索引库
public class LuceneSearch {

  /**
   * indexSearchpath 索引库的位置
   *   查询操作的公共设置部分
   */
  public IndexSearcher manager(String indexSearchpath) throws IOException {
    //第一步,创建一个directory对象,也就是索引库的位置
    Directory directory = new SimpleFSDirectory(new File(indexSearchpath));
    //第二步,创建一个indexReader对象,需要指明directory对象,表明查询的索引库位置
    IndexReader indexReader = DirectoryReader.open(directory);
    //第三步,创建一个indexSearch对象,指定indexReader对象
    IndexSearcher indexSearcher = new IndexSearcher(indexReader);
    return indexSearcher;
  }

  /**
   * 查询结果进行封装操作
   */
  public List resultEidt(IndexSearcher indexSearcher,Query query) throws IOException {
    //查询出评分最高的5条
    TopDocs topDocs = indexSearcher.search(query, 5);
    //遍历并返回查询结果 ,查询的结果是对应的文档的id
    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
    ArrayList list = new ArrayList();
    for (ScoreDoc scoreDoc : scoreDocs) {
      int docID = scoreDoc.doc;
      Document document = indexSearcher.doc(docID);
      //查询出之前放进去的内容(通过索引库中的域名)
      //文件名
      String filename = document.get("filename");
      String filepath = document.get("filepath");
      String filesize = document.get("filesize");
      String fileconent = document.get("fileconent");
      String[] file = new String[4];
      file[0] = filename;
      file[1] = filepath;
      file[2] = filesize;
      file[3] = fileconent;

      list.add(Arrays.deepToString(file));
      System.out.println(Arrays.deepToString(file));

    }
    return list;
  }


  /**
   * 有条件的查询(指定域和关键词) 精准查询
   */
  public List lucenesearcher(String indexSearchpath, String keyword, String field) throws IOException {
  IndexSearcher indexSearcher=manager(indexSearchpath);
    //第四步,创建一个TermQuery对象(精准查询),指定查询的域和查询的关键词
    Query query = new TermQuery(new Term(field, keyword));
    //调用封装结果方法进行查询出结果并封装
    List list = resultEidt(indexSearcher,query);
    //关闭资源
   indexSearcher.getIndexReader().close();
    return list;
  }


  /**
   * 查询所有关键词
   */
  public List  luceneSearchAll(String indexSearchpath) throws IOException {
   IndexSearcher indexSearcher= manager(indexSearchpath);
   //查询所有(query)
   Query query=new MatchAllDocsQuery();
   //调用方法查询出结果并封装返回
    List list=resultEidt(indexSearcher,query);
    //关闭资源
    indexSearcher.getIndexReader().close();
    return list;
  }

  /**
   * 根据数值范围查询
   */
  public  List luceneSearchByNumber(String indexSearchpath,String field,long max,long min) throws IOException {
    IndexSearcher indexSearcher=manager(indexSearchpath);
    Query query= NumericRangeQuery.newLongRange(field,min,max,false,true);
   List list= resultEidt(indexSearcher,query);
   indexSearcher.getIndexReader().close();
   return list;
  }

/**
 * 组合查询,多条件结合查询(可以有多个,可以不止两个)
 */
 public List luceneBooleanSearch(String indexSearchpath, String field1, String field2, String keyWord1,String keyWord2) throws IOException {
 IndexSearcher indexSearcher=  manager(indexSearchpath);
 //第一个索引查询
 Query query1=new TermQuery(new Term(field1,keyWord1));
 //第二个索引查询
 Query query2=new TermQuery(new Term(field2,keyWord2));
 //组合查询
   BooleanQuery booleanQuery=new BooleanQuery();
   //第二个参数中must意味着组合查询中必须有这个索引条件
   booleanQuery.add(query1, BooleanClause.Occur.MUST);
   //第二个参数should意味着在组合查询中这个条件可有可无(相当于数据库查询中的or)
   booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
List list=resultEidt(indexSearcher,booleanQuery);
indexSearcher.getIndexReader().close();
return list;
 }

  /**
   * 语法解析查询,上面的一些查询query条件设置好了后,底层会先解析成对象的语法,然后
   * 才会会查询,
   * 如   1.MatchAllDocsQuery()解析后的语法就是  *:*  ,第一个*代表域名,第二个参数
   * 代表查询的关键词
   *       2 有条件的精准查询 解析后就是 域名:关键词
   *       3.数值范围查询: 域名:{min TO max] ,其中{ 代表不包含, ] 代表包含
   *       4.组合查询 +代表必须包含,-代表不包含, 没有符号代表可有可无
   *  +域名1:关键词1 +域名2:关键词2   -域名1:关键词1 域名2:关键词2
   *      也可以是: 域名1:关键词1 AND 域名2:关键词2  域名1:关键词1 OR 域名2:关键词2
   *      域名1:关键词1 NOT 域名2:关键词2
   */
  public  List lunceneParseSearch(String IndexSearchpath, String field1, String field2,
                                  String keyword1, String keyword2,long min,long max) throws Exception{
    IndexSearcher indexSearcher=manager(IndexSearchpath);
    //这里注意QueryParser的导包
    //第一个参数是语法解析设定的默认域,第二个就是查询用的分词器
    QueryParser queryParser=new QueryParser(field1,new IKAnalyzer());
    //里面的参数就是语法(上面那些查询中query对象的结果(类型)就是语法)
      //这个是查询所有,如果下面写出了域名,那么上面设定的默认域就会失效
         queryParser.parse("*:*");
         //查询默认域下的关键词(查field1域下的keyword1)
       queryParser.parse(keyword1);
       //查询指定的域下的关键词
    queryParser.parse(field2+":"+keyword2);
     //如果关键词是一句话,那先将这一句话分词,然后查找对应的分词出来的所有结果
       //索引库中的java  dependenties  fast 都会被查出来
   queryParser.parse("java  dependenties so fast");
         //查询值范围下的
     queryParser.parse("{"+min+" TO "+max+"]");
       //组合查询
      queryParser.parse("+"+field1+":"+keyword1+" "+"-"+field2+":"+keyword2);
    Query query=  queryParser.parse(field1+":"+keyword1+" AND "+field2+":"+keyword2);
   List list=resultEidt(indexSearcher,query);
   indexSearcher.getIndexReader().close();
   return list;
  }

  /**
   * 多个默认域的语法解析查询,其他和单个域的一样,多个域就是这些条件在多个默认域下都查,
   * 如果语法中指定了域,那设定的多个默认域也和单个的默认域一样无效
   */
 public List Mu(String indexSearchpath,String field1, String field2,
                String keyword1, String keyword2,long min,long max) throws Exception {
   IndexSearcher indexSearcher=manager(indexSearchpath);
   //多个默认域的数组
   String[]fields={field1,field2};
   //设置默认域
   QueryParser queryParser=new MultiFieldQueryParser(fields,new IKAnalyzer());
   Query query= queryParser.parse(keyword1);
    List list=resultEidt(indexSearcher,query);
    indexSearcher.getIndexReader().close();
    return list;
 }
}