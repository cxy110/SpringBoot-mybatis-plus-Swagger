package com.zxingUtil;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jp.sourceforge.qrcode.util.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRcodeUtil {
  //加密方法content
  /*
  content: 文字信息
  imgPath:生成路径
  imgType:生成的二维码图片类型
  size:图片尺寸
  * */
  public void encodeQRCode(String content,String imgPath,String imgType, int width,int height,
                           String logo )throws Exception{
   //生成图片
    //BufferedImage代表内存中的一张图片,在内存中生成后输出到硬盘
    BufferedImage bufferedImage= qRCodeCommon(content, imgType,width,height,logo); //BufferedImage最终是RenderImage
    // 的实现类


   File file=new File(imgPath); //输出路径
    ImageIO.write(bufferedImage,imgType,file);
  }

  /**
   *
   * @param content
   * @param imgType
   * @param
   * @return
   * @throws Exception
   * 在内存中生成一个二维码图片
   */
  public  BufferedImage qRCodeCommon(String content,String imgType,int width,int height,String logo)throws Exception{
   BufferedImage bufferedImage=null;
    //图片的大小,颜色(计算机3原色)
       bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    Hashtable <EncodeHintType,Object>map= new Hashtable<EncodeHintType,Object>();
     //排错率
    map.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.Q);
    //二维码外边距,二维码外边与图片外边的距离
    map.put(EncodeHintType.MARGIN,1);
    //设置字符编码
    map.put(EncodeHintType.CHARACTER_SET,"utf-8");
    map.put(EncodeHintType.QR_VERSION,15);
   //BitMatrix中包含了一个Boolean[][]数组 ,BarcodeFormat参数为生成的类型
   BitMatrix byteMatrix=new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,map);
   //对相应的位置画图,形成二维码
   for(int x=0;x<width;x++){
     for(int y=0;y<height;y++){
       bufferedImage.setRGB(x,y,byteMatrix.get(x,y)? Color.BLACK:Color.WHITE);
     }
   }
//画logo
  bufferedImage= new ZXingcodeLogo().logoMatrix(bufferedImage,logo);

    return bufferedImage;







  }

  //解密二维码
  public  String codeprise(File file) throws Exception {
    //将需要解码的文件传入内存中
    BufferedImage image=ImageIO.read(file);
    MultiFormatReader formatReader=new MultiFormatReader();
    LuminanceSource source=new BufferedImageLuminanceSource(image);
    Binarizer binarizer=new HybridBinarizer(source);
    BinaryBitmap binaryBitmap=new BinaryBitmap(binarizer);
    Map map=new HashMap();
    map.put(EncodeHintType.CHARACTER_SET,"utf-8");
    Result result=  formatReader.decode(binaryBitmap,map);
    return result.toString();
  }
}
