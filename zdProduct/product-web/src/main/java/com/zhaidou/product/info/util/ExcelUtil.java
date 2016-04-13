package com.zhaidou.product.info.util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/** 
 * Excel生成類. 
 */  
public class ExcelUtil {  
    
    /** 
     * 根據模板生成Excel文件. 
     * @param templateFileName 模板文件. 
     * @param list 模板中存放的數據. 
     * @param resultFileName 生成的文件. 
     */  
    public void createExcel(String templateFileName, List<?> list, String resultFileName){  
        //創建XLSTransformer對象  
        XLSTransformer transformer = new XLSTransformer();  
        //獲取java項目編譯後根路徑  
        URL url = this.getClass().getClassLoader().getResource("");  
        //得到模板文件路徑  
        String srcFilePath = url.getPath() + templateFileName;  
        Map<String,Object> beanParams = new HashMap<String,Object>();  
        beanParams.put("list", list);  
        String destFilePath = url.getPath() + resultFileName;  
        try {  
            //生成Excel文件  
            try {
                transformer.transformXLS(srcFilePath, beanParams, destFilePath);
            } catch (InvalidFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        } catch (ParsePropertyException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  
