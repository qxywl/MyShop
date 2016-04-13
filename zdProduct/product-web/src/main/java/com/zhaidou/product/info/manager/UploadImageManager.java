package com.zhaidou.product.info.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageManager {
    
    /**
     * 上传图片处理
     *
     * @param file
     */
    public void uploadImage(MultipartFile[] file,String[] fileNames,HttpServletRequest req,String productCode,Integer type);
    
    public void uploadExcelToBak(MultipartFile file, HttpServletRequest req);
}
