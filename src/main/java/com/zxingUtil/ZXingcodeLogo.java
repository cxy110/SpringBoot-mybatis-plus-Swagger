package com.zxingUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class ZXingcodeLogo {
  //传入logo,二维码-->返回带logo的二维码
  public BufferedImage logoMatrix(BufferedImage matrixImage,String logo)throws Exception{
    //在二维码上画上logo
    //在二维码上产生一个二维画板
    Graphics2D g2=matrixImage.createGraphics();
    //画图(画二维码的logo是在内存中画的),把logo放入内存
    BufferedImage logoImg= ImageIO.read(new File(logo));
    int height=matrixImage.getHeight();
    int width=matrixImage.getWidth();
    //将logo画在二维码上
    g2.drawImage(logoImg,width*2/5,height*2/5,width*1/5,height*1/5,null);

    //然后在logo的外围画上一个白色的外边框和细一点的红边;
       //具体就是通过画笔,画出一个外边框,下面的画法是边框覆盖了部分的logo,因为两者画图的起始位置一样

           //画外边框

    //产生一个白色圆角正方形画笔:第一个参数是画笔的粗细,也就是画出来的边框的宽度
      //第二个参数是画笔的笔尖的行状,是宽或圆,第三个是线段交界处的处理方式,圆角(直角)
    BasicStroke stroke=new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    //将画板和画笔关联
    g2.setStroke(stroke);
    //产生一个正方形
    RoundRectangle2D.Float round=new RoundRectangle2D.Float(width*2/5,height*2/5,width*1/5,height*1/5,
        BasicStroke.CAP_ROUND
        ,BasicStroke.JOIN_ROUND);
    //正方形的颜色是白色
    g2.setColor(Color.white);
    //通过画笔画图(这个正方形的宽度就是画笔的)
    g2.draw(round);

     //画内部的红色边框

    //产生画笔
    BasicStroke stroke1=new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
   g2.setStroke(stroke1);
   //画正方形
    RoundRectangle2D.Float round1=new RoundRectangle2D.Float(width*2/5+2
        ,height*2/5+2,width*1/5-4,height*1/5-4,
        BasicStroke.CAP_ROUND
        ,BasicStroke.JOIN_ROUND);
   g2.setColor(Color.gray);
   g2.draw(round1);
g2.dispose();
matrixImage.flush();
    return matrixImage;
  }
}
