package com.demo;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.omg.PortableInterceptor.INACTIVE;
import sun.awt.SunHints;

import javax.el.LambdaExpression;
import java.util.*;

/**
 * @program: demo
 * @description: 几种循环的比较, 包括普通, foreach 和lambda等
 * @author: cxy
 * @create: 2019-10-10 09:35
 */
public class forEach {
  static class User{
    String name;
    Integer age;
public  User(String name, Integer age){
  this.age=age;
  this.name=name;
}
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getAge() {
      return age;
    }

    public void setAge(Integer age) {
      this.age = age;
    }
  }
  public  List<Integer> turn(){
    List<Integer> ints = new ArrayList<>();
    for(int i=0;i<100000;i++){
      ints.add(i);
    }
    return ints;
  }

  public static void main(String[] args) {
    List<Integer> turn = new forEach().turn();
    //foreach循环
    long simple = System.currentTimeMillis();
    for (Integer integer : turn) {
      integer.toString();
    }
    long simpleEnd = System.currentTimeMillis();
    System.out.println("foreach时间:" + (simpleEnd - simple));

    //lambda表达式循环
    long lambda = System.currentTimeMillis();
    turn.forEach(t -> t.toString());
    long lamdbaend = System.currentTimeMillis();
    System.out.println("lambda时间:" + (lamdbaend - lambda));
    //java8中新的stream+foreach
    long startStream = System.currentTimeMillis();
    turn.stream().forEach(integer -> integer.toString());
    long endStream = System.currentTimeMillis();
    System.out.println("stream时间:" + (endStream - startStream));

    //java8中的parallelStream
    long startParallelStream = System.currentTimeMillis();
    turn.parallelStream().forEach(integer -> integer.toString());
    long endparallelStream = System.currentTimeMillis();
    System.out.println("parallelStream时间:" + (endparallelStream - startParallelStream));




    //lambda表达式只能用在函数式接口,比如在一个方法上传入一个参数,这个参数如果想要用lambda表达,那这个参数必须要求是,函数式接口的;
    // lambda表达式运算后的内容必须赋值给一个函数式接口.

    //lambda表达式内部的引用的外围变量必须是最终变量(初始化后,不会赋新值)
    ArrayList<User> user = new ArrayList<>();
    for(int i=1;i<100;i++){
      User user1 = new User("name" + i, i);
      user.add(user1);

    }
    //想要使用这种方法引用,那么就要用::分割方法名与对象或类,也就是::前面必须是foreach所对应的对象或类
   user.forEach(User::toString);

  }




}
