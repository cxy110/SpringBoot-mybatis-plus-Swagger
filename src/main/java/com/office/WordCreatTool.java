package com.office;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * @Description:java生成word文档的工具类
 * @author:ChenXY
 * @Date:2019/6/24
 */
public class WordCreatTool {
  public void  method1() {
    OPCPackage pack;
    {
      try {
        //获取word的package对象
        pack = POIXMLDocument.openPackage("C:\\Users\\Administrator\\Desktop\\课件.docx");
        //获取xword解析对象
        XWPFDocument document = new XWPFDocument(pack);
        //获取第一个段落对象
        XWPFParagraph paragraph = document.getParagraphs().get(1);
        System.out.println(paragraph);
        //设置段落的对齐方式
//      paragraph.setAlignment(ParagraphAlignment.LEFT);
        //指定段落上方边框样式
        paragraph.setBorderTop(Borders.CUP);
        //指定应在指定段落周围的页面左侧显示的边框
        paragraph.setBorderLeft(Borders.BATS);
        //指定应在指定段落周围的页面右侧显示的边框
        paragraph.setBorderRight(Borders.BABY_RATTLE);
        //指定应在一组段落下方显示的边框，这些段落具有相同的段落边框设置
        //     paragraph.setBorderBottom(Borders.BALLOONS_3_COLORS);
        //设置首行缩进
        paragraph.setFirstLineIndent(20);
        //设置文本的对齐方式,段落的对齐方式 1左 2中 3右 4往上  不可写0和负数
//      paragraph.setFontAlignment(1);
        //设置父段第一行的缩进
        //     paragraph.setIndentationFirstLine(10);
        //首行前进,指定应从父段落的第一行中删除的缩进，方法是将第一行上的缩进移回文本流的方向的开头。
        //     paragraph.setIndentationHanging(10);
        //整段右移
//      paragraph.setIndentFromLeft(400);
        //整段左移
//      paragraph.setIndentFromRight(400);
        //设置段落的编码
        paragraph.setNumID(BigInteger.ONE);
        //指定在分页视图中呈现此文档时，此段落的内容将在文档中新页面的开头呈现
        paragraph.setPageBreak(true);
        //以绝对单位在此段落的最后一行之后添加的间距
        paragraph.setSpacingAfter(40);
        //应以行为单位在此段落的最后一行之后添加的间距,间距是3行
//      paragraph.setSpacingAfterLines(3);
        //以绝对单位在本段落之前添加的间距
        paragraph.setSpacingBefore(40);
        //以行为单位在本段落前添加间距,2行
//      paragraph.setSpacingBeforeLines(2);
        //设置段落中行之间的间距
//      paragraph.setSpacingBetween(2.00);
        paragraph.setSpacingBetween(1.35, LineSpacingRule.AT_LEAST);
        //指定如何计算行之间的间距以存储在line属性中
        paragraph.setSpacingLineRule(LineSpacingRule.AT_LEAST);
        paragraph.setStyle("标题1");
        //设定本段文本的垂直对齐方式
//        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        //此元素指定消费者是否应通过将两行中的单词（在字符级别上打破）或将单词移动到下一行（打破单词级别）来破坏超出行的文本范围的拉丁文本
        paragraph.setWordWrapped(true);
        //删除段落中pos位置的Run
//      paragraph.removeRun(0);
        List<XWPFRun> list = paragraph.getRuns();
        for (XWPFRun a : list) {
          System.out.println(a.toString());
        }

      XWPFRun run=  paragraph.createRun();

        //设置文字颜色
        run.setColor("FFB6C1");
        //在文档中显示时，粗体属性是否应应用于此运行内容中的所有非复杂脚本字符。
        run.setBold(true);
        run.setCapitalized(true);
        //双删除线
        run.setDoubleStrikethrough(true);
        //浮雕字体----效果和印记（悬浮阴影）类似
        run.setEmbossed(false);
        //字体
        run.setFontFamily("黑体");
        run.setFontSize(14);
        //印迹（悬浮阴影）---效果和浮雕类似
        run.setImprinted(false);
        //设置斜体
        run.setItalic(false);
        //字距调整----这个好像没有效果
        run.setKerning(1);
        //文本会变粗有重影
        run.setShadow(true);
        //改变了  英文字母  的格式
        run.setSmallCaps(true);
        //设置下划线
        run.setUnderline(UnderlinePatterns.DASH);
        //设置上标,下标
        run.setSubscript(VerticalAlign.SUPERSCRIPT);
        run.setText("舜发于畎亩之中， 傅说举于版筑之间， 胶鬲举于鱼盐之中， 管夷吾举于士,the BEST iS Me",0);

        File file=new File("d://你好.docx");
      FileOutputStream outputStream = new FileOutputStream(file);

     //   XWPFDocument x= paragraph.getDocument();
        document.write(outputStream);
//其中document不关闭，因为关闭后对document执行的操作会被保存到原文件
        outputStream.flush();
        outputStream.close();
        pack.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
