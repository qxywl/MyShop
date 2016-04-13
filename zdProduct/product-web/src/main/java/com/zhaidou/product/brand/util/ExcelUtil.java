package com.zhaidou.product.brand.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.zhaidou.common.util.ReflectUtil;
import com.zhaidou.product.brand.model.ProductBrandModel;

public class ExcelUtil<T> {
   
	
	/**
     * 读取Excel文件数据，封装到DBObject 对象里面
     *
     * @param filename 文件名
     * @param is  文件流
     * @param titleList Excel表格文件头数据格式列表
     * @param fieldList 要导入的对象属性字段列表
     * @return List<DBObject> 
     * @throws IOException 
     */
	public static List<DBObject> readExcel(String filename, InputStream is,List<String> titleList,List<String> fieldList) throws IOException {
       
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

        int count_cell = sheet.getRow(0).getPhysicalNumberOfCells();//读取第一行表头，得到一行单元格总数
        Row titleRow = sheet.getRow(0);
       
        boolean flag  =  validTitle(titleList,titleRow);
        if(!flag){
        	throw new IOException("excel表格数据不正确");
        }
        
        
        for (int i = 1; i <= count_row; i++) { //遍历行，从第2行开始
            DBObject brand = new BasicDBObject(); //存入填入的信息对象
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < count_cell; j++) { //遍历列
                    Cell cell = row.getCell(j);
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
                        brand.put(fieldList.get(j), oContent);
                        
                    } else if (fieldList.get(j) != null) {
                    	brand.put(fieldList.get(j), "");
                    }
                }
            }
            list.add(brand);
        }
        return list;
    }
	
	
	/**
	 * 校验Excel数据格式
	 * */
    private static boolean validTitle(List<String> titleList, Row titleRow) {
		
    	int colTotal =  titleRow.getPhysicalNumberOfCells();//总列数
    	List<String> colTitle = new ArrayList<String>();
    
    	for(int i=0;i<colTotal;i++){//循环获取表头数据
    		 Cell cell = titleRow.getCell(i);
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
                 colTitle.add(i, oContent);
             } 
    	}
    	if(titleList.equals(colTitle)){
    		return true;
    	}
    	return false;
	}
 
  


    /**
     * 导出表格数据
     * @param 文件数据类型
     * @param titles  表头
     * @param sheetName 
     * @param fields  字段名
     * @param List<T> dataList 泛型数据列表
     * @return Workbook
     * @throws Exception 
     */
    public  Workbook writeExcel(String fileType,String sheetName,List<String> titles, List<String> fields, List<T> dataList) throws IOException {
    	
    	Workbook wb = null;
    	if(".xls".equals(fileType)){
    		wb = new HSSFWorkbook();
    	}else if(".xlsx".equals(fileType)){
    		wb = new XSSFWorkbook();
    	}else{
    		throw new IOException("文件格式不正确！！！！");
    	}

        Sheet sheet = wb.createSheet(sheetName);// 创建一个Excel的Sheet
        sheet.setDefaultColumnWidth(35);
        CellStyle cellStyle =  wb.createCellStyle(); //单元格样式
        Font font =  wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setWrapText(true);  // 当文字长于该列的宽度时，自动把宽加长
        //设置单元为可编辑
        cellStyle.setLocked(false);

        //1.创建的表头
        Row sysRow = sheet.createRow(0);
        sysRow.setRowStyle(cellStyle);
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = sysRow.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles.get(i));
        }

        //2.填写数据
        int j = 1;
        JSONObject json = null;
        for (T data : dataList) {
           
        	Row sysRowJ = sheet.createRow(j); //创建行
        	sysRowJ.setHeightInPoints(35);
        	json = (JSONObject) JSON.toJSON(data);
            for (int i = 0; i < fields.size(); i++) {
            	
            	String  key = fields.get(i);
            	Object value = json.get(key);

            	Cell cell0 = sysRowJ.createCell(i);
            	value = value == null ? "" : value;
                cell0.setCellValue(value.toString());
            }
            j++;  // 控制行数
        }
        return wb;
    }
    
   
    public static void main(String[] args) throws Exception {
		File file = new File("d:\\test.xlsx");
	    InputStream in = new FileInputStream(file);
	    
	   /* String [] strTitle ={"品牌名称","品牌别名","品牌说明","品牌故事","品牌国家","品牌logo","状态","品牌旗舰店铺编号","旗舰店认证状态"};
		String [] strField ={"brandName","brandEname","brandInfo","brandStory","brandCountry","brandLogo","brandStatus","flagshipStoreCode","storeCertificationType"};
	    
		List<String> titleList = Arrays.asList(strTitle);
		List<String> fieldList = Arrays.asList(strField);
	    
	    List<DBObject> list= readExcel(file.getName(), in,titleList,fieldList);
	    for (DBObject dbObject : list) {
	    	 System.out.println(dbObject);
		}*/

    	String [] strTitle ={"品牌id","品牌编号","品牌名称","品牌别名","品牌说明","品牌故事","品牌国家","品牌logo","状态","品牌旗舰店铺编号","旗舰店认证状态","最后修改时间","最后修改者","创建时间","创建者"};
    	String [] strField ={"brandId","brandCode","brandName","brandEname","brandInfo","brandStory","brandCountry","brandLogo","brandStatus","flagshipStoreCode","storeCertificationType","updateTime","updateUser","createTime","creator"};
        
    	List<String> titleList = Arrays.asList(strTitle);
    	List<String> fieldList = Arrays.asList(strField);
    	
    	List<ProductBrandModel> list = new ArrayList<ProductBrandModel>();
    	ProductBrandModel productBrandMode =  new ProductBrandModel();
    	productBrandMode.setBrandId(1L);
    	productBrandMode.setBrandCode("brandCode002");
    	productBrandMode.setBrandName("brandName002");
    	productBrandMode.setBrandEname("brandEname002");
    	productBrandMode.setBrandInfo("brandInfo002");
    	productBrandMode.setBrandCountry("brandCountry002");
    	productBrandMode.setBrandLogo("brandLogo002");
    	productBrandMode.setBrandStatus(2);
    	productBrandMode.setFlagshipStoreCode("flagshipStoreCode002");
    	productBrandMode.setStoreCertificationType(2);
    	productBrandMode.setBrandStory("brandStory002");
    	productBrandMode.setUpdateUser("updateUser002");
    	productBrandMode.setCreator("creator002");
    	productBrandMode.setCreateTime(new Date().getTime());
    	productBrandMode.setUpdateTime(new Date().getTime());
    	productBrandMode.setUpdateUser("updateUser002");
    	list.add(productBrandMode);
    	
    	Object obj = ReflectUtil.getVal(productBrandMode,"updateTime");
    	Object obj1 = ReflectUtil.getVal(productBrandMode,"brandId");
    	JSONObject json = (JSONObject) JSON.toJSON(productBrandMode);
    	
    	Object update = json.get("updateTime");
    	
    	System.out.println(update);
    	
    	System.out.println(obj);
    	System.out.println(obj1);
    	Workbook book = new ExcelUtil().writeExcel(".xlsx","品牌",titleList, fieldList, list);
    	OutputStream os = new FileOutputStream("e:\\df1223.xlsx");
    	book.write(os);
    	os.flush();
    	os.close();
    	
	}
}
