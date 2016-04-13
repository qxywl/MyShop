package com.zhaidou.product.category.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhaidou.common.util.AjaxObject;
import com.zhaidou.common.util.Response;
import com.zhaidou.framework.exception.ZhaidouRuntimeException;
import com.zhaidou.framework.model.RequestObject;
import com.zhaidou.framework.model.ResponseObject;
import com.zhaidou.framework.util.page.Page;
import com.zhaidou.imageservice.manager.ImagePathManager;
import com.zhaidou.imageservice.model.request.Image;
import com.zhaidou.imageservice.model.request.UploadImageRequestPO;
import com.zhaidou.imageservice.model.response.UploadImageResponsePO;
import com.zhaidou.product.category.model.CategoryPO;
import com.zhaidou.product.category.service.CategoryService;
import com.zhaidou.product.category.service.Category_Type;
import com.zhaidou.product.category.service.MountProductService;
import com.zhaidou.product.category.util.ImgOwnerEnum;
import com.zhaidou.product.category.util.Utils;
import com.zhaidou.product.solr.model.GoodsSolrModel;
import com.zhaidou.product.solr.service.GoodsSolrService;

/**
 * @Title: SaleCategoryController.java 
 *
 * @Package com.teshehui.product.category.controller 
 *
 * @Description:   运营分类Controller
 *
 * @author lvshuding 
 *
 * @date 2015年3月27日 下午4:24:58 
 *
 * @version V1.0
 */
@Controller("saleCategoryController")
@RequestMapping("/sale-category")
public class SaleCategoryController extends BaseController{

	private static final Log logger = LogFactory.getLog(SaleCategoryController.class);

	private final static String CURRENT_CATE_ID="currentCateId";
	
	@Value("#{propertyConfigurerForProject2['img_tmp_dir']}")
	private String IMG_TMP_DIR = "image/banner-info"; // 图片文件目录

	@Value("#{propertyConfigurerForProject2['img_tmp_dir_rel']}")
	private String IMG_TMP_DIR_Rel; // 图片文件目录
	
	@Resource
	private ImagePathManager imagePathManager;

	@Resource
	private CategoryService categoryService;
	
	@Resource
	private MountProductService mountProductService;
	
	@Resource
	private GoodsSolrService goodsSolrService;
	
	
	
	@RequestMapping("/main")
	public String toMain(HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		List<CategoryPO> poList = categoryService.queryAll(Category_Type.SALE_CATEGORY);
		
		model.addAttribute("orgTreeStr",Utils.buildJsonFormTree(poList,req.getParameter(CURRENT_CATE_ID)));
		
		return "category/sale_category";
	}
	
	@RequestMapping("/to-add")
	public String toAdd(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		model.addAttribute(CURRENT_CATE_ID, po.getParentId());
		if(po.getParentId() == null || po.getParentId().longValue() == 0l){
			model.addAttribute("isFirstLevel", true);
		}else{
			model.addAttribute("isFirstLevel", false);
		}
		
		return "category/sale_category_add";
	}
	
	/**
     * 转至图片上传页面
     */
    @RequestMapping(value="/to_image_upload",method = { RequestMethod.GET, RequestMethod.POST })
    public String toImageUpload(Model model){
    	model.addAttribute("frmSubmitUrl", "/sale-category/uploadImage");
    	return "category/category_image_upload";
    }
    
