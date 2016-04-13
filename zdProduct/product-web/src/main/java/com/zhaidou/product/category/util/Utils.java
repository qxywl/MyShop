package com.zhaidou.product.category.util;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.product.category.model.CategoryPO;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Title: Utils.java 
 *
 * @Package com.teshehui.product.category.util 
 *
 * @Description:   工具类
 *
 * @author lvshuding 
 *
 * @date 2015年3月25日 上午10:42:45 
 *
 * @version V1.0
 */
public class Utils {

	/**
	 * @Description: 根据分类列表构造zTree格式树
	 * @param poList
	 * return String
	 */
	public static String buildJsonFormTree(List<CategoryPO> poList,String selectCateId){
		if(poList == null || poList.size()==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(CategoryPO po:poList){
			sb.append("{").append("id:").append(po.getId()).append(",")
			  .append("pId:").append(po.getParentId()==null?"0":po.getParentId()).append(",")
			  .append("\"name\":\"").append(po.getCategoryName()).append("\"").append(",")
			  .append("\"showFlag\":").append(po.getShowFlag()).append("");
			
			if(StringUtils.isNotBlank(selectCateId) && po.getId().longValue() == Long.parseLong(selectCateId)){
				sb.append(",checked:true, open:true");
			}
			sb.append("}").append(",");
		}
		return sb.substring(0,sb.length()-1);
	}
	
	
	/**
	 * @Description:  从Request中获取登录的用户名
	 * @param req
	 * return String
	 */
	@SuppressWarnings("unchecked")
	public static String getUserNameFromRequest(HttpServletRequest req){
		Object userObj = req.getSession().getAttribute("user");
		if(userObj != null){
			Map<String, String> userMap = (Map<String, String>)userObj;
			return userMap.get("userName");
		}
		return null;
	}
	
	public static AjaxObject uploadImage(String image,MultipartFile file,HttpServletRequest request){
		AjaxObject returnAjax = null;
    	
	   	 //创建图片文件的根目录
//	   	 String path=request.getSession().getServletContext().getRealPath("/");
	   	 String contextPath = request.getContextPath();
//	   	 System.out.println("ContextPath == " + contextPath);
	   	 String imagePath = image;
	   	 File dirSaveTempFile = new File(imagePath); // 创建文件夹
         if (!dirSaveTempFile.exists()){
         	dirSaveTempFile.mkdirs();
         }
   	 
	     String filename = file.getOriginalFilename();
	    
	     //解析文件名,组装文件名
	     String fileExt = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	     Long dateLong = new Date().getTime();
	     if("jpg".endsWith(fileExt)){//
	    	 filename = dateLong + ".jpg"; 
	     }else if("png".endsWith(fileExt)){
	    	 filename = dateLong + ".png"; 
	     }else if("BMP".endsWith(fileExt)){
	    	 filename = dateLong + ".BMP"; 
	     }else if("jif".endsWith(fileExt)){
	    	 filename = dateLong + ".jif"; 
	     }else if("jpeg".endsWith(fileExt)){
	    	 filename = dateLong + ".jpeg"; 
	     }else if("gif".endsWith(fileExt)){
	    	 filename = dateLong + ".gif";
	     }
         File  uploadedFile= new File(imagePath, filename);
    	 OutputStream os = null;
		 try {
			 os = new FileOutputStream(uploadedFile);
			 byte buf[] = file.getBytes();// 可以修改 1024 以提高读取速度
		     os.write(buf, 0, buf.length);
		     os.flush();
		     os.close();
		     returnAjax = AjaxObject.newOk("图片上传成功");
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
			returnAjax = AjaxObject.newOk("图片上传失败.");
		 } catch (IOException e) {
			e.printStackTrace();
			returnAjax = AjaxObject.newOk("图片上传失败..");
		 } finally{ }
	   	 
	   	 returnAjax.setMessage(filename);
    	 return returnAjax;
	}
}
