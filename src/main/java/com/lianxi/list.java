package com.lianxi;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: cxy
 * @create: 2019-09-06 14:50
 */
public class list {
  public static void main(String[] args) {
    List<Object> ms = new ArrayList<Object>();
    ms.add("1");
    ms.add("2");
    ms.add("3");
    ms.add("4");
    System.out.println(ms);
  }
}
class MyData {
  private String name;
  public MyData(String name) {
    this.name = name;
  }
  public String toString() {
    return "数据域：" + name;
  }


  }


