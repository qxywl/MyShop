package com.zhaidou.common.util;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ExcelIO<T> {
    /**
     * 读取导入Excel文件
     *
     * @param filename
     * @param is
     * @throws java.io.IOException
     * @return List<DBObject>
     */
    public static List<DBObject> read(String filename, InputStream is, List<String> headerList, List<String> fieldList) throws IOException {
       
		Workbook wb = null;
        if (filename.endsWith("xls")) {
            wb = new HSSFWorkbook(is);
        } else if (filename.endsWith("xlsx")) {
            wb = new XSSFWorkbook(is);
        }
        
        //读取第1个工作表
        Sheet sheet = wb.getSheetAt(0);
        //共有多少行
        int count_row = sheet.getLastRowNum();
        List<DBObject> list = new ArrayList<DBObject>(); //存放名单数据
        List<String> tmpHeaderList = new ArrayList<String>(); //存放表头对应字段名

        int count_cell = sheet.getRow(0).getPhysicalNumberOfCells();//读取第一行表头，得到一行单元格总数
      
        //处理表头,判断表头是否跟预定义的表头一样
        if(!checkHeader(headerList, sheet.getRow(0))){
    		throw new IOException("文件头与模板不一致");
        }
        for (int i = 1; i <= count_row; i++) { //遍历行，从第2行开始
        	DBObject dbObj = new BasicDBObject(); //存入填入的信息对象
//        	JSON json = JSON.parse(text)
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < count_cell; j++) { //遍历列
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                            	Long intVal = (long)cell.getNumericCellValue();
                            	dbObj.put(fieldList.get(j), intVal);
                                break;
                            case Cell.CELL_TYPE_STRING:
                                String str = cell.getStringCellValue();
                                dbObj.put(fieldList.get(j), str);
                                break;
                            default:
                            	dbObj.put(fieldList.get(j), cell.getDateCellValue());
                                break;
                        }
                    } else if (fieldList.get(j) != null) {
                    	dbObj.put(fieldList.get(j), null);
                    }
                }
                list.add(dbObj);
            }
//            JSON.serialize(dbObj)
        }
        return list;
    }

    
    /**
     * 处理表头：将模板中的中文表头转换成对应的字段名
     *
     * @param headerList    通道字段对象
     * @param firstRow 第一行
     * @param oContent  中文表头
     * @param col       表头所有列
     */
    private static Boolean checkHeader( List<String> headerList, Row firstRow) {
    	 List<String> tmpList = new ArrayList<String>(); //存放读取到的表头
    	 int count_cell = firstRow.getPhysicalNumberOfCells();
         if (firstRow != null) {
             for (int j = 0; j < count_cell; j++) { //遍历列
                 Cell cell = firstRow.getCell(j);
                 if (cell != null) {
                 	String oContent = null;
                     switch (cell.getCellType()) {
                         case Cell.CELL_TYPE_NUMERIC:
                             BigDecimal db = new BigDecimal(cell.getNumericCellValue());
                             oContent = db.toPlainString();
                             break;
                         case Cell.CELL_TYPE_STRING:
                             oContent = cell.getStringCellValue();
                             break;
                         default:
                             oContent = "";
                             break;
                     }
                     oContent = oContent.trim();
                 	//处理名单内容
                   tmpList.add(oContent);
                 }
             }
         }
    	if(tmpList.equals(headerList))
    		return true;
        return false;
    }
    
    /**
     * 根据报名通道生成导入名单模板
     *
     * @param channelTitle 报名通道名称
     * @param fields       表头，报名要填写的信息
     * @return
     * @throws java.io.IOException
     */
    public static HSSFWorkbook write(String channelTitle, List<String> fields) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        String title = channelTitle + "名单导入模板";
        Sheet sheet = wb.createSheet(title);// 创建一个Excel的Sheet
      
        HSSFCellStyle cellStyle = wb.createCellStyle(); //单元格样式
       
        HSSFFont font = wb.createFont();//创建字体 样式
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 12);
        
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setWrapText(true);  // 当文字长于该列的宽度时，自动把宽加长
        //设置单元为不可编辑
        cellStyle.setLocked(true);

        //创建一行，第一行用来写报名通道信息
        Row sysRow = sheet.createRow(0);
        //创建单元格，第一个单元格写入报名通道名称
        Cell cell0 = sysRow.createCell(0);
        cell0.setCellValue(title);
        cell0.setCellStyle(cellStyle);

        //创建第二行，用来写入表头
        Row headerRow = sheet.createRow(1);
        int col = 0; //列
        for (String field : fields) {
            if (col < 2) {
                sheet.setColumnWidth(col, title.length() * 3 * 256);
            } else {
                sheet.setColumnWidth(col, field.length() * 3 * 256);
            }
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(field);

            cell.setCellStyle(cellStyle);
            col++;
        }
        return wb;
    }


    /**
     * 以xlsx文件格式导出品牌
     *
     * @param headers  表头
     * @param fields  字段名
     * @param members 参会人员列表
     * @return
     * @throws Exception 
     */
    public  Workbook export(String fileType,String sheetName, List<String> headers, List<String> fields, List<T> dataSet) throws Exception {
    	
    	Workbook wb = null;
    	if(".xls".equals(fileType)){
    		wb = new HSSFWorkbook();
    	}else if(".xlsx".equals(fileType)){
    		wb = new XSSFWorkbook();
    	}else{
    		throw new Exception("文件格式不正确！！！！");
    	}
    	
        Sheet sheet = wb.createSheet(sheetName);// 创建一个Excel的Sheet
        DataFormat dataFormat = wb.createDataFormat();
        
        CellStyle cellStyle =  wb.createCellStyle(); //表头单元格样式
        
        CellStyle cellStyle2 =  wb.createCellStyle(); //单元格样式
        short numbericFormat = HSSFDataFormat.getBuiltinFormat("0"); //整数
        cellStyle2.setDataFormat(numbericFormat);
        
        CellStyle cellStyle3 =  wb.createCellStyle(); //单元格样式
        short dateFormat = dataFormat.getFormat("yyyy/MM/dd hh:mm:ss");
        cellStyle3.setDataFormat(dateFormat);
        
        CellStyle cellStyle4 =  wb.createCellStyle(); //单元格样式
        short textFormat = dataFormat.getFormat("@"); //文本格式
        cellStyle4.setDataFormat(textFormat);
        
        Font font =  wb.createFont();
        
        
        
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setWrapText(true);  // 当文字长于该列的宽度时，自动把宽加长
        //设置单元为不可编辑
        cellStyle.setLocked(true);

        //1.创建的表头
        Row sysRow = sheet.createRow(0);
        sysRow.setRowStyle(cellStyle);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = sysRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headers.get(i));
        }

        
        //2.填写数据
        int j = 1;
        JSONObject json = null;
        for (T row : dataSet) {
           
        	Row sysRowJ = sheet.createRow(j); //创建行
        	json = (JSONObject) JSON.toJSON(row);
            for (int i = 0; i < fields.size(); i++) {
            	
            	String  key = fields.get(i);
            	Object value = json.get(key);

            	Cell cell0 = sysRowJ.createCell(i);
            	value = value == null ? "" : value;
            	if(value instanceof Number){
            		
            		cell0.setCellStyle(cellStyle2);
            		cell0.setCellValue(Double.parseDouble(value.toString()));
            	}else if(value instanceof Date){
            		cell0.setCellStyle(cellStyle3);
            		cell0.setCellValue((Date)value);
            	}else{
            		//默认是文本格式
            		cell0.setCellStyle(cellStyle4);
            		cell0.setCellValue(value.toString());
            	}
//                cell0.setCellValue(value.toString());
            }
            j++;  // 控制行数
        }
        return wb;
    }
    
   
