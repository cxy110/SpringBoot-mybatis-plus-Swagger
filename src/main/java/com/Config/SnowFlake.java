package com.Config;

import org.activiti.engine.impl.cmd.SaveTaskCmd;

import javax.servlet.http.PushBuilder;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: demo
 * @description: 雪花算法
 * @author: cxy
 * @create: 2019-09-30 11:36
 */
public class SnowFlake {
  //开始时间戳:2019年10月1日0时0分0秒
  public static final long start_temp=1569859200L;
  /*
  每一部分占的位数
  * */
  //数据中心占5个bit位(可以理解为机房,2^10,最多有32个机房)
  public static final long datacenterBits = 5L;
  //机器标识占5个bit位(每个机房里的机器id,每个机房最对有2^10,32台机器)
  public static final long workmachineBits=5L;
  //序列号占了12个bit位
  public static final long sequenceBits=12L;
  /**
   *每一部分的最大值
   */
  //数据中心部分的最大值
  public static final long datacenterMax=-1L^(-1L<<datacenterBits);
  //机器标识的最大值
  public static final long workmachineMax=-1L^(-1L<<workmachineBits);
  //序列号位的最大值
  public static final long sequenceMax=-1L^(-1L<<sequenceBits);

  /**
   *每一部分生成后在合并时的移动位数
   *
   */
  //机器标识左移的位数
  public static final  long machineleftmove=sequenceBits;
  //数据中心的左移位数
   public static final long datacenterleftmove=sequenceBits+workmachineBits;
   //计算后的时间戳的左移位数
  public static final long time_stmp=sequenceBits+workmachineBits+datacenterBits;
  /**
   * 类的属性
   */
  private long datacenterId;//数据中心
  private long machineId; //机器标识
  private long sequenceId=0L; //序列号
  private long lastTimeSTMP=1570504614L; //上一次的时间戳

  /**
   *
   * @param datacenterId
   * @param machineId
   * SnowFlake的有参构造方法
   */
 public  SnowFlake(long datacenterId,long machineId){
   if(datacenterId>datacenterMax||datacenterId<0){
     throw new IllegalArgumentException("数据中心数据不能大于"+datacenterMax+"或者小于0");
   }
   if(machineId>workmachineMax||machineId<0){
     throw new IllegalArgumentException("机器标识不能大于"+workmachineMax+"或者小于0");
   }
   this.datacenterId=datacenterId;
   this.machineId=machineId;
 }

 public long getNextId(){
   //获取当前系统时间
   long currentTime=getNewTime();
  // System.out.println(currentTime);
   //当前时间小于上一次的时间戳
   if(currentTime<lastTimeSTMP){
     throw new  RuntimeException("当前系统时间有问题,请调整");
   }
   //当前时间等于上一次的时间戳,说明是在同一毫秒内,序列号自增
   if (currentTime == lastTimeSTMP) {
     sequenceId = (sequenceId + 1) & sequenceMax;
     //当sequenceId到达当前毫秒的最大值后,加1位与最大值,结果为0,
     if (sequenceId == 0L) {
       //把新的时间赋值给currentTime;
       currentTime = getNextMill();
     }

   } else {
     //到达下一个毫秒时,序列号重新开始
     sequenceId = 0L;
   }
   lastTimeSTMP=currentTime;
   long s=currentTime-start_temp;
   System.out.println("当前时间是:"+currentTime);
   System.out.println("时间戳是:"+s);
return (currentTime-start_temp)<<time_stmp                //时间戳部分
    | datacenterId<<datacenterleftmove       //数据中心(机房)
    |machineId<<machineleftmove              //机器id部分
    |sequenceId;                               //序列号
 }
 public long getNextMill(){
   long newTime = getNewTime();
   while(newTime<=lastTimeSTMP){
    newTime= getNewTime();
   }
   return newTime;
 }
private  synchronized long getNewTime(){
   return System.currentTimeMillis();
}
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(1569859200L);
    int year = calendar.get(Calendar.YEAR);
    int m = calendar.get(Calendar.MONTH);
    int d = calendar.get(Calendar.DAY_OF_WEEK);
   // System.out.println(year+":"+m+":"+d);

    SnowFlake snowFlake = new SnowFlake(1, 1);
    long start = System.currentTimeMillis();
    for(int i=1;i<100000;i++){
      System.out.println( snowFlake.getNextId());
    }
    System.out.println(System.currentTimeMillis()-start);

  }

}
