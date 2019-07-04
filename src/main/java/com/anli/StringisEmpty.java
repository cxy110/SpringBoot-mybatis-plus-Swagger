package com.anli;

/**
 * @program: demo
 * @description: isEmpty和"'的联系区别
 * @author: cxy
 * @create: 2019-07-04 14:50
 */
public class StringisEmpty {
  public static void main(String[] args) {
    String a=new String();
    String b="";
    String c=null;
    test(c);
  }
  /*
  isEmpty()和""都是分配了内存,然后isEmpty()里的值是空的(绝对的空),而""的值是就是空字符串,(相对的空),
isEmpty是判断对象的状态的,只要对象内部文本是空的(文本的数量是0),通过isEmpty判断就是true,""也是一个对象(空的),
而null是未分配内存,是不存在的,无法用isEmpty()来比较,会报空指针异常
   */
  public static void test(String s){
    if(s.isEmpty()){
      System.out.println(s+"是分配内存了,但是值是空的");
    }
    if(s==""){
      System.out.println(s+"你好");
    }
    if(s.equals("")){
      System.out.println(s+"比较值是否一样");
    }
  }
}
