package com.zhaidou.product.brand.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageUtil {
	private static String[] imgExg = {"jpg","png","BMP","jif","jpeg","gif"};
	
	public static void write(byte bytes[],String outPath,String fileName){
		 File rootFile = new File(outPath); // 创建文件夹
         if (!rootFile.exists())
         {
        	 rootFile.mkdirs();
         }
		
         File  outFile= new File(outPath, fileName);
	     OutputStream  os = null;
		
	    try {
	    	os = new FileOutputStream(outFile);
			os.write(bytes, 0, bytes.length);
		    os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	    
	}
	
	
	/**
	 *输入类型判断是否image类型 
	 * */
	public static boolean checkImage(String type){
		for (String img : imgExg) {
			if(img.equals(type)){
				return true;
			}
		}
		return false;
	}
		

}
