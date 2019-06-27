package com.office;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.*;
import java.util.List;

/**
 * @Description:
 * @author:ChenXY
 * @Date:2019/6/25
 */
public class WordTool {
  /*
   * 为段落新增一个run,
   * paragraph:对应的段落
   * text:添加run的文本,
   * pos:文本在文本数组中的位置
   */
public void creatRun(XWPFParagraph paragraph, String text, int pos) {
    XWPFRun run = paragraph.createRun();
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
    run.setText(text, pos);
  }

  /*
   * 获取指定的文档并对其中一段设置格式
   * */
  public void paragraph(String path) {
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
        //设置垂直对齐方式
        paragraph.setVerticalAlignment(TextAlignment.CENTER);
        //设置段落的水平对齐方式
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
        // 遍历段落中的Run
        List<XWPFRun> list = paragraph.getRuns();
        for (XWPFRun a : list) {
          System.out.println(a.toString());
        }

        File file = new File("d://你好.docx");
        FileOutputStream outputStream = new FileOutputStream(file);
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

  /*
   * 获取指定文档,设置页脚页眉等,并为word文档添加表格,
   * path: 获取的word文档的路径
   * name:查询的数据库的表名
   * */
  public void addforDocument(String path, String tname) throws SQLException, ClassNotFoundException {
    OPCPackage opcPackage;
    try {
      opcPackage = POIXMLDocument.openPackage(path);
      XWPFDocument document = new XWPFDocument(opcPackage);
      document.createHeader(HeaderFooterType.DEFAULT);
      document.createFooter(HeaderFooterType.EVEN);
      document.createFootnotes();
      document.createHeaderFooterPolicy();
      document.createNumbering();
      //添加表格
      //查询数据库数据
      //jdbc数据驱动等几步查询
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = null;
      connection = DriverManager.getConnection("jdbc:mysql://192.168.0.96:3306/jtdb?serverTimezone=UTC&useUnicode=true" +
          "&characterEncoding=utf8&characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&autoReconnct" +
          "=true&autoReconnectForPools=true&allowMultiQueries=true", "root", "root");
      String sql = "select * from " + tname;
      PreparedStatement statement = connection.prepareStatement(sql);
      ResultSet  rs = statement.executeQuery();

      //获取结果集中的字段名
      ResultSetMetaData metaData = rs.getMetaData();
      //获取结果集中字段的个数
      int cell = metaData.getColumnCount();
      //新建一个表格,表格的列数就是结果集的字段个数
      XWPFTable table = document.createTable(1, cell);
      //设置表格水平居中
      CTTblPr ctTblPr = table.getCTTbl().addNewTblPr();
      ctTblPr.addNewJc().setVal(STJc.CENTER);
      //获取表格的第一行,将第一行设置为表头,当分页时在各页顶端以标题形式重复出现
      XWPFTableRow row = table.getRow(0);
      CTRow ctRow = row.getCtRow();
      CTTrPr ctTrPr = ctRow.isSetTrPr() ? ctRow.getTrPr() : ctRow.addNewTrPr();
      ctTrPr.addNewTblHeader();
      //并将字段名填充到第一行
      for(int i=0;i<cell;i++){
        row.getCell(i).setText(metaData.getColumnName(i+1));
        System.out.println(row.getCell(i).getText());
      }
      //然后将数据填充到表格中
      while(rs.next()){
        XWPFTableRow row1 = table.createRow();
       for(int j=0;j<cell;j++){
         row1.getCell(j).setText(rs.getString(j+1));
         System.out.println(row1.getCell(j).getText());
         System.out.println("nihao1");
       }
      }
      setCellwidthandVAlign(table);
      rs.close();
      statement.close();
      connection.close();

      File file=new File("d://creattable.docx");
      FileOutputStream fileOutputStream=new FileOutputStream(file);
      document.write(fileOutputStream);
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

/*
设置word中表格样式
* */
public void setCellwidthandVAlign(XWPFTable table){
  CTTbl ctTbl = table.getCTTbl();
  //为表格增加边框属性
  CTTblBorders ctTblBorders = ctTbl.getTblPr().addNewTblBorders();
   //底部边框
  CTBorder bBorder = ctTblBorders.addNewBottom();
     bBorder.setColor("4B0082");
     bBorder.setVal(STBorder.BASIC_WHITE_DASHES);
     bBorder.setSz(BigInteger.valueOf(10));
     //左侧边框
  CTBorder lBorder = ctTblBorders.addNewLeft();
    lBorder.setColor("FFFF00");
    lBorder.setVal(STBorder.BABY_PACIFIER);
    lBorder.setSz(BigInteger.valueOf(10));
    //顶部边框
  CTBorder tBorder = ctTblBorders.addNewTop();
    tBorder.setColor("FF0000");
    tBorder.setVal(STBorder.BABY_RATTLE);
    tBorder.setSz(BigInteger.valueOf(10));
    //右侧边框
  CTBorder rBorder = ctTblBorders.addNewRight();
    rBorder.setColor("FF00FF");
    rBorder.setVal(STBorder.CAKE_SLICE);
    rBorder.setSz(BigInteger.valueOf(10));
    //内部竖线
  CTBorder vBorder = ctTblBorders.addNewInsideV();
    vBorder.setColor("0000FF");
    vBorder.setVal(STBorder.CANDY_CORN);
    vBorder.setSz(BigInteger.valueOf(5));
    //内部横线
  CTBorder hBorder = ctTblBorders.addNewInsideH();
    hBorder.setColor("00FF00");
    hBorder.setVal(STBorder.BIRDS_FLIGHT);
    hBorder.setSz(BigInteger.valueOf(4));


  //获取表格的第一行中每一列的集合
  List<XWPFTableCell> tableCells = table.getRow(0).getTableCells();
  //定义好单元格宽度和类型(对齐方式)
  CTTblWidth ctTblWidth=CTTblWidth.Factory.newInstance();
  ctTblWidth.setW(BigInteger.valueOf(1300));
  ctTblWidth.setType(STTblWidth.AUTO);
  //循环设置第一行(表头)单元格宽度,颜色,对齐方式等
  for(XWPFTableCell cell:tableCells){
    //设置单元格的垂直对齐方式
    cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
    //设置单元格颜色
    cell.setColor("FFF8DC");
    //获取单元格属性
    CTTcPr tcPr = cell.getCTTc().getTcPr();
    tcPr.addNewVAlign().setVal(STVerticalJc.CENTER);
    //设置表格内容水平居中
    cell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
    System.out.println(cell.getCTTc().getPList().size());
    if(tcPr!=null){
      tcPr.setTcW(ctTblWidth);
    }else{
      tcPr=CTTcPr.Factory.newInstance();
      tcPr.setTcW(ctTblWidth);
    }
  }

  //设置除表头外的其他行的样式
  int Rows = table.getNumberOfRows();
  for(int i=1;i<Rows;i++){
    XWPFTableRow row = table.getRow(i);
    List<XWPFTableCell> tableCells1 = row.getTableCells();
    for(XWPFTableCell cell:tableCells1){
      //设置单元格的垂直对齐方式
      cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
      //设置单元格颜色
      cell.setColor("1E90FF");
      CTTcPr tcPr = cell.getCTTc().getTcPr();
      tcPr.addNewVAlign().setVal(STVerticalJc.CENTER);
      cell.getCTTc().getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
      tcPr.setTcW(ctTblWidth);
    }
  }
}


}