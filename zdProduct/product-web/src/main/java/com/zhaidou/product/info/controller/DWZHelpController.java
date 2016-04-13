package com.zhaidou.product.info.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.UploadImageResponsePO;
import com.zhaidou.product.attributies.model.ProductCateAttrGroupModel;
import com.zhaidou.product.brand.model.ProductBrandModel;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.info.manager.ProductAttrbuteManager;
import com.zhaidou.product.info.manager.ProductCategoryManager;
import com.zhaidou.product.info.manager.ProductManager;
import com.zhaidou.product.info.manager.UploadImageManager;
import com.zhaidou.product.info.model.ProductImageModel;
import com.zhaidou.product.info.util.InfoUtil;

@Controller
@RequestMapping(value="/dwz")
public class DWZHelpController {
    
    private static Logger logger = Logger.getLogger(DWZHelpController.class);
    @Resource
    private ProductCategoryManager productCategoryManager;
    @Resource
    private ProductManager productManager;
    @Resource
    private ImagePathManager imagePathManager;
    @Resource
    private ProductAttrbuteManager productAttrbuteManager;
    @Value("#{propertyConfigurerForProject2['img_tmp_dir']}")
    private String imgTmpDir;
    
    @RequestMapping(value="/combox",method={RequestMethod.GET,RequestMethod.POST})
//    @ResponseBody
    public String getComboxString(String code,String type,Map<String,Object> map){
        
        CategoryPO categoryPo = new CategoryPO();
        categoryPo.setCategoryCode(code);
        List<CategoryPO> listCategory = productCategoryManager.getCategoBaseryList(categoryPo);
        
        map.put("listCategory", listCategory);
        if("combox".equals(type)){
            return "info/product/ajax/combox";
        }else{
            return "info/product/ajax/input_li";
        }
    }
    
    @RequestMapping(value="/selectback",method={RequestMethod.GET,RequestMethod.POST})
    public String selectBack(ProductBrandModel productBrandModel,Page page,Map<String,Object> map){
        List<ProductBrandModel> listBrand = productManager.getBrand(productBrandModel,page);
        map.put("listBrand", listBrand);
        map.put("target", "dialog");
        return "info/attribute/attribute_list";
    }
    
    @RequestMapping(value="/attr_group",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String attrGroupAjax(String catCode){
        List<ProductCateAttrGroupModel> listAttr = productAttrbuteManager.getAttrByCategory(catCode);
        
        return JSONUtil.toString(listAttr);
    }
    
//    editor
    /**
     * 编辑器 图片上传操作
     *
     * @param productBrandModel
     * @param page
     * @param map
     * @return
     */
    @RequestMapping(value="/editor_upload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String editorUpload(MultipartFile filedata,HttpServletRequest req,HttpServletResponse resp,String type,String attrName,String productCode){
        //获取根目录
        String targetDirectory = imgTmpDir+"/"+productCode;
        //路径不存在 则创建
        File saveDir = new File(targetDirectory);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        String fileAbsoluteName="";
        try {
            InputStream in = filedata.getInputStream();
            fileAbsoluteName=targetDirectory+"/"+filedata.getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(fileAbsoluteName);
            byte[] b = new byte[(int) filedata.getSize()];  
            in.read(b);
            fos.write(b);
            in.close();
            fos.close();
        } catch (IOException e) {
            e.getStackTrace();
            logger.error(""+e.toString());
        }
        
        List<Image> listImg = new ArrayList<Image>();
        Image image = null;
        
        image = new Image();
        if(InfoUtil.map.get(productCode)!=null && !"".equals(InfoUtil.map.get(productCode))){
            int index = (Integer)InfoUtil.map.get(productCode)+1;
            image.setImageIndex(index);
            InfoUtil.map.put(productCode, index);
        }else{
            image.setImageIndex(1);
            InfoUtil.map.put(productCode, 1);
        }
        
        image.setImageName(filedata.getOriginalFilename());
        listImg.add(image);
        
        RequestObject requestObjest = new RequestObject();
        UploadImageRequestPO requestPo = new UploadImageRequestPO();
        requestPo.setImages(listImg);
        requestPo.setRelationCode(productCode);
        requestPo.setTmpPath("product/"+productCode);
        requestPo.setRelationType(2);
        requestObjest.setRequestParams(requestPo);
        ResponseObject responseObject = imagePathManager.uploadImage(requestObjest);
        ProductImageModel imageModel = null;
        if(responseObject.getCode()==0){
            UploadImageResponsePO responsePo = (UploadImageResponsePO)responseObject.getData();
            listImg = responsePo.getImages();
            for(Image img :listImg){
                imageModel = new ProductImageModel();
                imageModel.setLevel(Long.parseLong(String.valueOf(img.getImageIndex())));
                imageModel.setImage(responsePo.getImagePath()+"/"+img.getImageName()+responsePo.getImageType());
            }
        }else{
            throw new ZhaidouRuntimeException("图片 hessaion 接口异常！"+responseObject.getMsg());
        }
        
        Map<String,String> map=new HashMap<String,String>();
        map.put("err", "");
        map.put("msg", imageModel.getImage());
        return JSONUtil.toString(map);
    }
    
    @RequestMapping(value="/to_upload")
    public String toUpload(){
        return "info/product/test_upload";
    }
    
//    @RequestMapping(value="/upload" ,method={RequestMethod.GET,RequestMethod.POST})
//    public String uploadImg(@RequestParam MultipartFile[] file,String userName){
//        System.out.println(file.length);
//        for(int i = 0;i<file.length;i++){
//            System.out.println("fileName---------->" + file[i].getOriginalFilename());
//        }
//        return "";
//    }
    
    @Resource
    private UploadImageManager uploadImageManager;
    
    /**
     * SKU 图片 ajax 异步上传临时目录
     *
     * @param file
     * @param fileName
     * @param productCode
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "/image_ajax", method = { RequestMethod.GET,
            RequestMethod.POST })
    @ResponseBody
    public String imageAjaxSubmit(@RequestParam MultipartFile file,String productCode,String fileName,HttpServletRequest req,HttpServletResponse resp){
        String targetDirectory = imgTmpDir+"/"+productCode; //req.getSession().getServletContext().getRealPath("/product");;
        ///data/img_tmp
        //路径不存在 则创建
        File saveDir = new File(targetDirectory);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        logger.info("SKU 图片上传临时目录,图片名称："+file.getOriginalFilename()+", 上传路径："+targetDirectory);
        
        String fileAbsoluteName = "";
        try {
            InputStream in = file.getInputStream();
            fileAbsoluteName = targetDirectory + "/"
                    + fileName;
            FileOutputStream fos = new FileOutputStream(fileAbsoluteName);
            byte[] b = new byte[(int) file.getSize()];
            in.read(b);
            fos.write(b);
            in.close();
            fos.close();
            
//            resp.setCharacterEncoding("UTF-8");
//            PrintWriter write = resp.getWriter();
//            write.print(fileName);
//            write.flush();
//            write.close();
        } catch (IOException e) {
            logger.error(e);
        }
        
        
        logger.info("SKU 图片上传临时目录,上传成功,重命名为："+fileName);
        return fileName;
    }
}
