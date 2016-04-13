package com.zhaidou.product.info.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.json.JSONUtil;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.manager.ImageSearchManager;
import com.zhaidou.imageservice.model.request.DeleteImageRequest;
import com.zhaidou.imageservice.model.request.GetImagePathRequestPO;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.GetImageResponsePO;
import com.zhaidou.imageservice.model.response.UploadImageResponsePO;
import com.zhaidou.product.info.model.ProductImageModel;
import com.zhaidou.product.info.service.impl.ProductAirServiceImpl;

/**文件操作工具类，临时文件
 * @author Johney.lee liq
 *
 * 2014-6-4
 */
public class FileUtil {
    private static final Log logger = LogFactory.getLog(ProductAirServiceImpl.class);

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
	
	 public static void uploadImage(MultipartFile[] file,String[] fileNames,HttpServletRequest req,String fileUrl,Integer type) {
	        String targetDirectory = fileUrl; //req.getSession().getServletContext().getRealPath("/product");
	        ///data/img_tmp
	        //路径不存在 则创建
	        File saveDir = new File(targetDirectory);
	        if(!saveDir.exists()){
	            saveDir.mkdirs();
	        }
	       if(file!=null){
	           
	           for(MultipartFile f: file){
	               
	               String fileSuffix= "";
	               if(f.getOriginalFilename()!=null && !"".equals(f.getOriginalFilename())){
	                   logger.info("上传图片到临时目录,上传图片原名称："+f.getOriginalFilename());
	                   fileSuffix = f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."), f.getOriginalFilename().length());
	                   
	                   long timestemp = new Date().getTime();
	                   int num = (int)(Math.random()*9000)+1000;
	                   int i=0;
	                   if(fileNames!=null && fileNames.length>0){
	                       
	                       if(type==1){
	                           for(String fileName:fileNames){
	                               if(fileName!=null && !"".equals(fileName) && fileName.indexOf(f.getOriginalFilename())>=0){
	                                   String[] nameArray = fileName.split("#-#");
	                                   fileName = nameArray[0]+"#-#"+timestemp+num+fileSuffix+"#-#"+nameArray[2];
	                                   fileNames[i]=fileName;
	                                   break;
	                               }
	                               i++;
	                           }
	                       }else{
	                           for(String fileName:fileNames){
	                               if(fileName!=null && !"".equals(fileName) && fileName.indexOf(f.getOriginalFilename())>=0){
	                                   
	                                   fileName =""+timestemp+num+fileSuffix;
	                                   fileNames[i]=fileName;
	                                   break;
	                               }
	                               i++;
	                           }
	                       }
	                   }
	                   try {
	                       InputStream in = f.getInputStream();
	                       FileOutputStream fos = new FileOutputStream(targetDirectory+"/"+timestemp+num+fileSuffix);
	                       byte[] b = new byte[(int) f.getSize()];  
	                       in.read(b);
	                       fos.write(b);
	                       in.close();
	                       fos.close();
	                       logger.info("上传图片到临时目录,成功:上传路径=="+targetDirectory+"/"+timestemp+num+fileSuffix);
	                   } catch (IOException e) {
	                       logger.error("",e);
	                   }
	               }
	           }
	       }
	        
	    }
	 
	 /**
	     * 实施获取图片 单个SKU
	     */
	    public static List<ProductImageModel> imageList(String skuCode,ImageSearchManager imageSearchManager){
	        RequestObject reqeustObject = new RequestObject();
	        GetImagePathRequestPO requestPo = new GetImagePathRequestPO();
	        requestPo.setRelationCode(skuCode);
	        requestPo.setRelationType(1l);
	        reqeustObject.setRequestParams(requestPo);
	        
	        List<ProductImageModel> imageList = new ArrayList<ProductImageModel>();
	        ProductImageModel productImageModel = null;
	        logger.info("获取图片,请求参数："+JSONUtil.toString(reqeustObject));
	        ResponseObject responseObject = imageSearchManager.getImagePath(reqeustObject);
	        logger.info("获取图片,返回值："+JSONUtil.toString(responseObject));
	        if(responseObject.getCode()==0){
	            List<GetImageResponsePO> responsePo = (List<GetImageResponsePO>)responseObject.getData();
	            if(responsePo!=null && responsePo.size()>0){
	                for(GetImageResponsePO imagePo: responsePo){
	                    productImageModel = new ProductImageModel();
	                    String imagePath = "";
	                    if(imagePo.getImagePath().indexOf("image")>=0){
	                        imagePath = imagePo.getImagePath().replace("image", "imgs");
	                    }else{
	                        imagePath = imagePo.getImagePath();
	                    }
	                    productImageModel.setImage(imagePath+"/"+imagePo.getImageName()+imagePo.getImageType());
	                    productImageModel.setLevel(imagePo.getImageIndex());
	                    imageList.add(productImageModel);
	                }
	            }
	        }else{
	            logger.error("获取SKU编号为："+skuCode+" 图片失败");
	            throw new ZhaidouRuntimeException("图片获取异常");
	        }
	        return imageList;
	    }
	    
