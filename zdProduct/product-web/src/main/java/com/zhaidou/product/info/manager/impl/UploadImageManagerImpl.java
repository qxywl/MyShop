package com.zhaidou.product.info.manager.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.util.date.DatetimeUtil;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.product.info.manager.UploadImageManager;
import com.zhaidou.product.info.util.FileUtil;

@Service("uploadImageManager")
public class UploadImageManagerImpl implements UploadImageManager{
    private static Logger logger = Logger.getLogger(UploadImageManagerImpl.class);
    
    @Resource
    private ImagePathManager imagePathManager;
    
    @Value("#{propertyConfigurerForProject2['img_tmp_dir']}")
    private String imgTmpDir;     //图片临时目录路径
    
    @Value("#{propertyConfigurerForProject2['excel_bak_dir']}")
    private String excelBakDir;     //excel临时目录路径
    /**
     * 上传图片处理
     *
     * @param file
     */
    @Override
    public void uploadImage(MultipartFile[] file,String[] fileNames,HttpServletRequest req,String productCode,Integer type) {
        
        FileUtil.uploadImage(file,fileNames, req, imgTmpDir+"/"+productCode,type);
    }
    
    @Override
    public void uploadExcelToBak(MultipartFile file, HttpServletRequest req) {
        
        String targetDirectory = excelBakDir; //req.getSession().getServletContext().getRealPath("/product");;
        //路径不存在 则创建
        File saveDir = new File(targetDirectory);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        
       if(file!=null){
           try {
               String prefix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
               String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());

               InputStream in = file.getInputStream();
               FileOutputStream fos = new FileOutputStream(targetDirectory+"/"+prefix+DatetimeUtil.currentDateMillis()+suffix);
               byte[] b = new byte[(int) file.getSize()];  
               in.read(b);
               fos.write(b);
               in.close();
               fos.close();
           } catch (IOException e) {
               logger.error("",e);
               throw new ZhaidouRuntimeException("上传到备份目录错误！");
           }
           
       }
    }

}
