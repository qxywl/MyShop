package com.zhaidou.product.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**文件操作工具类，临时文件
 * @author Johney.lee liq
 *
 * 2014-6-4
 */
public class FileUtil {


    static public InputStream getInputStream(String filename) throws
            IOException {
        File file = new File(filename);

        FileInputStream fin = new FileInputStream(file);

        return fin;
    }

    static public OutputStream getOutputStream(String filename) throws
            IOException {
        FileOutputStream fout = new FileOutputStream(filename);

        return fout;
    }
    /**读取文本文件所有文本
     * @param path
     * @return
     */
    public static String getFileDataStr(String path) {
		String laststr = "";
		BufferedReader br;
		try {
			String paths=getSrcFilePath(path);
			br = new BufferedReader(new FileReader(paths));
			String data;
				data = br.readLine();//一次读入一行，直到读入null为文件结束
				while( data!=null){
					laststr+=data;
				      data = br.readLine(); //接着读下一行
				}
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return laststr;
	}
    
    private static String getSrcFilePath(String filename){
    	String xmlpath = FileUtil.class.getResource("/").getPath()+filename; 
    	return xmlpath.toString();
    }

    /**
	 * 将文件绝对路径转化为虚拟路径
	 * @param realPath 实际路径
	 * @param dirPrefix	文件绝对路径
	 * @param pathPrefix	映射的前缀
	 * @return
	 */
	public static String convertDirToPath(String realPath, String dirPrefix,
			String pathPrefix) {
		//windows平台下要将'\'转换成'/'
		if (File.separator.equals("\\")) {
			return realPath.replace('\\', '/').replaceFirst(dirPrefix.replace('\\', '/'), pathPrefix);
		} else {
			return realPath.replaceFirst(dirPrefix, pathPrefix);
		}
	}
	   public static void main(String[] args){
	        System.out.println(getSrcFilePath("AirLines.json"));;
	    }
}