	    /**
         * 实施获取图片 SKU List
         */
//        public static List<ProductImageModel> imageList(List<String> skuCodeList,ImageSearchManager imageSearchManager,List<ProductSkuModel> listSku){
//            RequestObject reqeustObject = new RequestObject();
//            GetImagePathBatchRequestPO requestPo = new GetImagePathBatchRequestPO();
//            requestPo.setRelationCodes(skuCodeList);;
//            requestPo.setRelationType(1l);
//            reqeustObject.setRequestParams(requestPo);
//            
//            List<ProductImageModel> imageList = new ArrayList<ProductImageModel>();
//            ProductImageModel productImageModel = null;
//            ResponseObject responseObject = imageSearchManager.getImagePathBatch(reqeustObject);
//            if(responseObject.getCode()==0){
//                GetImageBatchResponsePO responsePo = (GetImageBatchResponsePO)responseObject.getData();
//                
//                Map<String, List<GetImageResponsePO>> map = responsePo.getImageMap();
//                if(map!=null && map.size()>0){
//                    for(String skuCode: map.keySet()){
//                        List<GetImageResponsePO> imgList = map.get(skuCode);
//                        for(GetImageResponsePO imgResponsePo :imgList){
//                            productImageModel = new ProductImageModel();
//                            productImageModel.setImage(imgResponsePo.getImagePath()+"/"+imgResponsePo.getImageName()+imgResponsePo.getImageType());
//                            productImageModel.setLevel(imgResponsePo.getImageIndex());
//                            productImageModel.setProductSkuCode(skuCode);
//                            imageList.add(productImageModel);
//                        } 
//                    }
//                }
//                List<ProductImageModel> bb = new ArrayList<ProductImageModel>();
//                for(ProductImageModel productImageModel:imageList){
//                    
//                }
//            }else{
//                logger.error("获取SKU 图片集合 失败");
//                throw new TeshehuiRuntimeException("图片集合获取异常");
//            }
//            return imageList;
//        }
	    
	    /**
	     * 上传 缩略图 
	     *
	     * @param productCode
	     * @param skuCode
	     * @param fileNames
	     * @param skuKey
	     * @return
	     */
	    public static String  uploadThumbnailImage(String productCode,String thumbnailFileName,String imgTmp,ImagePathManager imagePathManager){
	        if(thumbnailFileName!=null && !"".equals(thumbnailFileName)){
	            List<Image> listImg = new ArrayList<Image>();
	            Image image = null;
	            
	            image = new Image();
	            image.setImageIndex(1);
	            
	            image.setImageName(thumbnailFileName);
	            listImg.add(image);
	            
	            RequestObject requestObjest = new RequestObject();
	            UploadImageRequestPO requestPo = new UploadImageRequestPO();
	            requestPo.setImages(listImg);
	            requestPo.setRelationCode(productCode);
	            requestPo.setTmpPath(imgTmp+"/"+productCode);
	            requestPo.setRelationType(2);
	            requestPo.setNeedlevel(1);
	            requestObjest.setRequestParams(requestPo);
	            
	            logger.info("上传图片,请求参数："+JSONUtil.toString(requestObjest));
	            ResponseObject responseObject = imagePathManager.uploadImage(requestObjest);
	            logger.info("上传图片,返回值："+JSONUtil.toString(responseObject));
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
	                logger.error("上传缩略图 ,图片 hessaion 接口错误!"+responseObject.getMsg());
	                throw new ZhaidouRuntimeException("上传缩略图 ,图片 hessaion 接口错误!"+responseObject.getMsg());
	            }
	            return imageModel.getImage();
	        }else{
	            return "";
	        }
	    }
	    
	    /**
	     * 删除图片接口
	     *
	     * @param skuCode
	     * @param level
	     * @param imagePathManager
	     */
	    public static ResponseObject deleteImage(String productCode,String skuCode,Integer level,ImagePathManager imagePathManager){
	        
	        
	        DeleteImageRequest imageRequest = new DeleteImageRequest();
	        imageRequest.setRelationCode(productCode);
	        imageRequest.setRelationSubCode(skuCode);
	        imageRequest.setImageIndex((long)level);
	        imageRequest.setRelationType(1l);
	        
	        RequestObject requestObject = new RequestObject();
	        requestObject.setRequestParams(imageRequest);
	        
	        logger.info("删除图片,请求参数："+JSONUtil.toString(requestObject));
	        ResponseObject responseObject = imagePathManager.deleteImage(requestObject);
	        logger.info("删除图片,返回值："+JSONUtil.toString(responseObject));
	        
	        return responseObject;
	    }

}