    /**
     * @Description:   上传图片
     * @param request
     * @param response
     * @param file
     * return String
     */
//    @ResponseBody
//    @RequestMapping(value="/uploadImage",method={ RequestMethod.POST },produces = {"application/json;charset=UTF-8"})
    public AjaxObject uploadImage(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam("file")   MultipartFile file, String categoryCode){
    	 
//    	 String image = "image/category/base"; //图片文件
    	 
//    	 String returnStr = Utils.uploadImage(image, file,request).toString();
    	 
    	 AjaxObject ajaxObj = Utils.uploadImage(IMG_TMP_DIR, file, request);
    	 
 		if(ajaxObj.getStatusCode() ==200 ){
 			RequestObject requestObj = new RequestObject();
 			UploadImageRequestPO requestPo = new UploadImageRequestPO();
// 			String bannerCode = NumberingRuleUtil.newBaseCode("B");
 			requestPo.setTmpPath(IMG_TMP_DIR_Rel);
 			requestPo.setNeedlevel(0);
 			requestPo.setRelationCode(categoryCode);
 			requestPo.setRelationType(ImgOwnerEnum.SALE_CATEGORY.getValue());
 			Image image = new Image(1,ajaxObj.getMessage(),"1");
 			List<Image> imageList = new ArrayList<Image>();
 			imageList.add(image);
 			requestPo.setImages(imageList);
 			requestObj.setRequestParams(requestPo);
 			ResponseObject responseObj = imagePathManager.uploadImage(requestObj);
 			if(responseObj.getCode() == -1){
 				return AjaxObject.newError(responseObj.getMsg());
 			}
 			UploadImageResponsePO responsePO = (UploadImageResponsePO)responseObj.getData();
 			String imgType = responsePO.getImageType();
 			String imgPath = responsePO.getImagePath();
 			List<Image> images = responsePO.getImages();
 			if(images != null && images.size() > 0){
 				String path = imgPath + "/" + images.get(0).getImageName() + imgType;
 				ajaxObj.setMessage(path);
 			}
// 			logger.info(JSON.toJSONString(responseObj));
 		}
 		return ajaxObj;
    	
    }
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp, @RequestParam("imageFile")   MultipartFile file){
		
		String userName = Utils.getUserNameFromRequest(req);
		
		if(po.getParentId() == null || po.getParentId().longValue() == 0l){
			if(!super.allowOperateLevel1(userName)){
				return AjaxObject.newError("你没有权限添加1级运营分类").toString();
			}
		}
		
		CategoryPO tmpPo = categoryService.selectByParentIdAndCategoryName(po, Category_Type.SALE_CATEGORY);
		if(tmpPo != null){
			return AjaxObject.newError("同一父分类下不能添加同名的分类").toString();
		}
		
		String categoryCode;
		try {
			CategoryPO parent = new CategoryPO();
			parent.setId(po.getParentId());
			categoryCode = categoryService.buildCurrentCode(parent, Category_Type.SALE_CATEGORY);
		} catch (Exception e) {
			return AjaxObject.newError(e.getMessage()).toString();
		}
		
		if(file.getSize() > 0){
			AjaxObject result = uploadImage(req, rsp, file, categoryCode);
			if(result.getStatusCode() != 200){
				return result.toString();
			}
			po.setImageUrl(result.getMessage());
		}
		po.setCategoryCode(categoryCode);
		//这里要获取登录用户的用户名
		po.setCreateUser(userName);
		po.setCreateDate(new Date(System.currentTimeMillis()));
		po.setUpdateUser(userName);
		po.setUpdateDate(po.getCreateDate());
		
		po.setShowFlag(1);
		po.setDeleteFlag(0);
		if(po.getSortNumber() == null){//设置默认值为0
			po.setSortNumber(0);
		}
		
		Response result = categoryService.insert(po, Category_Type.SALE_CATEGORY);
		return AjaxObject.newOk(result.getMsg()).toString();
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String toDelete(String delIds,HttpServletRequest req,HttpServletResponse rsp){
		
		if(StringUtils.isBlank(delIds)){
			return AjaxObject.newErrorForward("没有选中要删除的运营分类", "sale-category/main").toString();
		}
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
				
		try{
			if(categoryService.checkExistLevel1ByIds(delIds, Category_Type.SALE_CATEGORY)){
				if(!super.allowOperateLevel1(userName)){
					return AjaxObject.newErrorForward("你没有权限删除1级运营分类", "sale-category/main").toString();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return AjaxObject.newErrorForward("后台异常", "sale-category/main").toString();
		}
		
		AjaxObject checkResult = this.noProductsInSubCategory(delIds);
		if(checkResult.getStatusCode() == AjaxObject.STATUS_CODE_FAILURE){
			return checkResult.toString();
		}
		
		boolean deleteOne = true;
		if(delIds.indexOf(",") > -1){
			deleteOne = false;
		}
		
		CategoryPO po = new CategoryPO();
		po.setDeleteFlag(1);
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Response result = null;
		if(deleteOne){
			po.setId(Long.valueOf(delIds));
			result = categoryService.delete(po, Category_Type.SALE_CATEGORY);
		}else {
			result = categoryService.deleteBatch(po,delIds, Category_Type.SALE_CATEGORY);
		}
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main").toString();
	}
	
	
	@RequestMapping("/show")
	@ResponseBody
	public String toShow(String showIds,HttpServletRequest req,HttpServletResponse rsp){
		
		if(StringUtils.isBlank(showIds)){
			return AjaxObject.newErrorForward("没有选中要显示的运营分类", "sale-category/main").toString();
		}
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
				
		try{
			if(categoryService.checkExistLevel1ByIds(showIds, Category_Type.SALE_CATEGORY)){
				if(!super.allowOperateLevel1(userName)){
					return AjaxObject.newErrorForward("你没有权限显示1级运营分类", "sale-category/main").toString();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return AjaxObject.newErrorForward("后台异常", "sale-category/main").toString();
		}
		
		
		boolean deleteOne = true;
		if(showIds.indexOf(",") > -1){
			deleteOne = false;
		}
		
		CategoryPO po = new CategoryPO();
		po.setShowFlag(1);
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Response result = null;
		if(deleteOne){
			po.setId(Long.valueOf(showIds));
			result = categoryService.show(po, Category_Type.SALE_CATEGORY);
		}else {
			result = categoryService.showBatch(po,showIds, Category_Type.SALE_CATEGORY);
		}
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main").toString();
	}
	
	
	
	@RequestMapping("/hidden")
	@ResponseBody
	public String toHidden(String hidIds,HttpServletRequest req,HttpServletResponse rsp){
		
		if(StringUtils.isBlank(hidIds)){
			return AjaxObject.newErrorForward("没有选中要隐藏的运营分类", "sale-category/main").toString();
		}
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
				
		try{
			if(categoryService.checkExistLevel1ByIds(hidIds, Category_Type.SALE_CATEGORY)){
				if(!super.allowOperateLevel1(userName)){
					return AjaxObject.newErrorForward("你没有权限隐藏1级运营分类", "sale-category/main").toString();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return AjaxObject.newErrorForward("后台异常", "sale-category/main").toString();
		}
		
		AjaxObject checkResult = this.noProductsInSubCategory(hidIds);
		if(checkResult.getStatusCode() == AjaxObject.STATUS_CODE_FAILURE){
			return checkResult.toString();
		}
		
		boolean deleteOne = true;
		if(hidIds.indexOf(",") > -1){
			deleteOne = false;
		}
		
		CategoryPO po = new CategoryPO();
		po.setShowFlag(0);
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		Response result = null;
		if(deleteOne){
			po.setId(Long.valueOf(hidIds));
			result = categoryService.hidden(po, Category_Type.SALE_CATEGORY);
		}else {
			result = categoryService.hiddenBatch(po,hidIds, Category_Type.SALE_CATEGORY);
		}
		return AjaxObject.newOkForward(result.getMsg(), "sale-category/main").toString();
	}
	
	
	@RequestMapping("/to-update")
	public String toUpdate(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp,Model model){
		
		po.setId(po.getParentId());
		po.setParentId(null);
		
		po = categoryService.queryOne(po, Category_Type.SALE_CATEGORY);
		model.addAttribute("curPo", po);
		
		if(po.getParentId() == null || po.getParentId().longValue() == 0l){
			model.addAttribute("isFirstLevel", true);
		}else{
			model.addAttribute("isFirstLevel", false);
		}
		
		return "category/sale_category_update";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(CategoryPO po,HttpServletRequest req,HttpServletResponse rsp,RedirectAttributes attr, @RequestParam("imageFile")   MultipartFile file){
		/*
		 * 
		 * 校验用户登录
		 * 
		 */
		
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(req);
		
		CategoryPO tmp1 = new CategoryPO();
		tmp1.setId(po.getId());
		tmp1 = categoryService.queryOne(tmp1, Category_Type.SALE_CATEGORY);
		
		if(tmp1!=null && tmp1.getParentCode() == null && !super.allowOperateLevel1(userName)){
			return AjaxObject.newErrorForward("你没有权限修改1级运营分类","sale-category/main").toString();
		}
		
		po.setParentId(tmp1.getParentId());
		CategoryPO tmpPo = categoryService.selectByParentIdAndCategoryName(po, Category_Type.SALE_CATEGORY);
		if(tmpPo != null && tmpPo.getId().longValue() != po.getId().longValue()){
			return AjaxObject.newError("同一父分类下不能存在同名的分类").toString();
		}
		
		if(file.getSize() > 0){
			AjaxObject result = uploadImage(req, rsp, file, tmp1.getCategoryCode());
			if(result.getStatusCode() != 200){
				return result.toString();
			}
			po.setImageUrl(result.getMessage());
		}
		po.setUpdateUser(userName);
		po.setUpdateDate(new Date(System.currentTimeMillis()));
		
		if(po.getSortNumber() == null){//设置默认值为0
			po.setSortNumber(0);
		}
		Response result = categoryService.update(po, Category_Type.SALE_CATEGORY);
		
		attr.addAttribute(CURRENT_CATE_ID, po.getId());
		
        return AjaxObject.newOk(result.getMsg()).toString();
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String toSearchCategory(Model model){
		return "category/search_category";
	}
	
	@RequestMapping(value="search", method=RequestMethod.POST)
	public String searchCategory(Model model,@RequestParam String productCode){
//		String productCode = categoryPO.getCategoryCode();
		if(productCode != null && productCode.trim() != ""){
			List<String> categoryList = this.categoryService.getListByProductCode(productCode);
			model.addAttribute("list", categoryList);
		}
		return "category/search_category";
		
	}
	
	
	/**
     * 导出运营分类商品
     * @return String 返回JSON字符格式结果
     * */
	@RequestMapping(value="/export",method={  RequestMethod.GET})
    @ResponseBody
    public String export(HttpServletRequest req,HttpServletResponse resp){
    	
        String strReturn = null;
        AjaxObject returnAjax = null;
        List<CategoryPO> catePOList = null;
       
        String fileName = "运营分类表单.xlsx";
        String user_agent = req.getHeader("user-agent");
    	if(user_agent != null && user_agent.indexOf("Firefox")>0){
    		try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("编码格式转换异常"+e.getMessage(), e);
			}
    	}else{
    		fileName = toUtf8String(fileName);
    	}
        
    	String [] strExportTitle ={"一级分类编号","一级分类名称","二级分类编号","二级分类名称","三级分类编号","三级分类名称"};
    	List<String> titleList = Arrays.asList(strExportTitle);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
        	
    		 catePOList = this.categoryService.queryAll(Category_Type.SALE_CATEGORY);
        	 book =  this.categoryService.writeExcel(titleList,  catePOList);
             resp.reset();
             resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
             resp.setContentType("application/vnd.ms-excel; charset=utf-8");
             os = resp.getOutputStream();
             book.write(os);
             returnAjax = AjaxObject.newOk("导出成功");
        } catch (Exception e) {
        	logger.error("export运营异常信息==",  e);
        	returnAjax = AjaxObject.newError("export运营异常!"+e.getMessage());
        	
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    logger.error("关闭输出流出错", e);
                }
            }
        }
    	
    	strReturn = returnAjax.toString();
        return strReturn;
    }
	
	/**
	 * 校验该分类能否挂载商品
	 * 
	 * 只有该分类下没有子分类是才可有挂载商品
	 * @param model
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value="/checkCategory")
	@ResponseBody
	public String checkCategory(Model model, @RequestParam("currentCateId") Long categoryId){
		if(categoryId == null){
			return AjaxObject.newError("运营分类ID 为空, 请选择运营分类节点").toString();
		}
		try{
			if(categoryService.isLeafNode(categoryId)){
				//没有子分类，可以挂载商品
				return AjaxObject.newOk("").toString();
			}else{
				return AjaxObject.newError("商品只能挂载在运营分类末级，请确定该分类节点为叶子分类").toString();
			}
		}catch(ZhaidouRuntimeException e){
			return AjaxObject.newError(e.getMessage()).toString();
		}
	}
	
	/**
	 * 校验是否有商品已经挂载到所选运营分类或子分类下， 只有所有该子树下所有分类都没有挂载商品时才可以删除
	 * @param model
	 * @param categoryId
	 * @return
	 */
	//@RequestMapping("/noProductsInSubCategory")
	//@ResponseBody
	private AjaxObject noProductsInSubCategory(String ids){
		if(ids == null){
			return AjaxObject.newError("运营分类ID 为空, 请选择运营分类节点");
		}
		try{
			List<String> categoryCodeList = categoryService.queryCodeListByIds(ids);
			if(categoryCodeList == null){
				return AjaxObject.newError("oops~~未查询到所选分类~~");
			}
			
			if(mountProductService.hasProductInCategory(categoryCodeList)){
				return AjaxObject.newError("所勾选要删除的分类已挂载商品，无法操作");
			}else{
				return AjaxObject.newOk("");
			}
		}catch(ZhaidouRuntimeException e){
			return AjaxObject.newError(e.getMessage());
		}catch(Exception e){
			return AjaxObject.newError(e.getMessage());
		}
	}
	
	
	/**
	 * 查询没有挂载在运行分类的商品
	 * 
	 * */
	@RequestMapping(value="/proNoCategory")
	public String getProNoCategory(Page page,Map<String,Object> map,GoodsSolrModel model){
		
		List<GoodsSolrModel> goodsList = null;
		try {
			goodsList = goodsSolrService.searchListFromSolr(page,model);
		} catch (SolrServerException e) {
			logger.error("查询solr没有挂载在运营分类下的商品错误，错误信息",e);
		}
		map.put("page", page);
		map.put("goodsModel",model);
		map.put("goodsList", goodsList);
		return "category/pro_no_category";
	}
	
	
	/**
	 * 查询没有挂载在运行分类的商品
	 * 
	 * */
	@RequestMapping(value="/proNoCategory/export")
	public String toExportProNoCategory(Map<String,Object> map) {
		
		List<CategoryPO> cateList = null;
		try {
			cateList = categoryService.selectAllByLevel(1, Category_Type.BASE_CATEGORY);
		} catch (Exception e) {
			logger.error("查询一级基础分类solr错误，错误信息== " +e.getMessage(), e);
		}
		map.put("cateList", cateList);
		return "category/pro_no_category_export";
	}
	
	
	/**
     * 导出没有挂载运营分类的所有商品
     * @return String 返回JSON字符格式结果
     * */
	@RequestMapping(value="/exportProNoCategory",method={  RequestMethod.GET})
    @ResponseBody
    public String exportNoCategory(GoodsSolrModel goodsModel,HttpServletRequest req,HttpServletResponse resp){
    	
        String strReturn = null;
        AjaxObject returnAjax = null;
        List<GoodsSolrModel> goodsList = null;
       
        String fileName = "分类下的商品.xlsx";
        String user_agent = req.getHeader("user-agent");
    	if(user_agent != null && user_agent.indexOf("Firefox")>0){
    		try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("编码格式转换异常"+e.getMessage(), e);
			}
    	}else{
    		fileName = toUtf8String(fileName);
    	}
        
        /**导出字段和表名*/
    	String [] strExportTitle ={"商品编号","商品名称","品牌编号","品牌名称 ","一级基础分类编号","一级基础分类名称","二级基础分类编号","二级基础分类名称","二级基础分类编号","三级基础分类名称","价格"};
    	String [] strExportField ={"goodsCode","goodsName","brandCode","brandName","baseCateCode1","baseCateName1","baseCateCode2","baseCateName2","baseCateCode3","baseCateName3","price"};
    	
    	List<String> titleList = Arrays.asList(strExportTitle);
    	List<String> fieldList = Arrays.asList(strExportField);
    	
    	Workbook book = null;
    	OutputStream os = null;
    	try {
    		
    		 goodsList = goodsSolrService.exportGoodsByBaseCatCode(goodsModel);
    		
    		 //查询基础分类
    		 String [] baseCateCodes = goodsModel.getBaseCateCodes();//页面传过来的一级分类
    		 List<CategoryPO> cateList = new ArrayList<CategoryPO>();
    		 List<CategoryPO> tmpList = new ArrayList<CategoryPO>();
    		 if(baseCateCodes != null && baseCateCodes.length>0){
    			 int length = baseCateCodes.length;
    			 for(int i=0;i<length;i++){
    				 tmpList = categoryService.selectParentAndSonByCode(baseCateCodes[i],Category_Type.BASE_CATEGORY);
    				 if(tmpList != null && tmpList.size()>0 ){
    					 cateList.addAll(tmpList);
    				 }
    			 }
    		 }else{
    			 tmpList = categoryService.selectParentAndSonByCode(null,Category_Type.BASE_CATEGORY);
    			 cateList.addAll(tmpList);
    		 }
    		 
    		 if(goodsList == null){
    			 goodsList = new ArrayList<GoodsSolrModel>();
    		 }
    		 book = goodsSolrService.exportProNoCategoryExcel(goodsList, cateList, titleList, fieldList);
             resp.reset();
             resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
             resp.setContentType("application/vnd.ms-excel; charset=utf-8");
             os = resp.getOutputStream();
             book.write(os);
             returnAjax = AjaxObject.newOk("导出成功");
             
        } catch (Exception e) {
        	logger.error("export没有挂载分类的商品==",  e);
        	returnAjax = AjaxObject.newError("export没有挂载分类的商品!"+e.getMessage());
        	
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    logger.error("关闭输出流出错", e);
                }
            }
        }
    	
    	strReturn = returnAjax.toString();
        return strReturn;
    }
	
	
	
	/**
	 * 跳转到批量导入商品挂载
	 * */
	@RequestMapping(value="/toBatchImportCate")
	public String toBatchImport(Map<String,Object> map) {
		return "category/sale_category_batch_import";
	}
	
	
	
	/**
	 * @Description: 批量挂载运营分类 -- 商品
	 * */
	@RequestMapping(value = "/batchCategoryImport", method = { RequestMethod.POST }, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String batchCategoryImport(MultipartHttpServletRequest request, @ModelAttribute CategoryPO saleCategory) {

		AjaxObject returnAjax = null;

		MultipartFile mf = request.getFile("file");
		String filename = mf.getOriginalFilename();
		//这里要获取登录用户的用户名
		String userName = Utils.getUserNameFromRequest(request);
		saleCategory.setUpdateUser(userName);

		logger.info("手动挂载商品上传[用户:" + userName + "\t文件名:" + filename + "\t文件大小:" + mf.getSize() + "]");
		
		
		if(!(filename.endsWith(".xls") || filename.endsWith(".xlsx"))){
			return AjaxObject.newError("导入失败：文件类型不正确").toString();
		}
		
		try {
			if (mf.getSize() == 0) {
				returnAjax = AjaxObject.newError("文件为空");
			} else {
				ResponseObject responseObj = mountProductService.batchCategoryImport(filename, mf.getInputStream(),userName);
				if(responseObj.getCode() == ResponseObject.CODE_SUCCESS_VALUE){
					returnAjax = AjaxObject.newOkForward(responseObj.getMsg(), "sale-category/product/list?"+CURRENT_CATE_ID+"="+saleCategory.getId());
				}else{
					returnAjax = AjaxObject.newError(responseObj.getMsg());
				}
			}
		} catch(NullPointerException e){
			logger.error("读取上传文件出错2:", e);
			returnAjax = AjaxObject.newError(e.getMessage());
		} catch (IOException e) {
			logger.error("读取上传文件出错", e);
			returnAjax = AjaxObject.newError("导入失败：导入格式有问题");
		} catch (Exception e) {
			logger.error("产品手动挂载失败", e);
			returnAjax = AjaxObject.newError("导入失败：系统错误");
		}
		String ss = returnAjax.toString();
		logger.info("returnAjax ============" + ss);

		return returnAjax.toString();
	}
	
	/**
     * 乱码解决
     * */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