//    public static void main(String[] args) throws Exception {
//		File file = new File("d:\\test.xlsx");
//	    InputStream in = new FileInputStream(file);
//	    List<DBObject> list= read(file.getName(), in);
//	    for (DBObject dbObject : list) {
//	    	 System.out.println(dbObject);
//		}
//
///*    	String [] strTitle ={"品牌id","品牌编号","品牌名称","品牌别名","品牌说明","品牌故事","品牌国家","品牌logo","状态","品牌旗舰店铺编号","旗舰店认证状态","最后修改时间","最后修改者","创建时间","创建者"};
//    	String [] strField ={"brandId","brandCode","brandName","brandEname","brandInfo","brandStory","brandCountry","brandLogo","brandStatus","flagshipStoreCode","storeCertificationType","updateTime","updateUser","createTime","creator"};
//        
//    	List<String> titleList = Arrays.asList(strTitle);
//    	List<String> fieldList = Arrays.asList(strField);
//    	
//    	List<ProductBrandModel> list = new ArrayList<ProductBrandModel>();
//    	ProductBrandModel productBrandMode =  new ProductBrandModel();
//    	productBrandMode.setBrandId(1);
//    	productBrandMode.setBrandCode("brandCode002");
//    	productBrandMode.setBrandName("brandName002");
//    	productBrandMode.setBrandEname("brandEname002");
//    	productBrandMode.setBrandInfo("brandInfo002");
//    	productBrandMode.setBrandCountry("brandCountry002");
//    	productBrandMode.setBrandLogo("brandLogo002");
//    	productBrandMode.setBrandStatus(2);
//    	productBrandMode.setFlagshipStoreCode("flagshipStoreCode002");
//    	productBrandMode.setStoreCertificationType(2);
//    	productBrandMode.setBrandStory("brandStory002");
//    	productBrandMode.setUpdateUser("updateUser002");
//    	productBrandMode.setCreator("creator002");
//    	productBrandMode.setCreateTime(new Date().getTime());
//    	productBrandMode.setUpdateTime(new Date().getTime());
//    	productBrandMode.setUpdateUser("updateUser002");
//    	list.add(productBrandMode);
//    	
//    	Object obj = ReflectUtil.getVal(productBrandMode,"updateTime");
//    	Object obj1 = ReflectUtil.getVal(productBrandMode,"brandId");
//    	JSONObject json = (JSONObject) JSON.toJSON(productBrandMode);
//    	
//    	Object update = json.get("updateTime");
//    	
//    	System.out.println(update);
//    	
//    	System.out.println(obj);
//    	System.out.println(obj1);*/
//    	/*Workbook book = ExcelUtil.writeBrand(".xlsx",titleList, fieldList, list);
//    	OutputStream os = new FileOutputStream("e:\\df1223.xlsx");
//    	book.write(os);
//    	os.flush();
//    	os.close();*/
//    	
//	}
}
