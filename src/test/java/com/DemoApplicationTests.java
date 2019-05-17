package com;

import com.lucene.LuceneIndex;
import com.lucene.LuceneSearch;
import com.zxingUtil.QRcodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  @Test
  public void QRcodeTest()throws Exception {
    //生成二维码
    /*
    * 生成的图片路径 imgPath
    * 二维码中所代表的信息 contenct:"helloword"
    *
    */
    String imagePath="src/main/java/二维码.png";
    String contenct="大家好,看我做的二维码";
    String logo="src/main/java/logo.jpg";
    //生成二维码
    new QRcodeUtil().encodeQRCode(contenct,imagePath,"png", 430,430,logo);
    /*
    加密 相关信息-->二维码

    解密  将二维码-->相关信息
    * */
  }
@Test
  public  void Codeprise() throws Exception {
    String uri="src/main/java/解码.png";
    File file=new File(uri);
    String result=new QRcodeUtil().codeprise(file);
    System.out.println("解码后的结果是:"+result);
  }
  //lucene生成索引库
@Test
  public  void creatIndex() throws IOException {
  String indexSearch="D:\\indexCreat";
  String BaseDocument="C:\\Users\\Administrator\\Downloads\\day14\\笔记";
  new LuceneIndex(indexSearch,BaseDocument);

}
//从索引库中进行关键词查询
@Test
  public  void  getIndex() throws IOException {
  String indexSearchpath="D:\\indexCreat";
  String keyWord="笔记";
  String field="filename";
  LuceneSearch luceneSearch=new LuceneSearch();
List list= luceneSearch.lucenesearcher(indexSearchpath,keyWord,field);
  System.out.println(list);
}

@Test
public static void main(String[] args) {
  int min=10;
  int max=11;
  System.out.println("{"+min+" TO "+max+"]");
}
}

