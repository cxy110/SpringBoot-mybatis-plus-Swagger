package com.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//关键词查询索引库
public class LuceneSearch {
//indexSearch 索引库的位置
  public static List lucenesearcher(String indexSearchpath, String keyword,String field) throws IOException {
  //第一步,创建一个directory对象,也就是索引库的位置
    Directory directory=new SimpleFSDirectory(new File(indexSearchpath));
    //第二步,创建一个indexReader对象,需要指明directory对象,表明查询的索引库位置
    IndexReader indexReader= DirectoryReader.open(directory);
    //第三步,创建一个indexSearch对象,指定indexReader对象
    IndexSearcher indexSearcher=new IndexSearcher(indexReader);
    //第四步,创建一个TermQuery对象(精准查询),指定查询的域和查询的关键词
    Query query=new TermQuery(new Term(field,keyword));
    //查询出评分最高的5条
    TopDocs topDocs= indexSearcher.search(query,5);
    //遍历并返回查询结果 ,查询的结果是对应的文档的id
    ScoreDoc[]scoreDocs=topDocs.scoreDocs;
    ArrayList list=new ArrayList();
    for(ScoreDoc scoreDoc:scoreDocs){
      int docID=scoreDoc.doc;
      Document document=indexSearcher.doc(docID);
      //查询出之前放进去的内容(通过索引库中的域名)
      //文件名
      String filename=document.get("filename");
      String filepath=document.get("filepath");
      String filesize=document.get("filesize");
      String fileconent=document.get("fileconent");
      String[]file=new String[4];
      file[0]=filename;
      file[1]=filepath;
      file[2]=filesize;
      file[3]=fileconent;

      list.add(Arrays.deepToString(file));
      System.out.println(Arrays.deepToString(file));
    }
    //关闭资源
    indexReader.close();
return list;
  }
}
